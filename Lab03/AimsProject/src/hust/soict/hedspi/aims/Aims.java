package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

import java.util.Scanner;

public class Aims {
    private static final Store store = new Store();
    private static final Cart cart = new Cart();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    viewCart();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    public static void viewStore() {
        int option;
        do {
            store.displayStore();
            storeMenu();
            option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    Media media = store.findByTitle(title);
                    if (media != null) {
                        System.out.println(media);
                        if (media instanceof Playable) {
                            mediaDetailsMenu();
                            int sub = Integer.parseInt(sc.nextLine());
                            if (sub == 1) cart.addMedia(media);
                            else if (sub == 2) ((Playable) media).play();
                        } else {
                            System.out.println("1. Add to cart\n0. Back");
                            int sub = Integer.parseInt(sc.nextLine());
                            if (sub == 1) cart.addMedia(media);
                        }
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter title: ");
                    title = sc.nextLine();
                    media = store.findByTitle(title);
                    if (media != null) {
                        cart.addMedia(media);
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter title: ");
                    title = sc.nextLine();
                    media = store.findByTitle(title);
                    if (media instanceof Playable) ((Playable) media).play();
                    else System.out.println("This media cannot be played.");
                    break;
                case 4:
                    cart.displayCart();
                    break;
            }
        } while (option != 0);
    }

    public static void updateStore() {
        System.out.println("1. Add media\n2. Remove media");
        int opt = Integer.parseInt(sc.nextLine());
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        Media media = store.findByTitle(title);
        if (opt == 1) {
            if (media == null) {
                System.out.print("Enter category: ");
                String cat = sc.nextLine();
                System.out.print("Enter cost: ");
                float cost = Float.parseFloat(sc.nextLine());
                store.addMedia(new Book(store.size() + 1, title, cat, cost));
            } else {
                System.out.println("Media already exists.");
            }
        } else if (opt == 2) {
            if (media != null) store.removeMedia(media);
            else System.out.println("Media not found.");
        }
    }

    public static void viewCart() {
        int opt;
        do {
            cart.displayCart();
            cartMenu();
            opt = Integer.parseInt(sc.nextLine());
            switch (opt) {
                case 1:
                    System.out.println("Filter by (id or title): ");
                    String keyword = sc.nextLine();
                    cart.filterMedia(keyword);
                    break;
                case 2:
                    System.out.println("Sort by title or cost?");
                    String by = sc.nextLine();
                    cart.sortMedia(by);
                    break;
                case 3:
                    System.out.print("Enter title to remove: ");
                    String title = sc.nextLine();
                    Media media = cart.findByTitle(title);
                    if (media != null) cart.removeMedia(media);
                    else System.out.println("Media not found.");
                    break;
                case 4:
                    System.out.print("Enter title to play: ");
                    title = sc.nextLine();
                    media = cart.findByTitle(title);
                    if (media instanceof Playable) ((Playable) media).play();
                    else System.out.println("Cannot play this media.");
                    break;
                case 5:
                    System.out.println("An order has been created. Thank you!");
                    cart.clearCart();
                    break;
            }
        } while (opt != 0);
    }
}
