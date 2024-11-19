package voidpointer.mc.tests

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ProductModel(
    override var id: Int? = null,
    override var name: String,
    override var description: String,
    var price: Double,
    var stockBalance: Int,
    var reserveBalance: Int,
) : AbstractProduct() {

    companion object {
        fun fromJson(json: String): ProductModel {
            return Json.decodeFromString<ProductModel>(json)
        }
    }

    /**
     * В целях читаемости кода валидация сделана через проверку полей в единственном конструкторе
     * класса.
     *
     * Статическими методами можно было бы достигнуть того же эффекта примером, приведённым ниже.
     *
     * ```java
     * companion object {
     *     fun validateId(id: Int) {
     *         require(id >= 0) { "Id must be positive." }
     *     }
     * }
     * ```
     */
    init {
        /* сказано убрать повтор кода - повтор кода убрала, хоть это здесь и нерационально */
        requireNotBlank(name, "Name")
        requireNotBlank(description, "Description")
        require(price > 0 && price.isFinite()) { "Price must be finite and greater than zero." }
        requirePositive(stockBalance, "Stock balance")
        requirePositive(reserveBalance, "Reserve balance")
    }

    private fun requirePositive(int: Int, fieldName: String) {
        require(int >= 0) { "$fieldName must be positive." }
    }

    private fun requireNotBlank(fieldValue: String, fieldName: String) {
        require(fieldValue.isNotBlank()) { "$fieldName must not be blank." }
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return AbstractProduct.hashCode()
    }
}
