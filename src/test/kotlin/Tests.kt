import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import ru.coffeeinjected.knbt.NBTByteArray
import ru.coffeeinjected.knbt.NBTCompound
import ru.coffeeinjected.knbt.io.NBTInputStream

class Tests : StringSpec({
    fun readFile(name: String) = Tests::class.java.classLoader.getResourceAsStream(name)!!

    "hello_world.nbt file parsing and printing" {
        readFile("hello_world.nbt").use {
            NBTInputStream(it).parse().toString()
        }
    }
    "bigtest.nbt file parsing and printing (compressed)" {
        readFile("bigtest.nbt").use {
            println(NBTInputStream(it, true).parse().toString())
        }
    }
    "Player-nan-value.nbt file parsing and printing (compressed)" {
        readFile("Player-nan-value.nbt").use {
            println(NBTInputStream(it, true).parse().toString())
        }
    }
    "bigtest.nbt byte array test" {
        readFile("bigtest.nbt").use {
            val testArray = (NBTInputStream(it, true).parse() as NBTCompound).get("byteArrayTest (the first 1000 values of (n*n*255+n*7)%100, starting with n=0 (0, 62, 34, 16, 8, ...))") as NBTByteArray
            testArray.size shouldBe 1000
            for (i in 0..999) {
                testArray[i] shouldBe (i * i * 255 + i * 7) % 100
            }
        }
    }
})