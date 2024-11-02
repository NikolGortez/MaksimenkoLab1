package nicol.lab.repository;

import nicol.lab.domain.product.Product;
import nicol.lab.domain.product.ProductShort;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class FilteringSortingDecorator implements ProductRepository {

    protected abstract ProductRepository productRepository();

    public abstract List<ProductShort> get_k_n_short_list(Predicate<Product> filter, Comparator<Product> comparator);

    public abstract int get_count(Predicate<Product> filter);

    @Override
    public Optional<Product> findById(int id) {
        return productRepository().findById(id);
    }

    @Override
    public List<ProductShort> get_k_n_short_list(int k, int n) throws IndexOutOfBoundsException {
        return productRepository().get_k_n_short_list(k, n);
    }

    @Override
    public List<Product> sortByPrice() {
        return productRepository().sortByPrice();
    }

    @Override
    public int addAndGenerateId(Product product) {
        return productRepository().addAndGenerateId(product);
    }

    @Override
    public boolean replace(Product product) {
        return productRepository().replace(product);
    }

    @Override
    public boolean remove(Product product) {
        return productRepository().remove(product);
    }
}
