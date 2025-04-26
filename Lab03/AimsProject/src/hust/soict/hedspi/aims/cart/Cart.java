package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.MediaComparatorByCostTitle;
import hust.soict.hedspi.aims.media.MediaComparatorByTitleCost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cart {
    // Dùng ArrayList để lưu trữ các Media (DVD, Sách, CD)
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    // Thêm media vào giỏ hàng
    public void addMedia(Media media) {
        itemsOrdered.add(media);
        System.out.println(media.getTitle() + " has been added to the cart.");
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
    public ArrayList<Media> getItemsOrdered() {
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
