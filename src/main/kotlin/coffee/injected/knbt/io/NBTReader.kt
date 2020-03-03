package coffee.injected.knbt.io

import coffee.injected.knbt.NBTCompound
import coffee.injected.knbt.NBTTag
import java.io.Closeable
import java.io.DataInputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream

class NBTReader(private val stream: InputStream, private val compressed: Boolean = false) : Closeable {

    fun read(): Pair<String, NBTTag> {
        val stream = if (compressed) DataInputStream(GZIPInputStream(stream)) else DataInputStream(stream)
        return NBTTag.readTag(stream)
    }

    fun readNBT(): NBTTag = read().second

    fun readCompound(): NBTCompound = readNBT().asNBTCompound()

    override fun close() = stream.close()
}