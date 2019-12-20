package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTInt(val value: Int) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeInt(value)
    }

    override fun deepClone() = NBTInt(value)

    override fun getTypeId(): Byte = 3

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTInt) return false

        return other.value == value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "$value"

    internal object Deserializer : TagDeserializer<NBTInt>() {
        override fun deserialize(name: String, input: DataInput) = NBTInt(input.readInt())
    }
}