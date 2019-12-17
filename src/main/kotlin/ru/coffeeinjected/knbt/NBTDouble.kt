package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTDouble(name: String, private val value: Double) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeDouble(value)
    }

    override fun valueToString() = "${value}D"

    override fun deepClone() = NBTDouble(name, value)

    internal object Parser : TagParser<NBTDouble>() {
        override fun parse(name: String, input: DataInputStream) = NBTDouble(name, input.readDouble())
    }
}