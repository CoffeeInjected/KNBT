import ru.coffeeinjected.knbt.io.NBTInputStream

class Test

fun main() {
    Test::class.java.classLoader.getResourceAsStream("hello_world.nbt")!!.use {
        println(NBTInputStream(it).parse().toString())
    }
}