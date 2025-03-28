public class Aims {
    public static void main(String[] args) {
        // Create a new cart
        Cart anOrder = new Cart();

        // Create new DVD objects and add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
            "Animation", "Roger Allers", 87, 19.95f);
        anOrder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
            "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
            "Animation", 18.99f);
        anOrder.addDigitalVideoDisc(dvd3);

        // Print cart before removal
        System.out.println("Cart before removing:");
        anOrder.printCart(); 

        // Remove a DVD from the cart
        anOrder.removeDigitalVideoDisc(dvd2);

        // Print cart after removal
        System.out.println("\nCart after removing Star Wars:");
        anOrder.printCart();
    }
}
