package hust.soict.hedspi.garbage;

import java.nio.file.*;
import java.io.*;

public class GarbageCreator {
    public static void main(String[] args) throws IOException {
        String filename = "test.exe"; // test.exe là tên hoặc đường dẫn tới file
        byte[] inputBytes = {0} ;
        long startTime, endTime;
        
        inputBytes = Files.readAllBytes(Paths.get(filename));
        startTime = System.currentTimeMillis();
        
        // sử dụng StringBuffer 
        startTime = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : inputBytes) {
            stringBuffer.append((char)b);
        }
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}