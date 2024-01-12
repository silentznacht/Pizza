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
    static String [] toppingsNameArr = Toppings.toppingsArr;
    static double [] toppingsPriceArr = Toppings.toppingsPriceArr;

    // Size Prices (Pizza)
    static int smallPie = Sizes.smallPie;
    static int medPie = Sizes.medPie;
    static int largePie = Sizes.largePie;

    // SizeArr, name and price

    static String [] sizeArr = Sizes.sizeArr;
    static int [] sizePrice = Sizes.sizePrice;

    static void toppings () { // displays user toppings          
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
                                billBalance += tempBalanceS; //  pizza size price
                                // insert pizzatopping method here
                                //choosePizzaToppings(billBalance,0, tempBalanceS, sizeChoice);
                               
                                /*
                                 * Observation: Input jumbles up, takes input from inital menu, then passes as input in this method
                                 * Example: Choice in Menu => 1, input in following method => 1, balance added (pricesizearr) = 5 
                                 */
                                
                                System.out.println("Test Passed!: " + billBalance);
                                sizeLoop = true;
                                choosePizzaToppings(billBalance, 0, tempBalanceS, sizeChoice);
                                break;
                            }
                            
                        }


                        if (!sizeDetected) {
                            System.out.println("Frank: Sorry bud, I don't think we serve that size here");
                        }
                }
    }
 
    static void choosePizzaToppings(int billBalance, int toppingCount, int pizzaPrice, String pizzaSize) {
        System.out.println("Frank: Alright bud, you chose your pizza topping. Now choose what toppings you'd like, all up to 3");
        boolean toppingLoop = false;
            while (!toppingLoop) {
                String toppingChoice = userChoice.nextLine();
                    for (int k = 0; k < toppingsNameArr.length;) {
                        if (toppingsNameArr[k].toLowerCase().contains(toppingChoice.toLowerCase())) {
                            toppingCount ++; // keeps track of toppings order by user
                            double tempBalanceT = toppingsPriceArr[k]; // topping cost
                            billBalance += tempBalanceT; // add total toppings cost to billBalance
                            System.out.println("Test: " + toppingCount + ": " + toppingChoice);
                                /*
                                 * Issue: Will counter ++ correctly, and move forward to following method
                                 *        but doesn't accept any other topping aside from initial inputted
                                 *        topping.
                                 */
                                if (toppingCount <  3) { //  asks user if they wish to add an additional topping
                                    System.out.println("Frank: Your previous topping chosen was (" + toppingChoice + ") would you like to choose another?: " );
                                    String userContinue = userChoice.nextLine();    
                                        if (userContinue.equalsIgnoreCase("y") || userContinue.equalsIgnoreCase("yes")) choosePizzaToppings(billBalance, toppingCount, pizzaPrice, pizzaSize);
                                        if (userContinue.equalsIgnoreCase("n") || userContinue.equalsIgnoreCase("no")) System.err.println("Frank: Okay, Heres the bill then bud"); receiptGenerator(billBalance, tempBalanceT, pizzaPrice, userContinue);
                                } else {
                                    toppingLoop = true;
                                    break;
                                }
                            
                        } else {
                            System.out.println("Frank: Sorry that ain't a topping we serve here bud");
                            break;
                        }
                    }
            }
    }

    static void receiptGenerator(int billBalance, double toppingPrice, int sizePrice, String pizzSize) {
            System.out.println("Test: End Program");
    }

    static void menu(boolean menuLoop) {
        do {
                System.out.println
                    (
                        """
                                            [Frank's Pizza]

                                1. Order Pizza 
                                2. Check Our Menu
                                3. Leave
                            
                        """ 
                    );

                int menuChoice = userChoice.nextInt();

                    switch(menuChoice) {
                            case 1:
                                // In progress
                                choosePizzaSize(0);
                                menuLoop = true;
                                break;
                            
                            case 2:
                                sizes();
                                toppings();
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

        } while(!menuLoop);
    }

    public static void main(String [] args) {
          menu(false);
    }
    
}
