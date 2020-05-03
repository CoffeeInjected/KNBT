package coffee.injected.knbt

import coffee.injected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTLongArray(val value: LongArray) : NBTTag<NBTLongArray> {

    val size: Int
        get() = value.size

    operator fun get(index: Int) = value[index]
    operator fun set(index: Int, value: Long) = this.value.set(index, value)
    operator fun iterator(): LongIterator = value.iterator()

    override fun write(output: DataOutput) {
        output.writeInt(value.size)
        value.forEach(output::writeLong)
    }

    override fun deepClone() = NBTLongArray(value.copyOf())

    override fun getTypeId(): Byte = 12

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTLongArray) return false

        return other.value.contentEquals(value)
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "[L;${value.joinToString(separator = ",")}]"

    internal object Deserializer : TagDeserializer<NBTLongArray>() {
        override fun deserialize(name: String, input: DataInput): NBTLongArray {
            return NBTLongArray(LongArray(input.readInt()) { input.readLong() })
        }
    }
}