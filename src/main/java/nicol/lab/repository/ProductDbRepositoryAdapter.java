package nicol.lab.repository;

import nicol.lab.domain.product.Product;
import nicol.lab.domain.product.ProductShort;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductDbRepositoryAdapter implements ProductRepository {
    private final Product_rep_DB repository = new Product_rep_DB();

    @Override
    public Optional<Product> findById(int id) {
        try {
            return repository.findById(id);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return Optional.empty();
        }
    }

    @Override
    public List<ProductShort> get_k_n_short_list(int k, int n) throws IndexOutOfBoundsException {
        try {
            return repository.get_k_n_short_list(k,n);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Product> sortByPrice() {
        try {
            return repository.findAllSortedByPrice();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return Collections.emptyList();
        }
    }

    @Override
    public int addAndGenerateId(Product product) {
        try {
            return repository.insert(product);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return -1;
        }
    }

    @Override
    public boolean replace(Product product) {
        try {
            return repository.update(product) > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return false;
        }
    }

    @Override
    public boolean remove(Product product) {
        try {
            return repository.deleteById(product.id()) > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return false;
        }
    }
}
