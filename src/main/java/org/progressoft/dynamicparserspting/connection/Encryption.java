package org.progressoft.dynamicparserspting.connection;

public class Encryption {
    public static int encrypt(String password){
        StringBuilder s = new StringBuilder();
        for (char c: password.toCharArray()) {
            s.append((char)(c + 2));
        }
        return s.toString().hashCode();
    }
}
