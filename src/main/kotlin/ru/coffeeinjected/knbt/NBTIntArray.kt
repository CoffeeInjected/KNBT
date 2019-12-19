package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTIntArray(val value: IntArray) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeInt(value.size)
        value.forEach(output::writeInt)
    }

    override fun deepClone() = NBTIntArray(value.copyOf())

    override fun getTypeId() = 11.toByte()

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTIntArray) return false

        return other.value.contentEquals(value)
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "[I;${value.joinToString(separator = ",")}]"

    internal object Deserializer : TagDeserializer<NBTIntArray>() {
        override fun deserialize(name: String, input: DataInput): NBTIntArray {
            return NBTIntArray(IntArray(input.readInt()) {
                input.readInt()
            })
        }
    }
}