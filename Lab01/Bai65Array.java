package Lab1;

import java.util.Arrays;
import java.util.Scanner;

public class Bai65Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số phần tử của mảng
        System.out.print("Nhập số phần tử của mảng: ");
        int n = scanner.nextInt();
        int[] myArray = new int[n];

        // Nhập các phần tử của mảng
        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            myArray[i] = scanner.nextInt();
        }

        // Sắp xếp mảng
        Arrays.sort(myArray);
        System.out.println("Mảng sau khi sắp xếp: " + Arrays.toString(myArray));

        // Tính tổng và giá trị trung bình
        int sum = 0;
        for (int num : myArray) {
            sum += num;
        }
        double average = (double) sum / n;

        System.out.println("Tổng các phần tử trong mảng: " + sum);
        System.out.println("Giá trị trung bình của mảng: " + average);

        scanner.close();
    }
}
