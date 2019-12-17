package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTString(name: String, private val value: String) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        output.writeUTF(value)
    }

    override fun valueToString() = "\"${value.replace("\"", "\\\"")}\""

    internal object Parser : TagParser<NBTString>() {
        override fun parse(name: String, input: DataInputStream) = NBTString(name, input.readUTF())
    }
}