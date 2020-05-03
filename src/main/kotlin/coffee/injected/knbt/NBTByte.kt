package coffee.injected.knbt

import coffee.injected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTByte(val value: Byte) : NBTTag<NBTByte> {

    override fun write(output: DataOutput) {
        output.writeByte(value.toInt())
    }

    override fun deepClone() = NBTByte(value)

    override fun getTypeId(): Byte = 1

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTByte) return false

        return other.value == value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "${value}B"

    internal object Deserializer : TagDeserializer<NBTByte>() {
        override fun deserialize(name: String, input: DataInput) = NBTByte(input.readByte())
    }
}
