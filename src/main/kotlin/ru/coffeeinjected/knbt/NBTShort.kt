package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTShort(val value: Short) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeShort(value.toInt())
    }

    override fun toString() = "${value}S"

    override fun deepClone() = NBTShort(value)

    internal object Deserializer : TagDeserializer<NBTShort>() {
        override fun deserialize(name: String, input: DataInput) = NBTShort(input.readShort())
    }
}