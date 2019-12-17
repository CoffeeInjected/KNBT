package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagByteArray(name: String, private val value: ByteArray) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeInt(value.size)
        output.write(value)
    }

    override fun toString() = "$name:[B;${value.joinToString(separator = ",")}]"

    internal object Parser : TagParser<NBTTagByteArray>() {
        override fun parse(name: String, input: DataInputStream): NBTTagByteArray {
            val arr = ByteArray(input.readInt())
            input.read(arr)
            return NBTTagByteArray(name, arr)
        }
    }
}