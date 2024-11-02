package nicol.lab.domain.product;

import java.util.Objects;

public record Product(
        int id,
        String name,
        String description,
        double price,
        int stockBalance,
        int reserveBalance
) {
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
