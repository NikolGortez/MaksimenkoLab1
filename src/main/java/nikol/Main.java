package nikol;

import com.google.gson.JsonParser;
import nikol.product.ProductModel;
import nikol.product.ProductView;

public class Main {
    public static void main(String[] args) {
        String productJson = """
                {
                  "id": 3,
                  "name": "Red Bull",
                  "description": "Can of Red Bull energy drink",
                  "price": 140.0,
                  "stockBalance": 250,
                  "reserveBalance": 2
                }""";
        ProductModel productModel = new ProductModel(JsonParser.parseString(productJson).getAsJsonObject());
        System.out.println(productModel);

        productModel.setId(1);
        productModel.setName("Cola");
        productModel.setDescription("Kind Cola can");
        productModel.setPrice(63.98);
        productModel.setStockBalance(1000);
        productModel.setReserveBalance(10);

        System.out.println(productModel);

        System.out.printf("%s %s %f\n", productModel.getName(), productModel.getDescription(), productModel.getPrice());

        ProductView productView = new ProductView(0, "aaaa", 555);
        productView.setId(1);
        productView.setName("Cola");
        productView.setPrice(63.98);

        System.out.println(productView);
    }
}
