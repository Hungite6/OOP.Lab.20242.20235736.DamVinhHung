package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Store {
    // Dùng ArrayList để lưu trữ các Media (DVD, Sách, CD)
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    // Thêm media vào cửa hàng
    public void addMedia(Media media) {
        itemsInStore.add(media);
        System.out.println(media.getTitle() + " has been added to the store.");
    }

    // Xóa media khỏi cửa hàng
    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println(media.getTitle() + " has been removed from the store.");
        } else {
            System.out.println(media.getTitle() + " is not in the store.");
        }
    }

    // Hiển thị thông tin cửa hàng
    public void displayStore() {
        System.out.println("************* Store *************");
        for (Media media : itemsInStore) {
            System.out.println(media);
        }
    }

    // Tìm media theo tiêu đề
    public Media findByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;  // Trả về media nếu tìm thấy
            }
        }
        return null;  // Trả về null nếu không tìm thấy media nào có tiêu đề trùng
    }
    public int size() {
        return itemsInStore.size();
    }
}
