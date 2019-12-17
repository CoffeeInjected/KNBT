package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTByteArray(val value: ByteArray) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeInt(value.size)
        output.write(value)
    }

    override fun toString() = "[B;${value.joinToString(separator = ",")}]"

    override fun deepClone() = NBTByteArray(value.copyOf())

    internal object Deserializer : TagDeserializer<NBTByteArray>() {
        override fun deserialize(name: String, input: DataInput): NBTByteArray {
            val arr = ByteArray(input.readInt())
            input.readFully(arr)
            return NBTByteArray(arr)
        }
    }
}