package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTLongArray(val value: LongArray) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeInt(value.size)
        value.forEach(output::writeLong)
    }

    override fun toString() = "[L;${value.joinToString(separator = ",")}]"

    override fun deepClone() = NBTLongArray(value.copyOf())

    override fun getTypeId() = 12.toByte()

    internal object Deserializer : TagDeserializer<NBTLongArray>() {
        override fun deserialize(name: String, input: DataInput): NBTLongArray {
            return NBTLongArray(LongArray(input.readInt()) { input.readLong() })
        }
    }
}