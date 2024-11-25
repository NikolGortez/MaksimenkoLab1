package nikol.product;

import com.google.gson.JsonObject;

public class ProductModel extends AbstractProduct {

    private String description;
    private int stockBalance;
    private int reserveBalance;

    public ProductModel(JsonObject json) {
        this(json.get("id").getAsInt(),
                json.get("name").getAsString(),
                json.get("price").getAsDouble(),
                json.get("description").getAsString(),
                json.get("stockBalance").getAsInt(),
                json.get("reserveBalance").getAsInt()
        );
    }

    public ProductModel(int id, String name, double price, String description, int stockBalance, int reserveBalance) {
        super(id, name, price);
        if (description == null)
            throw new IllegalArgumentException("description cannot be null");
        if (stockBalance < 0)
            throw new IllegalArgumentException("stockBalance cannot be negative");
        if (reserveBalance < 0)
            throw new IllegalArgumentException("reserveBalance cannot be negative");

        this.description = description;
        this.stockBalance = stockBalance;
        this.reserveBalance = reserveBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null)
            throw new IllegalArgumentException();
        this.description = description;
    }

    public int getStockBalance() {
        return stockBalance;
    }

    public void setStockBalance(int quantity) {
        if (stockBalance < 0)
            throw new IllegalArgumentException();
        this.stockBalance = quantity;
    }

    public int getReserveBalance() {
        return reserveBalance;
    }

    public void setReserveBalance(int reserveBalance) {
        if (reserveBalance < 0)
            throw new IllegalArgumentException();
        this.reserveBalance = reserveBalance;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", reserveBalance=" + reserveBalance +
                ", stockBalance=" + stockBalance +
                ", description='" + description + '\'' +
                "}";
    }
}
