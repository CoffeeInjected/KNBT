package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTFloat(name: String, private val value: Float) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeFloat(value)
    }

    override fun valueToString() = "${value}F"

    override fun deepClone() = NBTFloat(name, value)

    internal object Parser : TagParser<NBTFloat>() {
        override fun parse(name: String, input: DataInputStream) = NBTFloat(name, input.readFloat())
    }
}