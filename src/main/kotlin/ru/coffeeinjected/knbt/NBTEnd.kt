package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTEnd private constructor() : NBTTag("END") {

    override fun write(output: DataOutputStream) {
        throw UnsupportedOperationException("Can't serialize NBTTagEnd separately")
    }

    override fun valueToString() = throw UnsupportedOperationException("Can't evaluate tag end")

    override fun deepClone() = throw UnsupportedOperationException("Can't clone tag end")

    internal object Parser : TagParser<NBTEnd>() {
        override fun parse(name: String, input: DataInputStream) = throw UnsupportedOperationException("You can't just create NBTTagEnd")
    }
}