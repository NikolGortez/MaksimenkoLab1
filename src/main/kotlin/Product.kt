package voidpointer.mc.tests

data class Product(
    val id : Int,
    val name : String,
    val description : String,
    val price : Double,
    val stockBalance : Int,
    val reserveBalance : Int,
    val categoryId : Int
) {

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
        require(id >= 0) { "Id must be positive." }
        require(name.isNotBlank()) { "Name must not be blank." }
        require(description.isNotBlank()) { "Description must not be blank." }
        require(price > 0 && price.isFinite()) { "Price must be finite and greater than zero." }
        require(stockBalance >= 0) { "Stock balance must be positive." }
        require(reserveBalance >= 0) { "Reserve balance must be positive." }
        require(categoryId >= 0) { "Category id must be positive." }
    }
}
