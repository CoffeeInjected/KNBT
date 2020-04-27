import arrow.core.success
import coffee.injected.knbt.NBTTag
import coffee.injected.knbt.io.NBTReader
import coffee.injected.knbt.io.NBTWriter
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream

class Tests : StringSpec({
    fun readFile(name: String) = Tests::class.java.classLoader.getResourceAsStream(name)!!

    "hello_world.nbt file parsing and printing" {
        readFile("hello_world.nbt").use {
            println(NBTReader(it).readNBT().asNBTCompound())
        }
    }
    "Compound to Byte wrong conversion" {
        readFile("hello_world.nbt").use {
            try {
                println(NBTReader(it).readNBT().asNBTByte())
            } catch (e: IllegalArgumentException) {
                if (e.message == "NBTCompound cannot be converted to NBTByte") {
                    success()
                } else {
                    throw e
                }
            }
        }
    }
    "bigtest.nbt file parsing and printing (compressed)" {
        readFile("bigtest.nbt").use {
            println(NBTReader(it, true).readNBT().asNBTCompound())
        }
    }
    "Player-nan-value.nbt file parsing and printing (compressed)" {
        readFile("Player-nan-value.nbt").use {
            println(NBTReader(it, true).readNBT().asNBTCompound())
        }
    }
    "bigtest.nbt byte array" {
        readFile("bigtest.nbt").use {
            val testArray = NBTReader(it, true).readCompound()
                    .getByteArray("byteArrayTest (the first 1000 values of (n*n*255+n*7)%100, starting with n=0 (0, 62, 34, 16, 8, ...))")
            testArray shouldBe ByteArray(1000) { i -> ((i * i * 255 + i * 7) % 100).toByte() }
        }
    }
    "bigtest.nbt deepClone toString" {
        readFile("bigtest.nbt").use {
            NBTReader(it, true).readCompound().apply {
                val clone = deepClone()
                toString() shouldBe clone.toString()
            }
        }
    }
    "bigtest.nbt deepClone equality" {
        readFile("bigtest.nbt").use {
            NBTReader(it, true).readCompound().apply {
                (this == deepClone()) shouldBe true
                (this === deepClone()) shouldBe false
            }
        }
    }

    fun testSerialization(fileName: String, compressed: Boolean) {
        var bytes = readFile(fileName).readBytes()
        fun parse(stream: InputStream) = NBTReader(stream, compressed).read().also { println(it) }
        fun serialize(tag: Pair<String, NBTTag>) = ByteArrayOutputStream().use { out ->
            NBTWriter(out, compressed).use { it.write(tag.first, tag.second) }
            val arr = out.toByteArray()
            val dSize = arr.size - bytes.size
            println("dSize=$dSize")
//            dSize shouldBe 0
            arr
        }

        println("1/5 Parsing...")
        val parse1 = parse(ByteArrayInputStream(bytes))

        println("2/5 Serializing...")
        bytes = serialize(parse1)

        println("3/5 Parsing...")
        val parse2 = parse(ByteArrayInputStream(bytes))
        parse1.second.toString() shouldBe parse2.second.toString()

        println("4/5 Serializing...")
        bytes = serialize(parse2)

        println("5/5 Parsing...")
        val parse3 = parse(ByteArrayInputStream(bytes))
        parse2.second.toString() shouldBe parse3.second.toString()
    }

    "hello_world.nbt serialization" { testSerialization("hello_world.nbt", false) }
    "bigtest.nbt serialization" { testSerialization("bigtest.nbt", true) }
    "Player-nan-value.nbt serialization" { testSerialization("Player-nan-value.nbt", true) }
})