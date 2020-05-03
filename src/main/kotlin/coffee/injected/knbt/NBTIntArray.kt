package coffee.injected.knbt

import coffee.injected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTIntArray(val value: IntArray) : NBTTag<NBTIntArray> {

    val size: Int
        get() = value.size

    operator fun get(index: Int) = value[index]
    operator fun set(index: Int, value: Int) = this.value.set(index, value)
    operator fun iterator(): IntIterator = value.iterator()

    override fun write(output: DataOutput) {
        output.writeInt(value.size)
        value.forEach(output::writeInt)
    }

    override fun deepClone() = NBTIntArray(value.copyOf())

    override fun getTypeId(): Byte = 11

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTIntArray) return false

        return other.value.contentEquals(value)
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = "[I;${value.joinToString(separator = ",")}]"

    internal object Deserializer : TagDeserializer<NBTIntArray>() {
        override fun deserialize(name: String, input: DataInput): NBTIntArray {
            return NBTIntArray(IntArray(input.readInt()) {
                input.readInt()
            })
        }
    }
}