package ru.coffeeinjected.knbt.io

import ru.coffeeinjected.knbt.NBTTag
import ru.coffeeinjected.knbt.internal.TagRegistry
import java.io.DataInputStream
import java.io.InputStream

class NBTInputStream(val stream: InputStream) {

    fun parse(): NBTTag {
        val data = DataInputStream(stream)
        return TagRegistry.getParser(data.readByte()).parse(data.readUTF(), data)
    }
}