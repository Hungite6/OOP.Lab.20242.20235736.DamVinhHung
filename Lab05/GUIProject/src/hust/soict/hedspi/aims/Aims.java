package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aims extends Application {
    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("AIMS Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();

     // Thêm DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "Inception", "Sci-Fi", "Christopher Nolan", 148, 19.99f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "The Matrix", "Action", "Wachowski Sisters", 136, 15.99f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Titanic", "Romance", "James Cameron", -194, 12.99f); // Độ dài âm
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(4, "The Godfather", "Crime", "Francis Ford Coppola", 175, 17.50f);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc(5, "Avatar", "Sci-Fi", "James Cameron", -162, 22.99f); // Độ dài âm

        // Thêm CDs
        CompactDisc cd1 = new CompactDisc(6, "Abbey Road", "Rock", 15.99f, "N/A", 47, "The Beatles");
        cd1.addTrack(new Track("Come Together", 4));
        cd1.addTrack(new Track("Something", 3));

        CompactDisc cd2 = new CompactDisc(7, "Thriller", "Pop", 14.50f, "N/A", 42, "Michael Jackson");
        cd2.addTrack(new Track("Billie Jean", 5));
        cd2.addTrack(new Track("Beat It", 4));

        CompactDisc cd3 = new CompactDisc(8, "Dark Side of the Moon", "Rock", 13.99f, "N/A", 0, "Pink Floyd"); // Độ dài sẽ được tính từ tracks
        cd3.addTrack(new Track("Time", -7)); // Track có độ dài âm
        cd3.addTrack(new Track("Money", 6));

        CompactDisc cd4 = new CompactDisc(9, "Back in Black", "Rock", 12.99f, "N/A", 42, "AC/DC");
        cd4.addTrack(new Track("Hells Bells", 5));

        CompactDisc cd5 = new CompactDisc(10, "Rumours", "Rock", 11.99f, "N/A", 40, "Fleetwood Mac");
        cd5.addTrack(new Track("Dreams", 4));

        // Thêm Books
        Book book1 = new Book(11, "The Great Gatsby", "Fiction", 10.99f);
        book1.addAuthor("F. Scott Fitzgerald");
        Book book2 = new Book(12, "To Kill a Mockingbird", "Fiction", 12.50f);
        book2.addAuthor("Harper Lee");
        Book book3 = new Book(13, "1984", "Dystopian", 9.99f);
        book3.addAuthor("George Orwell");
        Book book4 = new Book(14, "Pride and Prejudice", "Romance", 8.75f);
        book4.addAuthor("Jane Austen");
        Book book5 = new Book(15, "Clean Code", "Programming", 29.99f);
        book5.addAuthor("Robert C. Martin");

        // Thêm vào Store theo thứ tự DVD, CD, Book
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(dvd4);
        store.addMedia(dvd5);
        store.addMedia(cd1);
        store.addMedia(cd2);
        store.addMedia(cd3);
        store.addMedia(cd4);
        store.addMedia(cd5);
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(book3);
        store.addMedia(book4);
        store.addMedia(book5);

        // Khởi chạy ứng dụng JavaFX
        launch(args);
    }
}