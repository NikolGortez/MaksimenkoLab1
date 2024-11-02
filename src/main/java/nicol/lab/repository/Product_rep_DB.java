package nicol.lab.repository;

import nicol.lab.dao.ProductDao;
import nicol.lab.domain.product.Product;
import nicol.lab.domain.product.ProductShort;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class Product_rep_DB {
    private final ProductDao productDao;

    public Product_rep_DB() {
        productDao = ProductDao.INSTANCE;
    }

    public Optional<Product> findById(int id) throws SQLException {
        return productDao.findById(id);
    }

    public List<Product> findAllSortedByPrice() throws SQLException {
        return productDao.findAllSortedByPrice();
    }

    public List<ProductShort> get_k_n_short_list(int k, int n) throws SQLException {
        return productDao.get_k_n_short_list(k, n);
    }

    public int insert(Product product) throws SQLException {
        return productDao.insert(product);
    }

    public int update(Product product) throws SQLException {
        return productDao.update(product);
    }

    public int deleteById(int id) throws SQLException {
        return productDao.deleteById(id);
    }

    public int get_count() throws SQLException {
        return productDao.get_count();
    }
}
