package nikol.product;

public class ProductView extends AbstractProduct {
    public ProductView(int id, String name, double price) {
        super(id, name, price);
    }

    public String toString() {
        return "id: " + id + ", name: " + name + ", price: " + price;
    }
}
