package nicol.lab.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import nicol.lab.domain.product.Product;
import nicol.lab.domain.product.ProductShort;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductDao {
    /* Singleton почти не стоит использовать, так как он ломает тестируемость и масштабируемость кода.
     * Даже тут он уже лишний и добавлен только потому что так сказано в задании. */
    public static final ProductDao INSTANCE;

    static {
        INSTANCE = new ProductDao("org.postgresql.Driver", "jdbc:postgresql://127.0.0.1:5432/lab2", "lab2");
        try {
            INSTANCE.connect("changeme");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final String driverClassName;
    private final String url;
    private final String username;

    private Connection connection;

    private void connect(String password) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(url, username, password);
    }

    public Optional<Product> findById(int id) throws SQLException {
        try (PreparedStatement selectById = connection.prepareStatement("SELECT * FROM product WHERE id = ?")) {
            selectById.setInt(1, id);
            ResultSet resultSet = selectById.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock_balance"),
                        resultSet.getInt("reserve_balance")
                ));
            }
            return Optional.empty();
        }
    }

    public List<ProductShort> get_k_n_short_list(int k, int n) throws SQLException {
        try (PreparedStatement selectRanged = connection.prepareStatement("SELECT id, name FROM product WHERE id >= ? AND id < ?")) {
            selectRanged.setInt(1, (k - 1) * n);
            selectRanged.setInt(2, k * n);
            ResultSet resultSet = selectRanged.executeQuery();
            List<ProductShort> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(new ProductShort(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return List.copyOf(products);
        }
    }

    public int add(Product product) throws SQLException {
        try (PreparedStatement insert = connection.prepareStatement("INSERT INTO product (name, description, price, stock_balance, reserve_balance) VALUES (?, ?, ?, ?, ?) RETURNING id")) {
            insert.setString(1, product.name());
            insert.setString(2, product.description());
            insert.setDouble(3, product.price());
            insert.setInt(4, product.stockBalance());
            insert.setInt(5, product.reserveBalance());
            ResultSet resultSet = insert.executeQuery();
            return resultSet.next() ? resultSet.getInt("id") : -1;
        }
    }

    public int update(Product product) throws SQLException {
        try (PreparedStatement update = connection.prepareStatement("UPDATE product SET name = ?, description = ?, price = ?, reserve_balance = ?, stock_balance = ? WHERE id = ?")) {
            update.setString(1, product.name());
            update.setString(2, product.description());
            update.setDouble(3, product.price());
            update.setInt(4, product.stockBalance());
            update.setInt(5, product.reserveBalance());
            update.setInt(6, product.id());
            return update.executeUpdate();
        }
    }

    public int deleteById(int id) throws SQLException {
        try (PreparedStatement delete = connection.prepareStatement("DELETE FROM product WHERE id = ?")) {
            delete.setInt(1, id);
            return delete.executeUpdate();
        }
    }

    public int get_count() throws SQLException {
        try (PreparedStatement select = connection.prepareStatement("SELECT COUNT(*) FROM product")) {
            ResultSet resultSet = select.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : -1;
        }
    }
}
