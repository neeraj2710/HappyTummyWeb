package org.happytummy.util;

import java.util.Base64;

public class PasswordEncryption {
    //    Returning encrypted password
    public static String getEncryptedPassword(String pwd){
        return Base64.getEncoder().encodeToString(pwd.getBytes());
    }

    //    Returning original Password
    public static String getDecryptedPassword(String pwd){
        return new String(Base64.getDecoder().decode(pwd.getBytes()));
    }
}
