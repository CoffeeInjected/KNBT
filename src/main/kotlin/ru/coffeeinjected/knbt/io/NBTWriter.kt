package ru.coffeeinjected.knbt.io

import ru.coffeeinjected.knbt.NBTTag
import java.io.DataOutputStream
import java.io.OutputStream
import java.util.zip.GZIPOutputStream

class NBTWriter(private val stream: OutputStream, private val compressed: Boolean = false) {

    fun write(name: String, tag: NBTTag) {
        val stream = if (compressed) DataOutputStream(GZIPOutputStream(stream)) else DataOutputStream(stream)
        NBTTag.writeTag(stream, name, tag)
        stream.close()
    }

    fun writeNBT(tag: NBTTag) = write("", tag)
}