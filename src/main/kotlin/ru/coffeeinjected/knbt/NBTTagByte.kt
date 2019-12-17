package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagByte(name: String, private val value: Byte) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeByte(value.toInt())
    }

    override fun toString() = "$name:${value}B"

    internal object Parser : TagParser<NBTTagByte>() {
        override fun parse(name: String, input: DataInputStream) = NBTTagByte(name, input.readByte())
    }
}