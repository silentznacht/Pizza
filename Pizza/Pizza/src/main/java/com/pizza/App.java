package Pizza.src.main.java.com.pizza;

import java.util.*;
import Pizza.src.main.java.com.pizza.prices.Sizes;
import Pizza.src.main.java.com.pizza.prices.Toppings;

public class App {
    // Scanner || Input
    static Scanner userChoice = new Scanner(System.in);

    // Stores all info (toppings and sizes) for user receipt
    private static HashMap<String, Double> infoSaver = new HashMap<String, Double>();
    private static List<String> selectedToppings = new ArrayList<>();
    private static List<Double> selectedToppingPrices = new ArrayList<>();

    // Topping Prices
    static double onions = Toppings.onions;
    static double peppers = Toppings.peppers;
    static double mushrooms = Toppings.mushrooms;
    static double bOlives = Toppings.bOlives;
    static double peperoni = Toppings.peperoni;
    static double sausage = Toppings.sausage;
    static double bacon = Toppings.bacon;

    // Toppings Arr, names & prices
    static String[] toppingsNameArr = Toppings.toppingsArr;
    static double[] toppingsPriceArr = Toppings.toppingsPriceArr;

    // Size Prices (Pizza)
    static double smallPie = (double) Sizes.smallPie;
    static double medPie = (double) Sizes.medPie;
    static double largePie = (double) Sizes.largePie;

    // SizeArr, name and price
    static String[] sizeArr = Sizes.sizeArr;
    static double[] sizePrice = Sizes.sizePrice;

    private static final double toppingMultiplier = (double) 1.25;

    static void toppings() { // displays user toppings
        System.out.println("            [Toppings]");
        for (int i = 0; i < toppingsNameArr.length; i++) {
            System.out.println(toppingsNameArr[i] + ": " + toppingsPriceArr[i]);
        }
    }

    static void sizes() { // displays user sizes
        System.out.println("            [Sizes]");
        for (int i = 0; i < sizeArr.length; i++) {
            System.out.println(sizeArr[i] + ": " + sizePrice[i]);
        }
    }

    static void choosePizzaSize(double billBalance) {
        System.out.println("Frank: Alright bud, choose a size for your pizza");
        boolean sizeLoop = false;
        boolean sizeDetected = false;

        userChoice.nextLine();
        while (!sizeLoop) {
            System.out.println("Starting Bill Balance: " + billBalance);
            String sizeChoice = userChoice.nextLine();
            for (int i = 0; i < sizeArr.length; i++) {
                if (sizeArr[i].toLowerCase().contains(sizeChoice.toLowerCase())) {
                    sizeDetected = true;
                    double tempBalanceS = sizePrice[i];
                    billBalance += tempBalanceS; // pizza size price
                    /*
                     * Observation: Input jumbles up, takes input from initial menu, then passes as
                     * input in this method
                     * Example: Choice in Menu => 1, input in following method => 1, balance added
                     * (pricesizearr) = 5
                     */

                    sizeLoop = true;
                    infoSaver.put(sizeArr[i], tempBalanceS);
                    System.out.println(infoSaver);
                    chooseToppings(billBalance, false);
                    break;
                }
            }

            if (!sizeDetected) {
                System.out.println("Frank: Sorry bud, I don't think we serve that size here");
            }
        }
    }

    static void chooseToppings(double billBalance, boolean extra) {
        boolean toppingLoop = false;
        boolean sysQuestion = false;
        boolean toppingDetected = false;
        int toppingCount = 0;

        while (!toppingLoop) {
            System.out.println("Starting Bill Balance: " + billBalance);
            System.out.println(
                    "Frank: Alright bud, you chose your pizza topping. Now choose what toppings you'd like?: ");
            String toppingChoice = userChoice.nextLine();

            for (int i = 0; i < toppingsNameArr.length; i++) {
                if (toppingsNameArr[i].toLowerCase().contains(toppingChoice.toLowerCase())) {
                    double chosenToppingPrice = toppingsPriceArr[i];

                    if (!extra) {
                        billBalance += chosenToppingPrice;
                    }

                    if (extra) {
                        double extraPrice = chosenToppingPrice + toppingMultiplier;
                        billBalance += extraPrice;
                    }

                    toppingDetected = true;
                    sysQuestion = true;
                    toppingCount++;

                    selectedToppings.add(toppingsNameArr[i]);
                    selectedToppingPrices.add(toppingsPriceArr[i]);

                    // Debug info
                    System.out.println(toppingCount + ". " + "Test: " + toppingsNameArr[i]);
                    System.out.println("Current Bill: " + billBalance);

                    if (sysQuestion) {
                        if (toppingCount == 3) {
                            continueToppings(toppingCount, billBalance);
                            toppingLoop = true;
                            break;
                        }
                    }

                    break;
                }

            }

            if (!toppingDetected) {
                System.out.println("Frank: Sorry that ain't a topping we serve here bud");
            }

        }

    }

    static void continueToppings(int toppingCount, double billBalance) {
        System.out.println(
                "Frank: Well bud you have ordered 3 toppings, you want some more?(Tip Any Extra Toppings Now Will Cost Extra)");
        String userContinue = userChoice.nextLine();
        if (userContinue.equalsIgnoreCase("yes") || userContinue.equalsIgnoreCase("y")) {
            chooseToppings(billBalance, true);
        }
        if (userContinue.equalsIgnoreCase("no") || userContinue.equalsIgnoreCase("n"))
            ;
        {
            receiptGenerator(billBalance);
        }
    }

    static void printItems(List<String> selectedToppings, List<Double> selectedToppingPrices) {
        for (Map.Entry<String, Double> entry : infoSaver.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            System.out.println("{Size: " + key + ": " + value + "}");
        }

        for (String topping : selectedToppings) {
            for (double price : selectedToppingPrices) {
                System.err.println("{" + topping + ":" + " $" + price + "}");
                break;
            }
        }
    }

    static void receiptGenerator(double billBalance) {
        System.err.println("        [RECEIPT]        ");
        printItems(selectedToppings, selectedToppingPrices);
        System.out.println("\nTOTAL: " + billBalance);
    }

    static void menu(boolean menuLoop) {

        try {
            do {
                System.out.println(
                        """
                                                            [Frank's Pizza]

                                                1. Order Pizza
                                                2. Check Our Menu
                                                3. Leave

                                """);

                int menuChoice = userChoice.nextInt();

                switch (menuChoice) {
                    case 1:
                        // In progress
                        choosePizzaSize(0);
                        menuLoop = true;
                        break;

                    case 2:
                        sizes();
                        toppings();
                        menu(false);
                        menuLoop = true;
                        break;

                    case 3:
                        System.out.println("Frank: Okay come again! :)");
                        menuLoop = true;
                        infoSaver.clear();
                        break;

                    default:
                        System.out.println("Frank: Sorry man, that ain't on the options list");
                        break;
                }

            } while (!menuLoop);
        } catch (InputMismatchException e) {
            System.out.println("Frank: Sorry man, that ain't on the options list [Tip: Remember 1 , 2 , 3]");
        }

    }

    public static void main(String[] args) {
        menu(false);
    }

}
