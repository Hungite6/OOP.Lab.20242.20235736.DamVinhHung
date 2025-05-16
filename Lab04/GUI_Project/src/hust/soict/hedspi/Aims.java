package hust.soict.hedspi;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.screen.manager.StoreManagerScreen;
import hust.soict.hedspi.aims.store.Store;

public class Aims {
    public static void main(String[] args) {
        Store store = new Store();

        // Thêm 9 sản phẩm DVD mẫu (chú ý truyền id)
        store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc(2, "Star Wars", "Science Fiction", "George Lucas", 124, 24.95f));
        store.addMedia(new DigitalVideoDisc(3, "Aladdin", "Animation", "John Musker", 90, 18.99f));
        store.addMedia(new DigitalVideoDisc(4, "Frozen", "Animation", "Chris Buck", 102, 22.50f));
        store.addMedia(new DigitalVideoDisc(5, "Avengers", "Action", "Joss Whedon", 143, 29.99f));
        store.addMedia(new DigitalVideoDisc(6, "Inception", "Sci-fi", "Christopher Nolan", 148, 27.99f));
        store.addMedia(new DigitalVideoDisc(7, "Coco", "Animation", "Lee Unkrich", 105, 20.00f));
        store.addMedia(new DigitalVideoDisc(8, "Zootopia", "Animation", "Byron Howard", 108, 21.95f));
        
        // Hiển thị giao diện store
        new StoreManagerScreen(store);
    }
}
