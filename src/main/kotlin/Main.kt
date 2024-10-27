package voidpointer.mc.tests

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main() {
    testJson()
}

fun testJson() {
    val product = Product(1, "Name", "Description", 1.0, 1, 1, 1)

    println(Json.encodeToString(product))
}
