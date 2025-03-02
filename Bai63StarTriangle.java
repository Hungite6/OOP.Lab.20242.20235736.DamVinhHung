package Lab1;

import java.util.Scanner;

public class Bai63StarTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập chiều cao tam giác từ người dùng
        System.out.print("Nhập chiều cao n: ");
        int n = scanner.nextInt();

        // Vẽ tam giác cân với n dòng
        for (int i = 1; i <= n; i++) {
            // In khoảng trắng
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // In dấu sao
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close();
    }
}
