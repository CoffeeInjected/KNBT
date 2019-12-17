package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagIntArray(name: String, private val value: IntArray) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeInt(value.size)
        value.forEach { output.writeInt(it) }
    }

    override fun toString() = "$name:[I;${value.joinToString(separator = ",")}]"

    internal object Parser : TagParser<NBTTagIntArray>() {
        override fun parse(name: String, input: DataInputStream): NBTTagIntArray {
            return NBTTagIntArray(name, IntArray(input.readInt()) { input.readInt() })
        }
    }
}