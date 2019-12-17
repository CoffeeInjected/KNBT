package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTByteArray(name: String, private val value: ByteArray) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeInt(value.size)
        output.write(value)
    }

    override fun toString() = "$name:[B;${value.joinToString(separator = ",")}]"

    internal object Parser : TagParser<NBTByteArray>() {
        override fun parse(name: String, input: DataInputStream): NBTByteArray {
            val arr = ByteArray(input.readInt())
            input.read(arr)
            return NBTByteArray(name, arr)
        }
    }
}