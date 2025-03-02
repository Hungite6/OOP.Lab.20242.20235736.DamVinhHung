package Lab1;

import java.util.Scanner;

public class Bai62InputFromKeyboard {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        // nhập tên
        System.out.print("What's your name? ");
        String name = keyboard.nextLine();

        // nhập tuổi
        System.out.print("How old are you? ");
        int age = keyboard.nextInt();

        // nhập chiều cao
        System.out.print("How tall are you (m)? ");
        double height = keyboard.nextDouble();

        // Hiển thị thông tin người dùng
        System.out.println("Mrs./Ms. " + name + ", " + age + " years old. " +
                           "Your height is " + height + " meters.");
    }
}
