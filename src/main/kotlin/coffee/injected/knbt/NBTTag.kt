package coffee.injected.knbt

import coffee.injected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

interface NBTTag<out T : NBTTag<T>> {

    fun write(output: DataOutput)

    fun deepClone(): T

    fun getTypeId(): Byte

    override operator fun equals(other: Any?): Boolean
    override fun hashCode(): Int
    override fun toString(): String

    private inline fun <reified T : NBTTag<T>> castTag(): T {
        return if (this is T) this else
            throw IllegalArgumentException("${this.javaClass.simpleName} cannot be converted to ${T::class.java.simpleName}")
    }

    fun asNBTByte() = castTag<NBTByte>()
    fun asNBTByteArray() = castTag<NBTByteArray>()
    fun asNBTCompound() = castTag<NBTCompound>()
    fun asNBTDouble() = castTag<NBTDouble>()
    fun asNBTFloat() = castTag<NBTFloat>()
    fun asNBTInt() = castTag<NBTInt>()
    fun asNBTIntArray() = castTag<NBTIntArray>()
    fun asNBTLong() = castTag<NBTLong>()
    fun asNBTLongArray() = castTag<NBTLongArray>()
    fun asNBTShort() = castTag<NBTShort>()
    fun asNBTString() = castTag<NBTString>()

    fun isByte(): Boolean = this is NBTByte
    fun isByteArray(): Boolean = this is NBTByteArray
    fun isCompound(): Boolean = this is NBTCompound
    fun isDouble(): Boolean = this is NBTDouble
    fun isFloat(): Boolean = this is NBTFloat
    fun isInt(): Boolean = this is NBTInt
    fun isIntArray(): Boolean = this is NBTIntArray
    fun isList(): Boolean = this is NBTList<*>
    fun isLong(): Boolean = this is NBTLong
    fun isLongArray(): Boolean = this is NBTLongArray
    fun isShort(): Boolean = this is NBTShort
    fun isString(): Boolean = this is NBTString

    companion object {

        /**
         * Main read method for any tag
         */
        fun readTag(input: DataInput): Pair<String, NBTTag<*>> {
            val tagId = input.readByte()
            val tagName = input.readUTF()
            val deserializer = getTypeById(tagId).deserializer

            return Pair(tagName, deserializer.deserialize(tagName, input))
        }

        /**
         * Main write method for any tag
         */
        fun writeTag(output: DataOutput, name: String, tag: NBTTag<*>) {
            output.writeByte(tag.getTypeId().toInt())
            output.writeUTF(name)
            tag.write(output)
        }

        fun getTypeById(id: Byte) = Type.values()[id.toInt()]
    }

    enum class Type(val deserializer: TagDeserializer<*>) {

        /* 00 */ END(NBTEnd.Deserializer),
        /* 01 */ BYTE(NBTByte.Deserializer),
        /* 02 */ SHORT(NBTShort.Deserializer),
        /* 03 */ INT(NBTInt.Deserializer),
        /* 04 */ LONG(NBTLong.Deserializer),
        /* 05 */ FLOAT(NBTFloat.Deserializer),
        /* 06 */ DOUBLE(NBTDouble.Deserializer),
        /* 07 */ BYTE_ARRAY(NBTByteArray.Deserializer),
        /* 08 */ STRING(NBTString.Deserializer),
        /* 09 */ LIST(NBTList.Deserializer),
        /* 0A */ COMPOUND(NBTCompound.Deserializer),
        /* 0B */ INT_ARRAY(NBTIntArray.Deserializer),
        /* 0C */ LONG_ARRAY(NBTLongArray.Deserializer);

        val typeId: Byte
            get() = ordinal.toByte()

    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : NBTTag<T>> NBTTag<*>.asNBTList() = this as NBTList<T>