package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTInt(name: String, private val value: Int) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeInt(value)
    }

    override fun toString() = "$name:${value}"

    internal object Parser : TagParser<NBTInt>() {
        override fun parse(name: String, input: DataInputStream) = NBTInt(name, input.readInt())
    }
}