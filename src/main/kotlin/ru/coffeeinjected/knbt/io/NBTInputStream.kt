package ru.coffeeinjected.knbt.io

import ru.coffeeinjected.knbt.NBTTag
import java.io.DataInputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream

class NBTInputStream(val stream: InputStream, val compressed: Boolean = false) {

    fun parse(): NBTTag {
        val data = if (compressed) DataInputStream(GZIPInputStream(stream)) else DataInputStream(stream)
        return NBTTag.readTag(data)
    }
}