package Pizza.src.main.java.com.pizza;

import java.util.*;
import Pizza.src.main.java.com.pizza.prices.Sizes;
import Pizza.src.main.java.com.pizza.prices.Toppings;

public class Main {
    // Scanner || Input
    static Scanner userChoice = new Scanner(System.in);

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
    static int smallPie = Sizes.smallPie;
    static int medPie = Sizes.medPie;
    static int largePie = Sizes.largePie;

    // SizeArr, name and price

    static String[] sizeArr = Sizes.sizeArr;
    static int[] sizePrice = Sizes.sizePrice;

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

    static void choosePizzaSize(int billBalance) {
        System.out.println("Frank: Alright bud, choose a size for your pizza");
        boolean sizeLoop = false;
        boolean sizeDetected = false;

        userChoice.nextLine();
        while (!sizeLoop) {
            String sizeChoice = userChoice.nextLine();
            for (int i = 0; i < sizeArr.length; i++) {
                if (sizeArr[i].toLowerCase().contains(sizeChoice.toLowerCase())) {
                    sizeDetected = true;
                    int tempBalanceS = sizePrice[i];
                    billBalance += tempBalanceS; // pizza size price
                    // insert pizzatopping method here
                    // choosePizzaToppings(billBalance,0, tempBalanceS, sizeChoice);

                    /*
                     * Observation: Input jumbles up, takes input from initial menu, then passes as
                     * input in this method
                     * Example: Choice in Menu => 1, input in following method => 1, balance added
                     * (pricesizearr) = 5
                     */

                    System.out.println("Test Passed!: " + billBalance);
                    sizeLoop = true;
                    choosePizzaToppings(billBalance, tempBalanceS, sizeChoice);
                    break;
                }

            }

            if (!sizeDetected) {
                System.out.println("Frank: Sorry bud, I don't think we serve that size here");
            }
        }
    }

    
    static void choosePizzaToppings(int billBalance, int pizzaPrice, String pizzaSize) {
        int toppingCount = 0;
        boolean toppingDetected = false;

        System.out.println("Test: " + pizzaSize);
        System.out.println("Frank: Alright bud, you chose your pizza topping. Now choose what toppings you'd like, all up to 3");

        while (toppingCount < 3) {
            String toppingChoice = userChoice.nextLine();
            for (int k = 0; k < toppingsNameArr.length; k++) {
                if (toppingsNameArr[k].equalsIgnoreCase(toppingChoice)) {
                    toppingDetected = true;
                    toppingCount++;
                    double tempBalanceT = toppingsPriceArr[k];
                    billBalance += tempBalanceT;
                    System.out.println("Test: " + toppingCount + ": " + toppingChoice);

                    if (toppingCount < 3) {
                        userChoice.nextLine();
                        System.out.println("Frank: Your previous topping chosen was (" + toppingChoice + ") would you like to choose another?: ");
                        userChoice.nextLine();
                        String userContinue = userChoice.nextLine();

                            if (userContinue.equalsIgnoreCase("y") || userContinue.equalsIgnoreCase("yes")) {choosePizzaToppings(billBalance, pizzaPrice, pizzaSize); break;}
                            if (userContinue.equalsIgnoreCase("n") || userContinue.equalsIgnoreCase("no")) {
                                System.err.println("Frank: Okay, Here's the bill then bud");
                                receiptGenerator(billBalance, tempBalanceT, pizzaPrice, userContinue, pizzaSize);// int billBalance, double toppingPrice, int sizePrice, String toppingChosen, String pizzSize
                                return;
                            } 
                    }

                    if (toppingCount > 3) {
                        System.err.println("Frank: You've reached the maximum toppings allowed.");
                        break;
                    }
                    break;
                }
            }

            if (!toppingDetected) {
                System.out.println("Frank: Sorry that ain't a topping we serve here bud");
            }
        }

    }

    static void receiptGenerator(int billBalance, double toppingPrice, int sizePrice, String toppingChosen, String pizzSize) {
        System.out.println("        [RECEIPT]        ");
        //for (String topping : )
    }

    static void menu(boolean menuLoop) {
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
                    break;

                default:
                    System.out.println("Frank: Sorry man, that ain't on the options list");
                    break;
            }

        } while (!menuLoop);
    }

    public static void main(String[] args) {
        menu(false);
    }

}
