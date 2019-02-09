
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Class<?> primarySrc;

    // Service to process transaction
    private static PointOfSaleService pointOfSaleService;

    // Store all the pricing
    private static List<ProductPrice> productPrices;

    // Store all the products
    private static List<Product> products;



    public App(Class<?> primarySources) {
        this.primarySrc = primarySources;
    }

    public static App run(Class<?> primarySource, String... args) {

        // Getting the instance of the service
        pointOfSaleService = new PointOfSaleService();

        return new App(primarySource).run(args);
    }

    public App run(String... args) {

        // Setting pricing
        ProductPrice priceForA = new ProductPrice();
        priceForA.productCode = "A";
        priceForA.unitPrice = 2.0;
        priceForA.volumeAmount = 4;
        priceForA.volumePrice = 7;
        pointOfSaleService.setPricing(priceForA);
        ProductPrice priceForB = new ProductPrice();
        priceForB.productCode = "B";
        priceForB.unitPrice = 12.0;
        pointOfSaleService.setPricing(priceForB);
        ProductPrice priceForC = new ProductPrice();
        priceForC.productCode = "C";
        priceForC.unitPrice = 1.25;
        priceForC.volumeAmount = 6;
        priceForC.volumePrice = 6;
        pointOfSaleService.setPricing(priceForC);
        ProductPrice priceForD = new ProductPrice();
        priceForD.productCode = "D";
        priceForD.unitPrice = 0.15;
        pointOfSaleService.setPricing(priceForD);

        // Check if the it is interactive or not based on that it print $ at the beginning to take commands
        boolean cmdInput = false;

        // Using interactive shell
        Scanner scanner = new Scanner(System.in);
        // Set interactive shell to true
        cmdInput = true;

        // Print initial $ for interactive shell
        if (cmdInput) {
            System.out.println("Enter product codes");
            System.out.printf(">>> ");
        }

        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("exit")) {
                // Exit program
                break;
            } else {

                String[] productCodes = input.split("");

                for (String productCode : productCodes) {
                    pointOfSaleService.scan(productCode);
                }

                double totalAmount = pointOfSaleService.total();
                System.out.println("$" + totalAmount);
                pointOfSaleService.empty();
                // Print initial $ for interactive shell
                if (cmdInput) {
                    System.out.println("Enter product codes");
                    System.out.printf(">>> ");
                }
            }
        }

        return this;
    }
}
