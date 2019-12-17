package ru.coffeeinjected.knbt.internal

import ru.coffeeinjected.knbt.NBTTag
import java.io.DataInputStream

abstract class TagParser<T : NBTTag> {

    abstract fun parse(name: String, input: DataInputStream): T
}