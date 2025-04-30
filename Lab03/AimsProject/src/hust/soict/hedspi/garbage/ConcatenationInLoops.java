package hust.soict.hedspi.garbage;

import java.util.Random;

public class ConcatenationInLoops {
    public static void main(String[] args) {
        // Using String with + operator
        long startTime = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time with String +: " + (endTime - startTime) + "ms");
        
        // Using StringBuilder
        startTime = System.currentTimeMillis();
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sbd.append(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time with StringBuilder: " + (endTime - startTime) + "ms");
    
        // Using StringBuffer
        startTime = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            sbf.append(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time with StringBuffer: " + (endTime - startTime) + "ms");
    	}
}

