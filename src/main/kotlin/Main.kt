package voidpointer.mc.tests

fun main() {
    val product = ProductModel(1, "Full name", "Description", 1.0, 1, 1)
    println(product)
    println(product.toStringShort())
}
