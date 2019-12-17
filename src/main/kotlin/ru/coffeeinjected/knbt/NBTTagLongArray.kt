package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagLongArray(name: String, private val value: LongArray) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeInt(value.size)
        value.forEach { output.writeLong(it) }
    }

    override fun toString() = "$name:[I;${value.joinToString(separator = ",")}]"

    internal object Parser : TagParser<NBTTagLongArray>() {
        override fun parse(name: String, input: DataInputStream): NBTTagLongArray {
            return NBTTagLongArray(name, LongArray(input.readInt()) { input.readLong() })
        }
    }
}