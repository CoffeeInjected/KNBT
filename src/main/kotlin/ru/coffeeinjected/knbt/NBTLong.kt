package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTLong(val value: Long) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeLong(value)
    }

    override fun toString() = "${value}L"

    override fun deepClone() = NBTLong(value)

    internal object Deserializer : TagDeserializer<NBTLong>() {
        override fun deserialize(name: String, input: DataInput) = NBTLong(input.readLong())
    }
}