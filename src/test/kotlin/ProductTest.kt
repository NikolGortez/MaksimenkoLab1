import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import voidpointer.mc.tests.Product
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductTest {
    @Test
    fun testJson() {
        val product = Product(1, "name", "description", 2.0, 3, 4, 5)
        val jsonProduct = Json.encodeToString(product)
        val decoded = Product.fromJson(jsonProduct)
        assertEquals(product, decoded)
    }
}
