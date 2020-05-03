package coffee.injected.knbt.internal

import coffee.injected.knbt.NBTTag
import java.io.DataInput

abstract class TagDeserializer<T : NBTTag<T>> {

    abstract fun deserialize(name: String, input: DataInput): T
}