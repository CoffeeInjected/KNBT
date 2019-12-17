package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTIntArray(name: String, private val value: IntArray) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeInt(value.size)
        value.forEach { output.writeInt(it) }
    }

    override fun valueToString() = "[I;${value.joinToString(separator = ",")}]"

    override fun deepClone() = NBTIntArray(name, value.copyOf())

    internal object Parser : TagParser<NBTIntArray>() {
        override fun parse(name: String, input: DataInputStream): NBTIntArray {
            return NBTIntArray(name, IntArray(input.readInt()) { input.readInt() })
        }
    }
}