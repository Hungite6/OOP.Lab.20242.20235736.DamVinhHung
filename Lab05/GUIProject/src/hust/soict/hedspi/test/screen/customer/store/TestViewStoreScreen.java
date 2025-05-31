package hust.soict.hedspi.test.screen.customer.store;

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

public class TestViewStoreScreen extends Application {
    private static Store store;
    private static Cart cart;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store,cart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();
        //DVD
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "One Piece", "Anime DVD", "Eiichiro Oda", 600, 19.99f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "Detective Conan", "Anime DVD", "Gosho Aoyama", 500, 15.99f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(3, "Doraemon", "Anime DVD", "Fujiko F. Fujio", 300, 12.99f);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(4, "Naruto", "Anime DVD", "Masashi Kishimoto", 550, 17.99f);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc(5, "Dragon Ball Z", "Anime DVD", "Akira Toriyama", 400, 14.99f);
        DigitalVideoDisc dvd6 = new DigitalVideoDisc(6, "Attack on Titan", "Anime DVD", "Hajime Isayama", 350, 18.50f);
        DigitalVideoDisc dvd7 = new DigitalVideoDisc(11, "Attack on Titan", "Anime DVD", "Hajime Isayama", 350, 18.50f);

        //Book
        Book book1 = new Book(7, "Clean Code", "Programming", 29.99f);
        book1.addAuthor("Robert C. Martin");
        Book book2 = new Book(8, "Effective Java", "Programming", 34.99f);
        
        book2.addAuthor("Joshua Bloch");
        //CD
        CompactDisc cd1 = new CompactDisc(9, "One Piece OST", "Anime Music", 9.99f, "Toei", 10, "Hiroshi Kitadani");
        cd1.addTrack(new Track("We Are!", 3));
        cd1.addTrack(new Track("Binks no Sake", 4));
        CompactDisc cd2 = new CompactDisc(10, "Naruto OST", "Anime Music", 10.99f, "Studio Pierrot", 8, "Yasuharu Takanashi");
        cd2.addTrack(new Track("Blue Bird", 3));
        cd2.addTrack(new Track("Silhouette", 4));

        // Thêm vào Store
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(dvd4);
        store.addMedia(dvd5);
        store.addMedia(dvd6);
        store.addMedia(dvd7);
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(cd1);
        store.addMedia(cd2);
        
        launch(args);
    }
}