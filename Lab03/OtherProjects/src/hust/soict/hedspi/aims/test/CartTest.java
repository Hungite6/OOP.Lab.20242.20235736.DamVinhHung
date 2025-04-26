package hust.soict.hedspi.aims.test;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;


public class CartTest {
    public static void main(String[] args) {
        //Create a new cart
        Cart cart = new Cart();

        //Create new dvd objects and add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", 
                "Animation", "Roger Allers", 87, 19.95f);
        cart.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", 
                "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", 
                "Animation", 18.99f);
        cart.addDigitalVideoDisc(dvd3);

        //Test the print method
        cart.printCart();

        //To-do: Test the search methods here
        // Search by ID
        System.out.println("\nSearch DVD by ID (Expecting a match with ID = 1):");
        cart.searchById(1);

        System.out.println("\nSearch DVD by ID (Expecting no match with ID = 10):");
        cart.searchById(10);

        // Search by Title
        System.out.println("\nSearch DVD by Title (Expecting match with 'Star'):");
        cart.searchByTitle("Star");

        System.out.println("\nSearch DVD by Title (Expecting no match with 'Harry Potter'):");
        cart.searchByTitle("Harry Potter");
    }
}

