package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagParser
import java.io.DataInputStream
import java.io.DataOutputStream

class NBTTagList(name: String) : NBTTag(name) {

    override fun write(output: DataOutputStream) {
        TODO()
    }

    override fun toString() = TODO()

    internal object Parser : TagParser<NBTTagList>() {
        override fun parse(name: String, input: DataInputStream): NBTTagList {
            TODO()
        }
    }
}