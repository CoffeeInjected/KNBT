package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTInt(val value: Int) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeInt(value)
    }

    override fun toString() = "$value"

    override fun deepClone() = NBTInt(value)

    override fun getTypeId() = 3.toByte()

    internal object Deserializer : TagDeserializer<NBTInt>() {
        override fun deserialize(name: String, input: DataInput) = NBTInt(input.readInt())
    }
}