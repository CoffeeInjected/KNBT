package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTString(val value: String) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeUTF(value)
    }

    override fun toString() = "\"${value.replace("\"", "\\\"")}\""

    override fun deepClone() = NBTString(value)

    override fun getTypeId() = 8.toByte()

    internal object Deserializer : TagDeserializer<NBTString>() {
        override fun deserialize(name: String, input: DataInput) = NBTString(input.readUTF())
    }
}