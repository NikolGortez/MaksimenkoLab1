package nicol.lab.domain.product;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@ConfigSerializable
public record Product(
        int id,
        String name,
        String description,
        double price,
        int stockBalance,
        int reserveBalance
) {

    public static Product randomProduct() {
        return new Product(
                0,
                randomString(),
                randomString(),
                ThreadLocalRandom.current().nextDouble(1, 1000),
                ThreadLocalRandom.current().nextInt(0, 100),
                ThreadLocalRandom.current().nextInt(0, 100)
        );
    }

    private static String randomString() {
        int length = ThreadLocalRandom.current().nextInt(1, 8);
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append((char) ThreadLocalRandom.current().nextInt('a', 'z'));
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Product product))
            return false;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
