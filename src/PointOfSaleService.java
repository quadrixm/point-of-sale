import java.util.HashSet;
import java.util.Set;

public class PointOfSaleService {

    // Store all the pricing
    private static Set<ProductPrice> productPrices;

    // Store all the products
    private static Set<Product> products;

    PointOfSaleService() {
        productPrices = new HashSet<>();
        products = new HashSet<>();
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
