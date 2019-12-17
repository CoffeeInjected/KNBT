import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import ru.coffeeinjected.knbt.NBTByteArray
import ru.coffeeinjected.knbt.NBTCompound
import ru.coffeeinjected.knbt.io.NBTReader

class Tests : StringSpec({
    fun readFile(name: String) = Tests::class.java.classLoader.getResourceAsStream(name)!!

    "hello_world.nbt file parsing and printing" {
        readFile("hello_world.nbt").use {
            NBTReader(it).readNBT().toString()
        }
    }
    "bigtest.nbt file parsing and printing (compressed)" {
        readFile("bigtest.nbt").use {
            println(NBTReader(it, true).readNBT().toString())
        }
    }
    "Player-nan-value.nbt file parsing and printing (compressed)" {
        readFile("Player-nan-value.nbt").use {
            println(NBTReader(it, true).readNBT().toString())
        }
    }
    "bigtest.nbt byte array test" {
        readFile("bigtest.nbt").use {
            val testArray = (NBTReader(it, true).readNBT() as NBTCompound).get("byteArrayTest (the first 1000 values of (n*n*255+n*7)%100, starting with n=0 (0, 62, 34, 16, 8, ...))") as NBTByteArray
            testArray.value.size shouldBe 1000
            for (i in 0..999) {
                testArray.value[i] shouldBe ((i * i * 255 + i * 7) % 100).toByte()
            }
        }
    }
})