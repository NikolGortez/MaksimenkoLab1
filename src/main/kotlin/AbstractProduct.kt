package voidpointer.mc.tests

import kotlinx.serialization.Serializable

@Serializable
abstract class AbstractProduct {
    abstract var id: Int?
    abstract var name: String
    abstract var description: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is AbstractProduct) return id == other.id
        return false
    }

    override fun hashCode(): Int {
        return id!!
    }
}
