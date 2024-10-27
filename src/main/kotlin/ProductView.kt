package voidpointer.mc.tests

data class ProductView(override val id: Int, val name: String, val price: Double) : AbstractProduct() {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return AbstractProduct.hashCode()
    }
}
