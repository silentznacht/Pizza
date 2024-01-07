package Pizza.src.main.java.com.pizza.prices;

public class Toppings {
    /*
     *  Toppings Abstract Class, will be used to define prices for toppings and be used in main class
     */
    
    public static double onions = 0.35;
    public static double peppers = 0.35;
    public static double mushrooms = 0.35;
    public static double bOlives = 0.35;

    public static double peperoni = 0.50;
    public static double sausage = 0.50;
    public static double bacon = 0.50;

    // Toppings Arr | Toppings Prices
    public static String [] toppingsArr = {"Onions", "Peppers", "Mushrooms", "Black Olives", "Peperoni", "Sausage", "Bacon"};
    public static double [] toppingsPriceArr = {onions, peppers, mushrooms, bOlives, peperoni, sausage, bacon};
    
    public Toppings(double onions, double peppers, double mushrooms, double bOlives, double peperoni, double sausage, double bacon) {
            Toppings.onions = onions;
            Toppings.peppers = peppers;
            Toppings.mushrooms = mushrooms;
            Toppings.bOlives = bOlives;
            Toppings.peperoni = peperoni;
            Toppings.sausage = sausage;
            Toppings.bacon = bacon;
    }
    
}
