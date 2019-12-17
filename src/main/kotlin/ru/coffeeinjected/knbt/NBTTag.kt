package ru.coffeeinjected.knbt

import ru.coffeeinjected.knbt.internal.TagRegistry
import java.io.DataInputStream
import java.io.DataOutputStream

abstract class NBTTag(val name: String) {

    abstract fun write(output: DataOutputStream)

    override fun toString(): String = if (name.isEmpty()) valueToString() else "$name:${valueToString()}"

    abstract fun valueToString(): String

    abstract fun deepClone(): NBTTag

    companion object {
        /**
         * Main parser method for any tag
         */
        fun readTag(input: DataInputStream): NBTTag {
            val tagId = input.readByte()
            val tagName = input.readUTF()
            val parser = TagRegistry.getTagParser(tagId)

            return parser.parse(tagName, input)
        }
    }

    class Type private constructor() {
        companion object {
            const val END: Byte = 0
            const val BYTE: Byte = 1
            const val SHORT: Byte = 2
            const val INT: Byte = 3
            const val LONG: Byte = 4
            const val FLOAT: Byte = 5
            const val DOUBLE: Byte = 6
            const val BYTE_ARRAY: Byte = 7
            const val STRING: Byte = 8
            const val LIST: Byte = 9
            const val COMPOUND: Byte = 10
            const val INT_ARRAY: Byte = 11
            const val LONG_ARRAY: Byte = 12
        }
    }
}