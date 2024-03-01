package Pizza.src.main.java.com.pizza;

import java.util.*;
import java.util.Map.Entry;

import Pizza.src.main.java.com.pizza.prices.Sizes;
import Pizza.src.main.java.com.pizza.prices.Toppings;

public class Main {
    // Scanner || Input
    static Scanner userChoice = new Scanner(System.in);

    // Stores all info (toppings and sizes) for user receipt 

    private static HashMap<String, Double> infoSaver = new HashMap<String, Double>();

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
    static double [] sizePrice = Sizes.sizePrice;

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
            String sizeChoice = userChoice.nextLine();
            for (int i = 0; i < sizeArr.length; i++) {
                if (sizeArr[i].toLowerCase().contains(sizeChoice.toLowerCase())) {
                    sizeDetected = true;
                    double tempBalanceS = sizePrice[i];
                    billBalance += tempBalanceS; // pizza size price
                    infoSaver.put(sizeArr[i], sizePrice[i]);
                    
                    /*
                     * Observation: Input jumbles up, takes input from initial menu, then passes as
                     * input in this method
                     * Example: Choice in Menu => 1, input in following method => 1, balance added
                     * (pricesizearr) = 5
                     */

                    sizeLoop = true;
                    chooseToppings(billBalance);
                    break;
                }

            }

            if (!sizeDetected) {
                System.out.println("Frank: Sorry bud, I don't think we serve that size here");
            }
        }
    }

    static void chooseToppings(double billBalance) {
        boolean toppingLoop = false;
        boolean toppingDetected = false;
        int toppingCount = 0;
        
        while (!toppingLoop) {
            System.out.println("Frank: Alright bud, you chose your pizza topping. Now choose what toppings you'd like, all up to 3");
            String toppingChoice = userChoice.nextLine();

                for (int i = 0; i < toppingsNameArr.length; i++) {
                    if (toppingsNameArr[i].toLowerCase().contains(toppingChoice)) {
                        toppingDetected = true;
                        toppingCount ++;
                        System.out.println("Test: " + toppingsNameArr[i]);

                            if (toppingCount < 3) {chooseToppings(billBalance);}
                            if (toppingCount > 3) {infoSaver.put("Bill Total", billBalance); System.err.println(infoSaver); break;}
                    } else {
                        System.err.println("Continue?: ");
                        
                        if (toppingChoice.toLowerCase().equalsIgnoreCase("y") || toppingChoice.toLowerCase().equalsIgnoreCase("yes")) {
                            System.err.println("Frank: Okay!");
                            continue;
                        } else if (toppingChoice.toLowerCase().equalsIgnoreCase("n") || toppingChoice.toLowerCase().equalsIgnoreCase("no")) {
                            System.err.println("Test passed next method!");
                            receiptGenerator(i);
                            break;
                        }
                        break;
                    }

                    if (toppingDetected) {
                        System.err.println();
                        infoSaver.put(toppingsNameArr[i], toppingsPriceArr[i]);
                    }
                }

                if (!toppingDetected) {System.out.println("Frank: Sorry that ain't a topping we serve here bud");}
        }

    }

    static void receiptGenerator(int billBalance) {
        System.err.println("Method in");
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
