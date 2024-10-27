import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import voidpointer.mc.tests.ProductModel
import voidpointer.mc.tests.ProductView
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductModelTest {
    @Test
    fun testJson() {
        val product = ProductModel(1, "name", "description", 2.0, 3, 4)
        val jsonProduct = Json.encodeToString(product)
        val decoded = ProductModel.fromJson(jsonProduct)
        assertEquals(product, decoded)
    }

    @Test
    fun testEquality() {
        val product: Any = ProductModel(1, "name", "description", 2.0, 3, 4)
        val productView: Any = ProductView(1, "name", 2.0)
        assertEquals(product, productView)
    }
}
