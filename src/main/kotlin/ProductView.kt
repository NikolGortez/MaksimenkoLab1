package voidpointer.mc.tests

data class ProductView(
    override var id: Int? = null,
    var price: Double
) : AbstractProduct() {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return AbstractProduct.hashCode()
    }
}
