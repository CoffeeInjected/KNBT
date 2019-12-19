package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTDouble(val value: Double) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeDouble(value)
    }

    override fun toString() = "${value}D"

    override fun deepClone() = NBTDouble(value)

    override fun getTypeId() = 6.toByte()

    internal object Deserializer : TagDeserializer<NBTDouble>() {
        override fun deserialize(name: String, input: DataInput) = NBTDouble(input.readDouble())
    }
}