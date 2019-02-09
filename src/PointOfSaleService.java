import java.util.HashSet;
import java.util.Iterator;
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

    private ProductPrice getPricing(String productName) {
        // Scanning existing set of product prices for given product name
        for (ProductPrice price: productPrices) {
            // Finding product price in set
            if (price.productName.equals(productName)) {
                // If found than return
                return price;
            }
        }
        return null;
    }

    private Product getProduct(String productName) {
        // Scanning existing set of products for given product name
        for (Product product: products) {
            // Finding product in set
            if (product.name.equals(productName)) {
                // If found than return
                return product;
            }
        }
        return null;
    }

    public void setPricing(ProductPrice pricing) {
        Iterator<ProductPrice> iterator = productPrices.iterator();
        // Remove any existing pricing for the given product name
        while (iterator.hasNext()) {
            ProductPrice price = iterator.next();
            if (price.productName.equals(pricing.productName)) {
                iterator.remove();
            }
        }
        productPrices.add(pricing);
    }

    public void scan(String productName) {

        // Getting existing product if any
        Product product = getProduct(productName);
        if (product != null) {
            // If found updating the amount
            product.amount++;
        } else {
            // If product not found in the list add a new product in the set
            product = new Product();
            product.name = productName;
            product.amount = 1;
            products.add(product);
        }
    }

    public double total() {

        double totalAmount = 0.0;

        for (Product product: products) {
            ProductPrice price = getPricing(product.name);
            if (price != null) {
                if (price.bulkAmount > 0 && product.amount >= price.bulkAmount) {
                    // TODO Calcualte bulk price
                }
                // TODO Calculate total
            }
        }

        return totalAmount;
    }
}
