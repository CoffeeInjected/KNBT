package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagFloat(name: String, private val value: Float) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeFloat(value)
    }

    override fun toString() = "$name:${value}F"

    internal object Parser : TagParser<NBTTagFloat>() {
        override fun parse(name: String, input: DataInputStream) = NBTTagFloat(name, input.readFloat())
    }
}