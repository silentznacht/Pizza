package Pizza.src.main.java.com.pizza.prices;

public class Sizes {
    /*
     * Pizza Size Abstract class, will be used in main class to define pizza size costs
     */
    public static int smallPie = 5;
    public static int medPie = 10;
    public static int largePie = 15;

    public static String [] sizeArr = {"Small Pie", "Medium Pie", "Large Pie"};
    public static int [] sizePrice = {smallPie, medPie, largePie};

        public Sizes(int smallPie, int medPie, int largePie) {
                Sizes.smallPie = smallPie;
                Sizes.medPie = medPie;
                Sizes.largePie = largePie;
        }
}