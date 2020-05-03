package coffee.injected.knbt

import coffee.injected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTFloat(val value: Float) : NBTTag<NBTFloat> {

    override fun write(output: DataOutput) {
        output.writeFloat(value)
    }

    override fun deepClone() = NBTFloat(value)

    override fun getTypeId(): Byte = 5

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTFloat) return false

        return other.value == value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "${value}F"

    internal object Deserializer : TagDeserializer<NBTFloat>() {
        override fun deserialize(name: String, input: DataInput) = NBTFloat(input.readFloat())
    }
}