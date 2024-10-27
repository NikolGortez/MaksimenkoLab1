package voidpointer.mc.tests

fun main() {
    val product = ProductModel(1, "Full name", "Description", 1.0, 1, 1)
    val view = ProductView(1, "a", 1.0)
    println(product)
    println(view)
}
