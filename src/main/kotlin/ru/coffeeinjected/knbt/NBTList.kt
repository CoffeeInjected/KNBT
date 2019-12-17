package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import ru.coffeeinjected.knbt.internal.TagRegistry
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTList(name: String, val tagId: Byte) : NBTTag(name) {

    private val tags = ArrayList<NBTTag>()

    fun add(tag: NBTTag) {
        tags += tag
    }

    override fun write(output: DataOutputStream) {
        output.writeByte(tagId.toInt())
        output.writeInt(tags.size)

        for (tag in tags) {
            tag.write(output)
        }
    }

    override fun valueToString() = "[${tags.joinToString(separator = ",")}]"

    internal object Parser : TagParser<NBTList>() {
        override fun parse(name: String, input: DataInputStream): NBTList {
            val tagId: Byte = input.readByte()
            val list = NBTList(name, tagId)
            val size = input.readInt()
            val parser = TagRegistry.getTagParser(tagId)

            repeat(size) {
                list.add(parser.parse("", input))
            }

            return list
        }
    }
}