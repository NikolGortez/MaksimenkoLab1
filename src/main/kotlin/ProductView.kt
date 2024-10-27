package voidpointer.mc.tests

data class ProductView(val id: Int, val name: String, val price: Double) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is ProductModel) return id == other.id
        if (other is ProductView) return id == other.id
        return false
    }

    override fun hashCode(): Int {
        return id
    }
}
