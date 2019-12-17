package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTFloat(val value: Float) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeFloat(value)
    }

    override fun toString() = "${value}F"

    override fun deepClone() = NBTFloat(value)

    internal object Deserializer : TagDeserializer<NBTFloat>() {
        override fun deserialize(name: String, input: DataInput) = NBTFloat(input.readFloat())
    }
}