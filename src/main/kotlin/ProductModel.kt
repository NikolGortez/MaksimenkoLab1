package voidpointer.mc.tests

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ProductModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val stockBalance: Int,
    val reserveBalance: Int,
) {

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
        requirePositive(id, "Id")
        requireNotBlank(name, "Name")
        requireNotBlank(description, "Description")
        require(price > 0 && price.isFinite()) { "Price must be finite and greater than zero." }
        requirePositive(stockBalance, "Stock balance")
        requirePositive(reserveBalance, "Reserve balance")
    }

    fun toStringShort(): String {
        return "ProductModel(id:$id, name:$name, price:$price)"
    }

    private fun requirePositive(int: Int, fieldName: String) {
        require(int >= 0) { "$fieldName must be positive." }
    }

    private fun requireNotBlank(fieldValue: String, fieldName: String) {
        require(fieldValue.isNotBlank()) { "$fieldName must not be blank." }
    }
}
