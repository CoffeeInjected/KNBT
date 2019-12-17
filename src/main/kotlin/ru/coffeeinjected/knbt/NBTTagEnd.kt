package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagEnd private constructor() : NBTTag("END") {

    override fun write(output: DataOutputStream) {
        throw UnsupportedOperationException("Can't serialize NBTTagEnd separately")
    }

    override fun toString() = name

    internal object Parser : TagParser<NBTTagEnd>() {
        override fun parse(name: String, input: DataInputStream) = throw UnsupportedOperationException("You can't just create NBTTagEnd")
    }
}