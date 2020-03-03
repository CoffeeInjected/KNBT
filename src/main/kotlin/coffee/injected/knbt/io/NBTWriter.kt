package coffee.injected.knbt.io

import coffee.injected.knbt.NBTTag
import java.io.Closeable
import java.io.DataOutputStream
import java.io.OutputStream
import java.util.zip.GZIPOutputStream

class NBTWriter(private val stream: OutputStream, private val compressed: Boolean = false) : Closeable {

    fun write(name: String, tag: NBTTag) {
        val stream = if (compressed) DataOutputStream(GZIPOutputStream(stream)) else DataOutputStream(stream)
        NBTTag.writeTag(stream, name, tag)
        stream.close()
    }

    fun write(tag: NBTTag) = write("", tag)

    override fun close() = stream.close()
}