package ru.coffeeinjected.knbt.io

import ru.coffeeinjected.knbt.NBTCompound
import ru.coffeeinjected.knbt.NBTTag
import java.io.DataInputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream

class NBTReader(val stream: InputStream, val compressed: Boolean = false) {

    fun read(): Pair<String, NBTTag> {
        val stream = if (compressed) DataInputStream(GZIPInputStream(stream)) else DataInputStream(stream)
        return NBTTag.readTag(stream)
    }

    fun readNBT(): NBTTag = read().second

    fun readCompound(): NBTCompound = readNBT().asNBTCompound()
}