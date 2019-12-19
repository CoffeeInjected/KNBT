package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTCompound : NBTTag {

    private val tags = HashMap<String, NBTTag>()

    fun getByte(name: String): Byte = this[name]!!.asNBTByte().value
    fun getByteArray(name: String): ByteArray = this[name]!!.asNBTByteArray().value
    fun getCompound(name: String): NBTCompound = this[name]!!.asNBTCompound()
    fun getDouble(name: String): Double = this[name]!!.asNBTDouble().value
    fun getFloat(name: String): Float = this[name]!!.asNBTFloat().value
    fun getInt(name: String): Int = this[name]!!.asNBTInt().value
    fun getIntArray(name: String): IntArray = this[name]!!.asNBTIntArray().value
    fun getList(name: String): NBTList = this[name]!!.asNBTList()
    fun getLong(name: String): Long = this[name]!!.asNBTLong().value
    fun getLongArray(name: String): LongArray = this[name]!!.asNBTLongArray().value
    fun getShort(name: String): Short = this[name]!!.asNBTShort().value
    fun getString(name: String): String = this[name]!!.asNBTString().value

    fun setByte(name: String, value: Byte) = set(name, NBTByte(value))
    fun setByteArray(name: String, value: ByteArray) = set(name, NBTByteArray(value))
    fun setDouble(name: String, value: Double) = set(name, NBTDouble(value))
    fun setFloat(name: String, value: Float) = set(name, NBTFloat(value))
    fun setInt(name: String, value: Int) = set(name, NBTInt(value))
    fun setIntArray(name: String, value: IntArray) = set(name, NBTIntArray(value))
    fun setLong(name: String, value: Long) = set(name, NBTLong(value))
    fun setLongArray(name: String, value: LongArray) = set(name, NBTLongArray(value))
    fun setShort(name: String, value: Short) = set(name, NBTShort(value))
    fun setString(name: String, value: String) = set(name, NBTString(value))

    operator fun set(name: String, tag: NBTTag) = tags.set(name, tag)
    operator fun get(name: String) = tags[name]

    fun put(name: String, tag: NBTTag) {
        tags[name] = tag
    }

    override fun write(output: DataOutput) {
        for ((name, tag) in tags.entries) {
            NBTTag.writeTag(output, name, tag)
        }
        output.writeByte(0) // NBTEnd
    }

    override fun deepClone() = NBTCompound().also { compound -> tags.forEach { compound.put(it.key, it.value.deepClone()) } }

    override fun getTypeId() = 10.toByte()

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is NBTCompound) return false

        return other.tags == tags
    }

    override fun hashCode() = tags.hashCode()

    override fun toString() = "{${tags.entries.joinToString(separator = ",") { "\"${it.key}\":${it.value}" }}}"

    internal object Deserializer : TagDeserializer<NBTCompound>() {
        override fun deserialize(name: String, input: DataInput): NBTCompound {
            val compound = NBTCompound()
            var tagId: Byte = input.readByte()

            while (tagId != 0.toByte()) {
                val tagName = input.readUTF()

                compound.put(tagName, NBTTag.getTypeById(tagId).deserializer.deserialize(tagName, input))

                tagId = input.readByte()
            }

            return compound
        }
    }
}