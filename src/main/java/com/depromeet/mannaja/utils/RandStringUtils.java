package com.depromeet.mannaja.utils;

import java.util.Random;

public class RandStringUtils {
    public static String generateRandomString(){
        StringBuffer stringBuffer = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    stringBuffer.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    stringBuffer.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    stringBuffer.append((rnd.nextInt(10)));
                    break;
            }
        }

        return stringBuffer.toString();
    }
}
