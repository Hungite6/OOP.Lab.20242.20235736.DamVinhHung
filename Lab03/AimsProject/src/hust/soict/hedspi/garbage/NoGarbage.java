package hust.soict.hedspi.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) {
        String filename = "src/hust/soict/hedspi/garbage/text.txt"; // Thay bằng file lớn để thấy rõ hiệu ứng
        byte[] inputBytes = { 0 };
        long startTime, endTime;
        
        try {
            inputBytes = Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        startTime = System.currentTimeMillis();
        
/*      //Using StringBuilder
        StringBuilder outputStringBuilder = new StringBuilder();
        for (byte b : inputBytes) {
            outputStringBuilder.append((char)b);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time to process with StringBuilder: " + (endTime - startTime) + "ms");
*/        
        // Using StringBuffer
        StringBuffer outputStringBuffer = new StringBuffer();
        for (byte b : inputBytes) {
            outputStringBuffer.append((char)b);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time to process with StringBuffer: " + (endTime - startTime) + "ms");
    }
    
}