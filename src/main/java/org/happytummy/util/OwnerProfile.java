package org.happytummy.util;

public class OwnerProfile {
    private static String username;
    private static String securityKey;
    private static String emailId;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        OwnerProfile.username = username;
    }
    public static String getSecurityKey() {
        return securityKey;
    }

    public static void setSecurityKey(String securityKey) {
        OwnerProfile.securityKey = securityKey;
    }

    public static String getEmailId() {
        return emailId;
    }

    public static void setEmailId(String emailId) {
        OwnerProfile.emailId = emailId;
    }
}
