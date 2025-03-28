public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20; // Giới hạn số DVD trong giỏ
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED]; 
    private int qtyOrdered = 0; // Số lượng DVD hiện tại trong giỏ

    // Thêm một DVD vào giỏ
    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered++;
        } else {
            System.out.println("The cart is almost full.");
        }
    }

    // Xóa một DVD khỏi giỏ
    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        boolean found = false;
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i] == disc) {
                found = true;
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1]; // Dịch các phần tử lên một vị trí
                }
                itemsOrdered[qtyOrdered - 1] = null; // Xóa phần tử cuối
                qtyOrdered--;
                break;
            }
        }
        if (!found) {
            System.out.println("The disc is not found in the cart.");
        }
    }

    // Tính tổng chi phí của giỏ hàng
    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }
    public void printCart() {
        System.out.printf("%-5s %-20s %10s%n", "", "Title", "Cost");
        
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.printf("%-5d %-20s %10.2f%n", (i + 1), 
                              itemsOrdered[i].getTitle(), 
                              itemsOrdered[i].getCost());
        }
        
        System.out.println();
        System.out.printf("%-5s %-20s %10.2f%n", "", "Total Cost", totalCost());
    }

}
