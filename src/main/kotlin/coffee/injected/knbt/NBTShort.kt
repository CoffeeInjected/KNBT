package coffee.injected.knbt

import coffee.injected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTShort(val value: Short) : NBTTag {

    override fun write(output: DataOutput) {
        output.writeShort(value.toInt())
    }

    override fun deepClone() = NBTShort(value)

    override fun getTypeId(): Byte = 2

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTShort) return false

        return other.value == value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "${value}S"

    internal object Deserializer : TagDeserializer<NBTShort>() {
        override fun deserialize(name: String, input: DataInput) = NBTShort(input.readShort())
    }
}