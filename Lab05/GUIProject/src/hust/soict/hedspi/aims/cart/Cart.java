package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.MediaComparatorByCostTitle;
import hust.soict.hedspi.aims.media.MediaComparatorByTitleCost;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 10;
    // Dùng ArrayList để lưu trữ các Media (DVD, Sách, CD)
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    // Thêm media vào giỏ hàng
    public void addMedia(Media media) throws LimitExceededException {
    	if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            itemsOrdered.add(media); // TODO: add media into cart
            System.out.println("Added media: " + media.getTitle());
        } else {
            throw new LimitExceededException("ERROR: The number of media has reached its limit!");
        }
    }

    // Xóa media khỏi giỏ hàng
    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println(media.getTitle() + " has been removed from the cart.");
        } else {
            System.out.println(media.getTitle() + " is not in the cart.");
        }
    }

    // Tính tổng chi phí của các media trong giỏ hàng
    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    // In thông tin giỏ hàng
    public void displayCart() {
        System.out.println("************* Cart *************");
        for (Media media : itemsOrdered) {
            System.out.println(media);
        }
        System.out.println("Total cost: " + totalCost() + " $");
    }
 // Lấy danh sách media
    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
    public void filterMedia(String keyword) {
        for (Media media : itemsOrdered) {
            if (String.valueOf(media.getId()).equals(keyword)
                    || media.getTitle().equalsIgnoreCase(keyword)) {
                System.out.println(media);
            }
        }
    }

    public void sortMedia(String by) {
        Comparator<Media> comparator = by.equalsIgnoreCase("title")
                ? new MediaComparatorByTitleCost()
                : new MediaComparatorByCostTitle();
        Collections.sort(itemsOrdered, comparator);
    }

    public void clearCart() {
        itemsOrdered.clear();
    }

    public Media findByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }
}
