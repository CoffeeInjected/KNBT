package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagLong(name: String, private val value: Long) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeLong(value)
    }

    override fun toString() = "$name:${value}L"

    internal object Parser : TagParser<NBTTagLong>() {
        override fun parse(name: String, input: DataInputStream) = NBTTagLong(name, input.readLong())
    }
}