package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagDouble(name: String, private val value: Double) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeDouble(value)
    }

    override fun toString() = "$name:${value}F"

    internal object Parser : TagParser<NBTTagDouble>() {
        override fun parse(name: String, input: DataInputStream) = NBTTagDouble(name, input.readDouble())
    }
}