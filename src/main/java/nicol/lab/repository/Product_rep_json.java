package nicol.lab.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import nicol.lab.domain.product.Product;
import nicol.lab.domain.product.ProductShort;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public final class Product_rep_json {
    private static final Gson PRETTY_GSON = new GsonBuilder().setPrettyPrinting().create();

    private final AtomicInteger idCounter = new AtomicInteger(0);
    private final List<Product> products = new ArrayList<>();

    public AtomicInteger idCounter() {
        return idCounter;
    }

    @SuppressWarnings("unchecked")
    public List<Product> loadNewFrom(Path pathToFile) {
        /* сгенерировать тип, который нужно прочитать из файла */
        /* прочитать список продуктов из файла */
        /* очистить список, если ранее уже что-то в нём лежало */
        /* добавить все загруженные продукты */
        /* установить ID на самый большой из загруженного списка + 1 или оставить 0 */
        try {
            Type type = TypeToken.getParameterized(List.class, Product.class).getType();
            List<Product> products = PRETTY_GSON.fromJson(Files.readString(pathToFile, StandardCharsets.UTF_8), type);
            this.products.clear();
            this.products.addAll(products);
            this.products.stream().map(Product::id).max(Comparator.naturalOrder()).ifPresent(id -> idCounter.set(id + 1));
        } catch (IOException io) {
            log.atError().setCause(io).log("Не удалось прочитать файл {}", pathToFile);
        }
        return products;
    }

    public boolean writeTo(Path pathToFile) {
        /* записать в файл и вернуть true/false в зависимости от успешности */
        try {
            Files.writeString(pathToFile, PRETTY_GSON.toJson(products));
            return true;
        } catch (IOException io) {
            log.atError().setCause(io).log("Не удалось сохранить файл {}", pathToFile);
            return false;
        }
    }

    public Optional<Product> findProductById(int id) {
        /* фильтр списка по id */
        return products.stream().filter(product -> product.id() == id).findAny();
    }

    public List<ProductShort> get_k_n_short_list(int k, int n) throws IndexOutOfBoundsException {
        /* получение списка k, n и преобразование типа из Product в ProductShort */
        /* если k, n некорректные - кинет ошибку IndexOutOfBoundsException */
        return products.subList((k - 1) * n, k * n)
                .stream()
                .map(product -> new ProductShort(product.id(), product.description()))
                .toList();
    }

    public List<Product> sortByPrice() {
        /* сортировка по цене */
        products.sort(Comparator.comparingDouble(Product::price).reversed());
        return products;
    }

    public Product addAndGenerateId(Product product) {
        /* скопировать объект продукта со сгенерированным id */
        /* добавить его в список и вернуть */
        Product productWithId = new Product(
                idCounter.getAndIncrement(),
                product.name(),
                product.description(),
                product.price(),
                product.stockBalance(),
                product.reserveBalance()
        );
        products.add(productWithId);
        return productWithId;
    }

    public boolean replaceById(Product product) {
        /* найти продукт с совпадающим id и заменить его */
        /* вернуть true, если найден и заменён или false в противном случае */
        ListIterator<Product> iterator = products.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().id() == product.id()) {
                iterator.set(product);
                return true;
            }
        }
        return false;
    }

    public Optional<Product> removeById(int id) {
        /* найти продукт с совпадающим id и удалить его */
        /* вернуть Optional с продуктом или пустой, если не найден */
        ListIterator<Product> iterator = products.listIterator();
        while (iterator.hasNext()) {
            Product nextProduct = iterator.next();
            if (nextProduct.id() == id) {
                iterator.remove();
                return Optional.of(nextProduct);
            }
        }
        return Optional.empty();
    }

    public int get_count() {
        return products.size();
    }
}
