package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTEnd private constructor() : NBTTag {

    override fun write(output: DataOutput) {
        throw UnsupportedOperationException("Can't serialize NBTTagEnd separately")
    }

    override fun toString() = throw UnsupportedOperationException("Can't evaluate tag end")

    override fun deepClone() = throw UnsupportedOperationException("Can't clone tag end")

    override fun getTypeId() = 0.toByte()

    internal object Deserializer : TagDeserializer<NBTEnd>() {
        override fun deserialize(name: String, input: DataInput) = throw UnsupportedOperationException("You can't just create NBTTagEnd")
    }
}