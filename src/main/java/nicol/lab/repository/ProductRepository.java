package nicol.lab.repository;

import nicol.lab.domain.product.Product;
import nicol.lab.domain.product.ProductShort;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(int id);

    List<ProductShort> get_k_n_short_list(int k, int n) throws IndexOutOfBoundsException;

    List<Product> sortByPrice();

    int addAndGenerateId(Product product);

    boolean replace(Product product);

    boolean remove(Product product);
}
