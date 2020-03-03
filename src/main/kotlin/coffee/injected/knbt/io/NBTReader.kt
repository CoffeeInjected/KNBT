package coffee.injected.knbt.io

import coffee.injected.knbt.NBTCompound
import coffee.injected.knbt.NBTTag
import java.io.Closeable
import java.io.DataInputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream

class NBTReader(stream: InputStream, compressed: Boolean = false) : Closeable {

    private val stream = if (compressed) DataInputStream(GZIPInputStream(stream)) else DataInputStream(stream)

    fun read(): Pair<String, NBTTag> = NBTTag.readTag(stream)
    fun readNBT(): NBTTag = read().second
    fun readCompound(): NBTCompound = readNBT().asNBTCompound()

    override fun close() = stream.close()
}