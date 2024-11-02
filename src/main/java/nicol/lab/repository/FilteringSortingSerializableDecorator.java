package nicol.lab.repository;

import lombok.RequiredArgsConstructor;
import nicol.lab.domain.product.Product;
import nicol.lab.domain.product.ProductShort;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class FilteringSortingSerializableDecorator extends FilteringSortingDecorator {
    private final SerializableRepository serializableRepository;

    @Override
    protected ProductRepository productRepository() {
        return serializableRepository;
    }

    @Override
    public List<ProductShort> get_k_n_short_list(Predicate<Product> filter, Comparator<Product> comparator) {
        return serializableRepository.allProducts().stream()
                .filter(filter)
                .sorted(comparator)
                .map(product -> new ProductShort(product.id(), product.name()))
                .toList();
    }

    @Override
    public int get_count(Predicate<Product> filter) {
        return Math.toIntExact(serializableRepository.allProducts().stream()
                .filter(filter)
                .count());
    }
}
