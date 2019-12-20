package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTString(val value: String) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeUTF(value)
    }

    override fun deepClone() = NBTString(value)

    override fun getTypeId(): Byte = 8

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTString) return false

        return other.value == value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "\"${value.replace("\"", "\\\"")}\""

    internal object Deserializer : TagDeserializer<NBTString>() {
        override fun deserialize(name: String, input: DataInput) = NBTString(input.readUTF())
    }
}