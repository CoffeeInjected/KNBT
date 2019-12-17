package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import ru.coffeeinjected.knbt.internal.TagRegistry
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTCompound(name: String) : NBTTag(name) {

    private val tags = HashMap<String, NBTTag>()

    fun put(name: String, tag: NBTTag) {
        tags[name] = tag
    }

    fun get(name: String): NBTTag? = tags[name]

    override fun write(output: DataOutputStream) {

    }

    override fun valueToString() = "{${tags.entries.joinToString(separator = ",") { it.value.toString() }}}"

    internal object Parser : TagParser<NBTCompound>() {
        override fun parse(name: String, input: DataInputStream): NBTCompound {
            val compound = NBTCompound(name)
            var tagId: Byte = input.readByte()

            while (tagId != 0.toByte()) {
                val tagName = input.readUTF()
                compound.put(tagName, TagRegistry.getTagParser(tagId).parse(tagName, input))
                tagId = input.readByte() // Reading next tag id
            }

            return compound
        }
    }
}