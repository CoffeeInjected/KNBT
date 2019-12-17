package ru.coffeeinjected.knbt.internal

import ru.coffeeinjected.knbt.*

object TagRegistry {

    private val tagsByIds = HashMap<Byte, TagParser<*>>()

    init {
        registerTag(NBTTag.Type.END, NBTEnd.Parser)
        registerTag(NBTTag.Type.BYTE, NBTByte.Parser)
        registerTag(NBTTag.Type.SHORT, NBTShort.Parser)
        registerTag(NBTTag.Type.INT, NBTInt.Parser)
        registerTag(NBTTag.Type.LONG, NBTLong.Parser)
        registerTag(NBTTag.Type.FLOAT, NBTFloat.Parser)
        registerTag(NBTTag.Type.DOUBLE, NBTDouble.Parser)
        registerTag(NBTTag.Type.BYTE_ARRAY, NBTByteArray.Parser)
        registerTag(NBTTag.Type.STRING, NBTString.Parser)
        registerTag(NBTTag.Type.LIST, NBTList.Parser)
        registerTag(NBTTag.Type.COMPOUND, NBTCompound.Parser)
        registerTag(NBTTag.Type.INT_ARRAY, NBTIntArray.Parser)
        registerTag(NBTTag.Type.LONG_ARRAY, NBTLongArray.Parser)
    }

    fun registerTag(id: Byte, parser: TagParser<*>) {
        tagsByIds[id] = parser
    }

    fun unregisterTag(id: Byte) {
        tagsByIds.remove(id)
    }

    fun getTagParser(id: Byte): TagParser<*> {
        return tagsByIds[id]!!
    }
}