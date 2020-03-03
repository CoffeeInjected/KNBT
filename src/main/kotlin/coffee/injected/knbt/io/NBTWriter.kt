package coffee.injected.knbt.io

import coffee.injected.knbt.NBTTag
import java.io.Closeable
import java.io.DataOutputStream
import java.io.OutputStream
import java.util.zip.GZIPOutputStream

class NBTWriter(stream: OutputStream, compressed: Boolean = false) : Closeable {

    private val stream = if (compressed) DataOutputStream(GZIPOutputStream(stream)) else DataOutputStream(stream)

    fun write(name: String, tag: NBTTag) = NBTTag.writeTag(stream, name, tag)
    fun write(tag: NBTTag) = write("", tag)

    override fun close() = stream.close()
}