package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTByteArray(val value: ByteArray) : NBTTag {

    val size: Int
        get() = value.size

    operator fun get(index: Int) = value[index]
    operator fun set(index: Int, value: Byte) = this.value.set(index, value)
    operator fun iterator(): ByteIterator = value.iterator()

    override fun write(output: DataOutput) {
        output.writeInt(value.size)
        output.write(value)
    }

    override fun deepClone() = NBTByteArray(value.copyOf())

    override fun getTypeId(): Byte = 7

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