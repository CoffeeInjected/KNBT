package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTShort(name: String, private val value: Short) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeShort(value.toInt())
    }

    override fun valueToString() = "${value}S"

    override fun deepClone() = NBTShort(name, value)

    internal object Parser : TagParser<NBTShort>() {
        override fun parse(name: String, input: DataInputStream) = NBTShort(name, input.readShort())
    }
}