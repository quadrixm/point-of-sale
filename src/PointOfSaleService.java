import java.util.LinkedList;
import java.util.List;

public class PointOfSaleService {

    // Store all the pricing
    private static List<ProductPrice> productPrices;

    // Store all the products
    private static List<Product> products;

    PointOfSaleService() {
        productPrices = new LinkedList();
        products = new LinkedList();
    }

    public void setPricing(ProductPrice pricing) {

    }

    public void scan(String productName) {

    }

    public double total() {

        double totalAmount = 0.0;

        // TODO Print all amount
        return totalAmount;
    }
}
