package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagDeserializer
import java.io.DataInput
import java.io.DataOutput

class NBTCompound : NBTTag {

    private val tags = HashMap<String, NBTTag>()

    fun put(name: String, tag: NBTTag) {
        tags[name] = tag
    }

    operator fun set(name: String, tag: NBTTag) = tags.set(name, tag)
    operator fun get(name: String) = tags[name]

    override fun write(output: DataOutput) {

    }

    override fun toString() = "{${tags.entries.joinToString(separator = ",") { "${it.key}:${it.value}" }}}"

    override fun deepClone() = NBTCompound().also { compound -> tags.forEach { compound.put(it.key, it.value.deepClone()) } }

    internal object Deserializer : TagDeserializer<NBTCompound>() {
        override fun deserialize(name: String, input: DataInput): NBTCompound {
            val compound = NBTCompound()
            var tagId: Byte = input.readByte()

            while (tagId != 0.toByte()) {
                val tagName = input.readUTF()
                compound.put(tagName, NBTTag.getTypeById(tagId).deserializer.deserialize(tagName, input))
                tagId = input.readByte() // Reading next tag id
            }

            return compound
        }
    }
}