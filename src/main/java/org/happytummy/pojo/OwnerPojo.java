package org.happytummy.pojo;

import org.happytummy.util.OwnerProfile;

public class OwnerPojo {
    private  String username;
    private String password;
    private String securityKey;
    private String emailId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "OwnerPojo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", securityKey='" + securityKey + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
