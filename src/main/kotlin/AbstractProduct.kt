package voidpointer.mc.tests

import kotlinx.serialization.Serializable

@Serializable
abstract class AbstractProduct : Product {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is Product) return id == other.id
        return false
    }

    override fun hashCode(): Int {
        return id
    }
}
