package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTByteArray(name: String, val value: ByteArray) : NBTTag(name) {

    val size: Int
        get() = value.size

    operator fun get(index: Int) = value[index]
    operator fun set(index: Int, value: Byte) = this.value.set(index, value)
    operator fun iterator(): ByteIterator = value.iterator()

    override fun write(output: DataOutputStream) {
        output.writeInt(value.size)
        output.write(value)
    }

    override fun valueToString() = "[B;${value.joinToString(separator = ",")}]"

    override fun deepClone() = NBTByteArray(name, value.copyOf())

    internal object Parser : TagParser<NBTByteArray>() {
        override fun parse(name: String, input: DataInputStream): NBTByteArray {
            val arr = ByteArray(input.readInt())
            input.read(arr)
            return NBTByteArray(name, arr)
        }
    }
}