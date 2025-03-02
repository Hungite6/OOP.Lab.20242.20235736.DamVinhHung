package Lab1;

import java.util.Scanner;

public class Bai66Matrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập kích thước ma trận
        System.out.print("Nhập số hàng của ma trận: ");
        int rows = scanner.nextInt();
        System.out.print("Nhập số cột của ma trận: ");
        int cols = scanner.nextInt();

        int[][] matrix1 = new int[rows][cols];
        int[][] matrix2 = new int[rows][cols];
        int[][] resultMatrix = new int[rows][cols];

        // Nhập ma trận thứ nhất
        System.out.println("Nhập các phần tử của ma trận thứ nhất:");
        inputMatrix(scanner, matrix1);

        // Nhập ma trận thứ hai
        System.out.println("Nhập các phần tử của ma trận thứ hai:");
        inputMatrix(scanner, matrix2);

        // Cộng hai ma trận
        addMatrices(matrix1, matrix2, resultMatrix);

        // Hiển thị kết quả
        System.out.println("Ma trận kết quả sau khi cộng:");
        printMatrix(resultMatrix);

        scanner.close();
    }

    // Phương thức nhập ma trận
    private static void inputMatrix(Scanner scanner, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("Nhập phần tử [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    // Phương thức cộng hai ma trận
    private static void addMatrices(int[][] matrix1, int[][] matrix2, int[][] resultMatrix) {
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
    }

    // Phương thức in ma trận
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
