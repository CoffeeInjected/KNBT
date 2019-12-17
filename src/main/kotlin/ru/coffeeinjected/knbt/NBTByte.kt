package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTByte(val value: Byte) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeByte(value.toInt())
    }

    override fun toString() = "${value}B"

    override fun deepClone() = NBTByte(value)

    internal object Deserializer : TagDeserializer<NBTByte>() {
        override fun deserialize(name: String, input: DataInput) = NBTByte(input.readByte())
    }
}
