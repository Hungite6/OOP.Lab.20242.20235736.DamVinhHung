package Lab1;

import java.util.Scanner;

public class Bai64DaysOfMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year;
        String monthInput = ""; // Khởi tạo giá trị mặc định để tránh lỗi
        int month = -1;

        // Nhập năm hợp lệ
        while (true) {
            System.out.print("Nhập năm (số nguyên dương): ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year >= 0) break;
            } else {
                scanner.next();
            }
            System.out.println("Năm không hợp lệ. Vui lòng nhập lại!");
        }

        scanner.nextLine(); // Xóa bộ nhớ đệm

        // Nhập tháng hợp lệ
        while (month == -1) {
            System.out.print("Nhập tháng (tên đầy đủ, viết tắt, 3 chữ cái hoặc số): ");
            monthInput = scanner.nextLine().trim();
            month = getMonthNumber(monthInput);
            if (month == -1) {
                System.out.println("Tháng không hợp lệ. Vui lòng nhập lại!");
            }
        }

        // Xác định số ngày trong tháng
        int days = getDaysInMonth(month, year);
        System.out.println("Số ngày trong tháng " + monthInput + " năm " + year + " là: " + days);

        scanner.close();
    }

    // Chuyển đổi tháng từ chuỗi sang số
    private static int getMonthNumber(String month) {
        String[] months = {
            "January", "Jan.", "Jan", "1",
            "February", "Feb.", "Feb", "2",
            "March", "Mar.", "Mar", "3",
            "April", "Apr.", "Apr", "4",
            "May", "5",
            "June", "Jun.", "Jun", "6",
            "July", "Jul.", "Jul", "7",
            "August", "Aug.", "Aug", "8",
            "September", "Sept.", "Sep", "9",
            "October", "Oct.", "Oct", "10",
            "November", "Nov.", "Nov", "11",
            "December", "Dec.", "Dec", "12"
        };

        for (int i = 0; i < months.length; i += 4) {
            for (int j = 0; j < 4 && i + j < months.length; j++) {
                if (months[i + j].equalsIgnoreCase(month)) {
                    return (i / 4) + 1;
                }
            }
        }
        return -1;
    }

    // Xác định số ngày trong tháng
    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }

    // Kiểm tra năm nhuận
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
