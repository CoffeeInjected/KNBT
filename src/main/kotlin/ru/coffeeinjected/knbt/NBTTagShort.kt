package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagShort(name: String, private val value: Short) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeShort(value.toInt())
    }

    override fun toString() = "$name:${value}S"

    internal object Parser : TagParser<NBTTagShort>() {
        override fun parse(name: String, input: DataInputStream) = NBTTagShort(name, input.readShort())
    }
}