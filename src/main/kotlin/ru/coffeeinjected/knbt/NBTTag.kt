package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

interface NBTTag {

    fun write(output: DataOutput)

    fun deepClone(): NBTTag

    companion object {

        /**
         * Main read method for any tag
         */
        fun readTag(input: DataInput): Pair<String, NBTTag> {
            val tagId = input.readByte()
            val tagName = input.readUTF()
            val deserializer = getTypeById(tagId).deserializer

            return Pair(tagName, deserializer.deserialize(tagName, input))
        }

        fun getTypeById(id: Byte) = Type.values()[id.toInt()]
    }

    enum class Type(val deserializer: TagDeserializer<*>) {

        END(NBTEnd.Deserializer),
        BYTE(NBTByte.Deserializer),
        SHORT(NBTShort.Deserializer),
        INT(NBTInt.Deserializer),
        LONG(NBTLong.Deserializer),
        FLOAT(NBTFloat.Deserializer),
        DOUBLE(NBTDouble.Deserializer),
        BYTE_ARRAY(NBTByteArray.Deserializer),
        STRING(NBTString.Deserializer),
        LIST(NBTList.Deserializer),
        COMPOUND(NBTCompound.Deserializer),
        INT_ARRAY(NBTIntArray.Deserializer),
        LONG_ARRAY(NBTLongArray.Deserializer)

    }
}