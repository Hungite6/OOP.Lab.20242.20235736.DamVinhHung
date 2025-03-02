package Lab1;

import javax.swing.JOptionPane;

public class Bai226EquationSolver {
    public static void main(String[] args) {
        while (true) {
            String choice = JOptionPane.showInputDialog(
                "CHỌN CÔNG CỤ:\n" +
                "1. Giải phương trình bậc nhất (1 ẩn)\n" +
                "2. Giải hệ phương trình bậc nhất (2 ẩn)\n" +
                "3. Giải phương trình bậc hai (1 ẩn)\n" +
                "4. Thoát\n" +
                "Nhập lựa chọn:"
            );
            
            if (choice == null) break;
            
            switch (choice) {
                case "1":
                    solveLinearEquation();
                    break;
                case "2":
                    solveSystemOfEquations();
                    break;
                case "3":
                    solveQuadraticEquation();
                    break;
                case "4":
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Lựa chọn không hợp lệ! Vui lòng nhập lại.");
            }
        }
    }

    private static void solveLinearEquation() {
        String inputA = JOptionPane.showInputDialog("Nhập hệ số a:");
        String inputB = JOptionPane.showInputDialog("Nhập hệ số b:");
        try {
            double a = Double.parseDouble(inputA);
            double b = Double.parseDouble(inputB);
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Phương trình vô nghiệm!");
            } else {
                double x = -b / a;
                JOptionPane.showMessageDialog(null, "Nghiệm: x = " + x);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập không hợp lệ!");
        }
    }

    private static void solveSystemOfEquations() {
        String inputA11 = JOptionPane.showInputDialog("Nhập a11:");
        String inputA12 = JOptionPane.showInputDialog("Nhập a12:");
        String inputB1 = JOptionPane.showInputDialog("Nhập b1:");
        String inputA21 = JOptionPane.showInputDialog("Nhập a21:");
        String inputA22 = JOptionPane.showInputDialog("Nhập a22:");
        String inputB2 = JOptionPane.showInputDialog("Nhập b2:");
        
        try {
            double a11 = Double.parseDouble(inputA11);
            double a12 = Double.parseDouble(inputA12);
            double b1 = Double.parseDouble(inputB1);
            double a21 = Double.parseDouble(inputA21);
            double a22 = Double.parseDouble(inputA22);
            double b2 = Double.parseDouble(inputB2);

            double D = a11 * a22 - a12 * a21;
            double Dx = b1 * a22 - b2 * a12;
            double Dy = a11 * b2 - a21 * b1;

            if (D == 0) {
                if (Dx == 0 && Dy == 0) {
                    JOptionPane.showMessageDialog(null, "Hệ phương trình có vô số nghiệm!");
                } else {
                    JOptionPane.showMessageDialog(null, "Hệ phương trình vô nghiệm!");
                }
            } else {
                double x1 = Dx / D;
                double x2 = Dy / D;
                JOptionPane.showMessageDialog(null, "Nghiệm: x1 = " + x1 + ", x2 = " + x2);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập không hợp lệ!");
        }
    }

    private static void solveQuadraticEquation() {
        String inputA = JOptionPane.showInputDialog("Nhập hệ số a:");
        String inputB = JOptionPane.showInputDialog("Nhập hệ số b:");
        String inputC = JOptionPane.showInputDialog("Nhập hệ số c:");
        
        try {
            double a = Double.parseDouble(inputA);
            double b = Double.parseDouble(inputB);
            double c = Double.parseDouble(inputC);
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Đây không phải là phương trình bậc hai!");
                return;
            }
            
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                JOptionPane.showMessageDialog(null, "Phương trình vô nghiệm!");
            } else if (delta == 0) {
                double x = -b / (2 * a);
                JOptionPane.showMessageDialog(null, "Phương trình có nghiệm kép: x = " + x);
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                JOptionPane.showMessageDialog(null, "Nghiệm: x1 = " + x1 + ", x2 = " + x2);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dữ liệu nhập không hợp lệ!");
        }
    }
}
