public class Main {
    public static void main(String[] args) {
        System.out.println("Placing order with the Krusty Krab Restaurant...");
        KrustyKrabRestaurant store = new OgKrustyKrabRestaurant();
        Burger kp = store.orderKrabbyPatty("KrabbyPatty");
        kp.printIngredients();

        kp = store.orderKrabbyPatty("AllBunPatty");
        kp.printIngredients();

        System.out.println("\nPlacing order with the Pretty Krab Restaurant...");
        store = new PrettyKrabRestaurant();
        kp = store.orderKrabbyPatty("KrabbyPatty");
        kp.printIngredients();
        kp = store.orderKrabbyPatty("AllBunPatty");
        kp.printIngredients();

        System.out.println("\nPlacing order with the Nasty Krab Restaurant...");
        store = new NastyKrabRestaurant();
        kp = store.orderKrabbyPatty("KrabbyPatty");
        kp.printIngredients();
        kp = store.orderKrabbyPatty("AllBunPatty");
        kp.printIngredients();
    }
}
