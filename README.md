# er диаграмма JSON и YAML классов
```mermaid
classDiagram
direction BT
class Product_rep_json {
  + Product_rep_json() 
  # read(String) List~Product~
  + writeTo(Path) boolean
}
class Product_rep_yaml {
  + Product_rep_yaml() 
  + writeTo(Path) boolean
  # read(String) List~Product~
}
class SerializableRepository {
  + SerializableRepository() 
  + removeById(int) Optional~Product~
  + addAndGenerateId(Product) Product
  + replaceById(Product) boolean
  + loadNewFrom(Path) List~Product~
  + writeTo(Path) boolean
  + get_k_n_short_list(int, int) List~ProductShort~
  + sortByPrice() List~Product~
  + idCounter() AtomicInteger
  + findProductById(int) Optional~Product~
  # read(String) List~Product~
   int _count
}

Product_rep_json  -->  SerializableRepository 
Product_rep_yaml  -->  SerializableRepository 
```

```mermaid
classDiagram
direction BT
class Product {
  + Product(int, String, String, double, int, int) 
  + price() double
  + description() String
  + name() String
  + reserveBalance() int
  - randomString() String
  + equals(Object) boolean
  + id() int
  + randomProduct() Product
  + stockBalance() int
  + hashCode() int
}
class ProductDao {
  - ProductDao(String, String, String) 
  + insert(Product) int
  + findAllSortedByPrice() List~Product~
  + update(Product) int
  + get_k_n_short_list(int, int) List~ProductShort~
  + findById(int) Optional~Product~
  - connect(String) void
  + deleteById(int) int
   int _count
}
class ProductDbRepositoryAdapter {
  + ProductDbRepositoryAdapter() 
  + remove(Product) boolean
  + get_k_n_short_list(int, int) List~ProductShort~
  + replace(Product) boolean
  + sortByPrice() List~Product~
  + addAndGenerateId(Product) int
  + findById(int) Optional~Product~
}
class ProductRepository {
<<Interface>>
  + sortByPrice() List~Product~
  + get_k_n_short_list(int, int) List~ProductShort~
  + replace(Product) boolean
  + addAndGenerateId(Product) int
  + remove(Product) boolean
  + findById(int) Optional~Product~
}
class ProductShort {
  + ProductShort(int, String) 
  + name() String
  + id() int
}
class Product_rep_DB {
  + Product_rep_DB() 
  + update(Product) int
  + findById(int) Optional~Product~
  + insert(Product) int
  + main(String[]) void
  + get_k_n_short_list(int, int) List~ProductShort~
  + findAllSortedByPrice() List~Product~
  + deleteById(int) int
   int _count
}
class Product_rep_json {
  + Product_rep_json() 
  # read(String) List~Product~
  + writeTo(Path) boolean
}
class Product_rep_yaml {
  + Product_rep_yaml() 
  # read(String) List~Product~
  + writeTo(Path) boolean
}
class SerializableRepository {
  + SerializableRepository() 
  + addAndGenerateId(Product) int
  + remove(int) boolean
  + get_k_n_short_list(int, int) List~ProductShort~
  + findProductById(int) Optional~Product~
  + writeTo(Path) boolean
  + replace(Product) boolean
  + loadNewFrom(Path) List~Product~
  # read(String) List~Product~
  + sortByPrice() List~Product~
  + idCounter() AtomicInteger
   int _count
}

ProductDao  ..>  Product : «create»
ProductDao  ..>  ProductShort : «create»
ProductDbRepositoryAdapter  ..>  ProductRepository 
ProductDbRepositoryAdapter "1" *--> "repository 1" Product_rep_DB 
ProductDbRepositoryAdapter  ..>  Product_rep_DB : «create»
Product_rep_DB "1" *--> "productDao 1" ProductDao 
Product_rep_json  -->  SerializableRepository 
Product_rep_yaml  -->  SerializableRepository 
SerializableRepository "1" *--> "products *" Product 
SerializableRepository  ..>  Product : «create»
SerializableRepository  ..>  ProductRepository 
SerializableRepository  ..>  ProductShort : «create»
```
