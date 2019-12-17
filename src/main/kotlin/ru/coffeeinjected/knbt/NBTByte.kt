package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTByte(name: String, private val value: Byte) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeByte(value.toInt())
    }

    override fun valueToString() = "${value}B"

    internal object Parser : TagParser<NBTByte>() {
        override fun parse(name: String, input: DataInputStream) = NBTByte(name, input.readByte())
    }
}