package coffee.injected.knbt

import coffee.injected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTList<T : NBTTag<T>>(val tagType: NBTTag.Type) : NBTTag<NBTList<T>> {

    private val tags = ArrayList<T>()

    val size: Int
        get() = tags.size

    fun add(tag: T) = tags.add(tag)
    fun remove(tag: T) = tags.remove(tag)
    fun removeAt(index: Int) = tags.removeAt(index)
    operator fun get(index: Int): T = tags[index]
    operator fun set(index: Int, value: T) = tags.set(index, value)
    operator fun contains(tag: T): Boolean = tag in tags
    operator fun iterator(): Iterator<T> = tags.iterator()

    operator fun plusAssign(tag: T) {
        tags += tag
    }

    operator fun minusAssign(tag: T) {
        tags -= tag
    }

    override fun write(output: DataOutput) {
        output.writeByte(tagType.typeId.toInt())
        output.writeInt(tags.size)

        for (tag in tags) {
            tag.write(output)
        }
    }

    override fun deepClone() = NBTList<T>(tagType).also { list -> tags.forEach { list.add(it.deepClone()) } }

    override fun getTypeId(): Byte = 9

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTList<*>) return false

        return other.tags == tags
    }

    override fun hashCode() = tags.hashCode()

    override fun toString() = tags.joinToString(separator = ",", prefix = "[", postfix = "]")

    internal object Deserializer : TagDeserializer<NBTList<NBTTag<*>>>() {
        @Suppress("UNCHECKED_CAST")
        override fun deserialize(name: String, input: DataInput): NBTList<NBTTag<*>> {
            val tagId: Byte = input.readByte()
            val list: NBTList<NBTTag<*>> = NBTList::class.java.constructors[0].newInstance(tagId) as NBTList<NBTTag<*>>
            val size = input.readInt()
            val deserializer = NBTTag.getTypeById(tagId).deserializer

            repeat(size) {
                list.add(deserializer.deserialize("", input))
            }

            return list
        }
    }
}