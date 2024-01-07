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
                while (!sizeLoop) {
                    String sizeChoice = userChoice.nextLine();
                        for (int i = 0; i < sizeArr.length;) {
                            if (sizeArr[i].toLowerCase().contains(sizeChoice.toLowerCase())) {
                                int tempBalanceS = sizePrice[i];
                                billBalance += tempBalanceS; //  pizza size price
                                // insert pizzatopping method here
                                choosePizzaToppings(billBalance,0, tempBalanceS, sizeChoice);
                                System.out.println("Test Passed!: " + billBalance);
                                sizeLoop = true;
                                break;
                            } else {
                                System.out.println("Frank: Sorry bud, I don't think we serve that size here");
                                break;
                            }
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
                            toppingCount ++;
                            double tempBalanceT = toppingsPriceArr[k]; // topping cost
                            billBalance += tempBalanceT;

                                if (toppingCount < 3) {
                                    choosePizzaToppings(billBalance, toppingCount, pizzaPrice, pizzaSize);
                                    break;
                                } else {
                                    // receipt method here
                                    receiptGenerator(billBalance, tempBalanceT, pizzaPrice, pizzaSize);
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

    }

    static void menu(boolean menuLoop) {
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

            while (!menuLoop) {
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
            }
    }

    public static void main(String [] args) {
          menu(false);
    }
    
}
