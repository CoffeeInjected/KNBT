import arrow.core.success
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import ru.coffeeinjected.knbt.io.NBTReader

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
    "bigtest.nbt byte array test" {
        readFile("bigtest.nbt").use {
            val testArray = NBTReader(it, true).readNBT().asNBTCompound().get("byteArrayTest (the first 1000 values of (n*n*255+n*7)%100, starting with n=0 (0, 62, 34, 16, 8, ...))")!!.asNBTByteArray()
            testArray.value.size shouldBe 1000
            for (i in 0 until 1000) {
                testArray.value[i] shouldBe ((i * i * 255 + i * 7) % 100).toByte()
            }
        }
    }
})