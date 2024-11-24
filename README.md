# ЛР 1

## Тема

Онлайн-магазин.

## ER модель

```mermaid
erDiagram
    user ||--o{ order : "заказывает"
    order ||--|| product : "имеет"
    product ||--|| category : "имеет"
    product {
        int id
        string name
        string description
        double price
        int stock_balance
        int reserve_balance
    }
    user {
        int id
        string name
        string email
        string address
    }
    order {
        int id
        int user_id
        int product_id
    }
```

### Описание таблиц

1. user Пользователь:
    - id: уникальный идентификатор
    - first_name, middle_name, last_name: ФИО пользователя
    - email: email адрес пользователя
    - address: адрес пользователя

2. order Заказ:
    - id: уникальный идентификатор - primary key
    - user_id: foreign key к id пользователя
    - product_id: foreign key к id продукта

3. product Продукт:
    - id: уникальный идентификатор - primary key
    - name: наименование продукта
    - description: описание продукта
    - price: цена продукта
    - stockBalance: кол-во продукта на складе
    - reserveBalance: кол-во зарезервированного продукта на складе

## Выбранная сущность

Выбрана независимая сущность `product` "Продукт"

## Полная диаграмма классов

```mermaid
classDiagram
direction BT
class AbstractProduct {
  + AbstractProduct() 
  + equals(Object?) Boolean
  + hashCode() Int
}
class Companion {
  + fromJson(String) ProductModel
}
class MainKt {
  + main() Unit
}
class Product {
<<Interface>>
   Int id
}
class ProductModelTest {
  + ProductModelTest() 
  + testEquality() Unit
  + testJson() Unit
}
class data  ProductModel {
  + ProductModel(Int, String, String, Double, Int, Int) 
  - requirePositive(Int, String) Unit
  - requireNotBlank(String, String) Unit
   String name
   String description
   Int id
   Int stockBalance
   Double price
   Int reserveBalance
}
class data  ProductView {
  + ProductView(Int, String, Double) 
   String name
   Double price
   Int id
}

AbstractProduct  ..>  Product 
data  ProductModel  -->  Companion 
MainKt  ..>  data  ProductModel : «создаёт»
MainKt  ..>  data  ProductView : «создаёт»
ProductModelTest  ..>  data  ProductModel : «создаёт»
ProductModelTest  ..>  data  ProductView : «создаёт»
data  ProductModel  -->  AbstractProduct 
data  ProductModel  ..>  AbstractProduct : «создаёт»
data  ProductView  -->  AbstractProduct 
data  ProductView  ..>  AbstractProduct : «создаёт»
```
