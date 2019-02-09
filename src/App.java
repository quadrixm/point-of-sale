
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

        boolean pricingIsSet = false;
        boolean newPricing = false;

        String newPricingFormat = "Enter new Pricing in the format <Product Code> <Per Unit Price> <Volume Amount (Optional)> <Volume Price  (Optional)>";
        String optionString = "Please enter 'continue' to continue with current pricing or 'new' to add new pricing";
        String continueOptionString = "Please enter 'continue' to continue with current pricing";
        String exitOptionString = "Please enter 'exit' to exit this program";

        // Using interactive shell
        Scanner scanner = new Scanner(System.in);

        // Setting default pricing
        pointOfSaleService.setDefaultPricing();
        System.out.println("Current pricing");
        for (ProductPrice price: pointOfSaleService.getDefaultPricing()) {
            String priceString = price.productCode + ":   $" + price.unitPrice + " each";
            if (price.volumeAmount > 0) {
                priceString += " or " + price.volumeAmount + " for $" + price.volumePrice;
            }
            System.out.println(priceString);
        }
        System.out.println(optionString);
        System.out.println(exitOptionString);
        System.out.printf(">>> ");

        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.equals("exit")) {
                // Exit program
                break;
            } else if (line.equals("continue")) {
                // Set default pricing and continue
                pricingIsSet = true;

                System.out.println("Current pricing");
                for (ProductPrice price: pointOfSaleService.getDefaultPricing()) {
                    String priceString = price.productCode + ":   $" + price.unitPrice + " each";
                    if (price.volumeAmount > 0) {
                        priceString += " or " + price.volumeAmount + " for $" + price.volumePrice;
                    }
                    System.out.println(priceString);
                }

                System.out.println("Enter product codes in serial");
                System.out.printf(">>> ");

            }  else {
                if (line.equals("new")) {
                    // Set newPricing true and clearing the existing pricing
                    newPricing = true;

                    pointOfSaleService.clearPricing();

                    System.out.println(newPricingFormat);
                    System.out.printf(">>> ");

                } else {
                    if (pricingIsSet) {
                        String[] productCodes = line.split("");

                        for (String productCode : productCodes) {
                            pointOfSaleService.scan(productCode);
                        }

                        double totalAmount = pointOfSaleService.total();
                        System.out.println("$" + totalAmount);
                        pointOfSaleService.empty();
                        // Print initial $ for interactive shell
                        System.out.println("Enter new product codes");
                        System.out.println(exitOptionString);
                        System.out.printf(">>> ");
                    } else if (newPricing) {
                        String[] pricingArray = line.split(" ");
                        ProductPrice newPrice = new ProductPrice();
                        if (pricingArray.length >= 2) {
                            newPrice.productCode = pricingArray[0];
                            newPrice.unitPrice = Double.valueOf(pricingArray[1]);
                            if (pricingArray.length >= 4) {
                                newPrice.volumeAmount = Integer.valueOf(pricingArray[2]);
                                newPrice.volumePrice = Double.valueOf(pricingArray[3]);
                            }
                            pointOfSaleService.setPricing(newPrice);
                            System.out.println("Current pricing");
                            for (ProductPrice price: pointOfSaleService.getDefaultPricing()) {
                                String priceString = price.productCode + ":   $" + price.unitPrice + " each";
                                if (price.volumeAmount > 0) {
                                    priceString += " or " + price.volumeAmount + " for $" + price.volumePrice;
                                }
                                System.out.println(priceString);
                            }
                            System.out.println(newPricingFormat);
                            System.out.println(continueOptionString);
                            System.out.println(exitOptionString);
                            System.out.printf(">>> ");
                        } else {
                            System.out.println("Invalid format for pricing");
                            System.out.println(newPricingFormat);
                            System.out.printf(">>> ");
                        }
                    } else {
                        System.out.println("Enter valid option");
                        System.out.println(optionString);
                        System.out.println(exitOptionString);
                        System.out.printf(">>> ");
                    }
                }
            }
        }

        return this;
    }
}
