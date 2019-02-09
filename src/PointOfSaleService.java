import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PointOfSaleService {

    // Store all the pricing
    private Set<ProductPrice> productPrices;

    // Store all the products
    private Set<Product> products;

    PointOfSaleService() {
        productPrices = new HashSet<>();
        products = new HashSet<>();
    }

    private ProductPrice getPricing(String productName) {
        // Scanning existing set of product prices for given product code
        for (ProductPrice price : productPrices) {
            // Finding product price in set
            if (price.productCode.equals(productName)) {
                // If found than return
                return price;
            }
        }
        return null;
    }

    private Product getProduct(String productName) {
        // Scanning existing set of products for given product code
        for (Product product : products) {
            // Finding product in set
            if (product.code.equals(productName)) {
                // If found than return
                return product;
            }
        }
        return null;
    }

    public void setPricing(ProductPrice pricing) {
        Iterator<ProductPrice> iterator = productPrices.iterator();
        // Remove any existing pricing for the given product code
        while (iterator.hasNext()) {
            ProductPrice price = iterator.next();
            if (price.productCode.equals(pricing.productCode)) {
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
            product.code = productName;
            product.amount = 1;
            products.add(product);
        }
    }

    public double total() {

        double totalAmount = 0.0;

        for (Product product : products) {
            // Getting price for the product
            ProductPrice price = getPricing(product.code);
            if (price != null) {
                // Initializing product amount for per unit pricing
                int productAmt4UnitPricing = product.amount;
                // Initializing product amount for volume pricing
                int productAmt4VolumePricing = 0;
                if (price.volumeAmount > 0 && product.amount >= price.volumeAmount) {
                    // If there is volume amoutn then updating per unit amount and volume amount for pricing
                    productAmt4UnitPricing = product.amount % price.volumeAmount;
                    productAmt4VolumePricing = (product.amount - productAmt4UnitPricing) / price.volumeAmount;
                }
                // Calculating all the price
                totalAmount = totalAmount + (productAmt4UnitPricing * price.unitPrice) + (productAmt4VolumePricing * price.volumePrice);
            }
        }

        return totalAmount;
    }

    public void empty() {
        products = new HashSet<>();
    }

    public void setDefaultPricing() {
        // Setting pricing
        ProductPrice priceForA = new ProductPrice();
        priceForA.productCode = "A";
        priceForA.unitPrice = 2.0;
        priceForA.volumeAmount = 4;
        priceForA.volumePrice = 7;
        setPricing(priceForA);
        ProductPrice priceForB = new ProductPrice();
        priceForB.productCode = "B";
        priceForB.unitPrice = 12.0;
        setPricing(priceForB);
        ProductPrice priceForC = new ProductPrice();
        priceForC.productCode = "C";
        priceForC.unitPrice = 1.25;
        priceForC.volumeAmount = 6;
        priceForC.volumePrice = 6;
        setPricing(priceForC);
        ProductPrice priceForD = new ProductPrice();
        priceForD.productCode = "D";
        priceForD.unitPrice = 0.15;
        setPricing(priceForD);
    }

    public Set<ProductPrice> getDefaultPricing() {
        return productPrices;
    }

    public void clearPricing() {
        productPrices = new HashSet<>();
    }
}
