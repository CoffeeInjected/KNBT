package ru.coffeeinjected.knbt

import java.io.DataInputStream
import java.io.DataOutputStream

abstract class NBTTag(val name: String) {

    abstract fun write(output: DataOutputStream)

    abstract override fun toString(): String

    companion object {
        fun readTag(input: DataInputStream): NBTTag? {
            return null
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