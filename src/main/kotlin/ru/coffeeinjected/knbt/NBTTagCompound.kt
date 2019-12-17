package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagCompound(name: String) : NBTTag(name) {

    private val tags = HashMap<String, NBTTag>()

    fun get(name: String): NBTTag? = tags[name]

    override fun write(output: DataOutputStream) {

    }

    override fun toString(): String {
        return "{${tags.entries.joinToString(separator = ",") { "${it.key}:${it.value}" }}}"
    }

    internal object Parser : TagParser<NBTTagCompound>() {
        override fun parse(name: String, input: DataInputStream): NBTTagCompound {
            TODO()
        }
    }
}