package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTByteArray(val value: ByteArray) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeInt(value.size)
        output.write(value)
    }

    override fun deepClone() = NBTByteArray(value.copyOf())

    override fun getTypeId() = 7.toByte()

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTByteArray) return false

        return other.value.contentEquals(value)
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "[B;${value.joinToString(separator = ",")}]"

    internal object Deserializer : TagDeserializer<NBTByteArray>() {
        override fun deserialize(name: String, input: DataInput): NBTByteArray {
            val arr = ByteArray(input.readInt())
            input.readFully(arr)
            return NBTByteArray(arr)
        }
    }
}