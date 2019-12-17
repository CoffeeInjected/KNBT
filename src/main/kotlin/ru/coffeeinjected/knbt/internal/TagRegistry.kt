package ru.coffeeinjected.knbt.internal

import ru.coffeeinjected.knbt.*

object TagRegistry {

    private val tagsByIds = HashMap<Byte, TagParser<*>>()

    init {
        registerTag(NBTTag.Type.END, NBTTagEnd.Parser)
        registerTag(NBTTag.Type.BYTE, NBTTagByte.Parser)
        registerTag(NBTTag.Type.SHORT, NBTTagShort.Parser)
        registerTag(NBTTag.Type.INT, NBTTagInt.Parser)
        registerTag(NBTTag.Type.LONG, NBTTagLong.Parser)
        registerTag(NBTTag.Type.FLOAT, NBTTagFloat.Parser)
        registerTag(NBTTag.Type.DOUBLE, NBTTagDouble.Parser)
        registerTag(NBTTag.Type.BYTE_ARRAY, NBTTagByteArray.Parser)
        registerTag(NBTTag.Type.STRING, NBTTagString.Parser)
        registerTag(NBTTag.Type.LIST, NBTTagList.Parser)
        registerTag(NBTTag.Type.COMPOUND, NBTTagCompound.Parser)
        registerTag(NBTTag.Type.INT_ARRAY, NBTTagIntArray.Parser)
        registerTag(NBTTag.Type.LONG_ARRAY, NBTTagLongArray.Parser)
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