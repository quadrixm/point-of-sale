
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

        // Check if the it is interactive or not based on that it print $ at the beginning to take commands
        boolean cmdInput = false;

        // Using interactive shell
        Scanner scanner = new Scanner(System.in);
        // Set interactive shell to true
        cmdInput = true;

        // Print initial $ for interactive shell
        if (cmdInput) if (cmdInput) System.out.printf("$ ");

        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("exit")) {
                // Exit program
                break;
            } else {
                // TODO Process checkout
                // Print initial $ for interactive shell
                if (cmdInput) System.out.printf("$ ");
            }
        }

        return this;
    }
}
