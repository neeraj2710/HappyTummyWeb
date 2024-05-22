package org.happytummy.util;

public class UserProfile {
    private static String customerId;
    private static String customerName;
    private static String customerEmail;

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        UserProfile.customerName = customerName;
    }

    public static String getCustomerId() {
        return customerId;
    }

    public static void setCustomerId(String customerId) {
        UserProfile.customerId = customerId;
    }

    public static String getCustomerEmail() {
        return customerEmail;
    }

    public static void setCustomerEmail(String customerEmail) {
        UserProfile.customerEmail = customerEmail;
    }
}
