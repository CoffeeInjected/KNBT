package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTLongArray(name: String, private val value: LongArray) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeInt(value.size)
        value.forEach { output.writeLong(it) }
    }

    override fun valueToString() = "[L;${value.joinToString(separator = ",")}]"

    override fun deepClone() = NBTLongArray(name, value.copyOf())

    internal object Parser : TagParser<NBTLongArray>() {
        override fun parse(name: String, input: DataInputStream): NBTLongArray {
            return NBTLongArray(name, LongArray(input.readInt()) { input.readLong() })
        }
    }
}