package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagString(name: String, private val value: String) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeUTF(value)
    }

    override fun toString() = "$name:\"${value.replace("\"", "\\\"")}\""

    internal object Parser : TagParser<NBTTagString>() {
        override fun parse(name: String, input: DataInputStream) = NBTTagString(name, input.readUTF())
    }
}