package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTList(val tagId: Byte) : NBTTag {

    private val tags = ArrayList<NBTTag>()

    fun add(tag: NBTTag) {
        tags += tag
    }

    override fun write(output: DataOutput) {
        output.writeByte(tagId.toInt())
        output.writeInt(tags.size)

        for (tag in tags) {
            tag.write(output)
        }
    }

    override fun toString() = "[${tags.joinToString(separator = ",")}]"

    override fun deepClone() = NBTList(tagId).also { list -> tags.forEach { list.add(it.deepClone()) } }

    internal object Deserializer : TagDeserializer<NBTList>() {
        override fun deserialize(name: String, input: DataInput): NBTList {
            val tagId: Byte = input.readByte()
            val list = NBTList(tagId)
            val size = input.readInt()
            val deserializer = NBTTag.getTypeById(tagId).deserializer

            repeat(size) {
                list.add(deserializer.deserialize("", input))
            }

            return list
        }
    }
}