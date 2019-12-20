package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTLong(val value: Long) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeLong(value)
    }

    override fun deepClone() = NBTLong(value)

    override fun getTypeId(): Byte = 4

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTLong) return false

        return other.value == value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "${value}L"

    internal object Deserializer : TagDeserializer<NBTLong>() {
        override fun deserialize(name: String, input: DataInput) = NBTLong(input.readLong())
    }
}