package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTDouble(val value: Double) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeDouble(value)
    }

    override fun deepClone() = NBTDouble(value)

    override fun getTypeId(): Byte = 6

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTDouble) return false

        return other.value == value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "${value}D"

    internal object Deserializer : TagDeserializer<NBTDouble>() {
        override fun deserialize(name: String, input: DataInput) = NBTDouble(input.readDouble())
    }
}