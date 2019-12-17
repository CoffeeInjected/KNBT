package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTLong(name: String, private val value: Long) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeLong(value)
    }

    override fun valueToString() = "${value}L"

    override fun deepClone() = NBTLong(name, value)

    internal object Parser : TagParser<NBTLong>() {
        override fun parse(name: String, input: DataInputStream) = NBTLong(name, input.readLong())
    }
}