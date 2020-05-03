package coffee.injected.knbt

import coffee.injected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTEnd private constructor() : NBTTag<NBTEnd> {

    override fun write(output: DataOutput) {
        throw UnsupportedOperationException("Can't serialize NBTTagEnd separately")
    }

    override fun deepClone() = throw UnsupportedOperationException("Can't clone tag end")

    override fun getTypeId(): Byte = 0

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTEnd) return false

        return true
    }

    override fun hashCode() = 0

    override fun toString() = throw UnsupportedOperationException("Can't evaluate tag end")

    internal object Deserializer : TagDeserializer<NBTEnd>() {
        override fun deserialize(name: String, input: DataInput) = throw UnsupportedOperationException("You can't just create NBTTagEnd")
    }
}