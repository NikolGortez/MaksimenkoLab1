package nikol.product;

import java.util.Objects;

public class AbstractProduct {
    protected int id;
    protected String name;
    protected double price;

    public AbstractProduct(int id, String name, double price) {
        if (id < 0)
            throw new IllegalArgumentException();
        if (name == null)
            throw new IllegalArgumentException();
        if (price < 0)
            throw new IllegalArgumentException();

        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException();
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException();
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException();
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof AbstractProduct that))
            return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
