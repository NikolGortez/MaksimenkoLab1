package nicol.lab.repository;

import lombok.RequiredArgsConstructor;
import nicol.lab.dao.ProductDao;
import nicol.lab.domain.product.Product;
import nicol.lab.domain.product.ProductShort;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class FilteringSortingDbDecorator extends FilteringSortingDecorator {

    /* При работе с БД нельзя корректно взять и "передать" фильтр, не раскрывая при этом внутреннее
     * устройство класса, что нарушает инкапсуляцию. (под это нужно писать запросы отдельно)
     *
     * В рамках адекватной архитектуры можно только применять фильтр к уже полученному результату, что
     * ещё более странно: зачем фильтровать здесь в коде, когда можно эффективнее отфильтровать запросом?
     *
     * Можно было бы сделать методы filterBy(), sortBy() и т.д., но в условии задачи "возможность передачи".
     * В работе такие методы не делают универсальными, а пишут по необходимости и/или используют фреймворки.
     * В общем, задача выполнена со слезами на глазах из-за странной формулировки задачи. */

    private final ProductDbRepositoryAdapter productDbRepositoryAdapter;

    @Override
    protected ProductRepository productRepository() {
        return productDbRepositoryAdapter;
    }

    @Override
    public List<ProductShort> get_k_n_short_list(Predicate<Product> filter, Comparator<Product> comparator) {
        try {
            return ProductDao.INSTANCE.findAll().stream()
                    .filter(filter)
                    .sorted(comparator)
                    .map(product -> new ProductShort(product.id(), product.name()))
                    .toList();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return Collections.emptyList();
        }
    }

    @Override
    public int get_count(Predicate<Product> filter) {
        try {
            return Math.toIntExact(ProductDao.INSTANCE.findAll().stream().filter(filter).count());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
            return -1;
        }
    }
}
