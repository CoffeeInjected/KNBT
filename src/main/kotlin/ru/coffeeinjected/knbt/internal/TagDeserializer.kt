package ru.coffeeinjected.knbt.internal

import ru.coffeeinjected.knbt.NBTTag
import java.io.DataInput

abstract class TagDeserializer<T : NBTTag> {

    abstract fun deserialize(name: String, input: DataInput): T
}