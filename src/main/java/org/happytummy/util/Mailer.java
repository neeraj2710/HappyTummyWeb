package org.happytummy.util;


import org.happytummy.pojo.CustomerPojo;
import org.happytummy.pojo.OrderPojo;
import org.happytummy.pojo.StaffPojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class MyAuthenticator extends Authenticator {

    private String userName;
    private String password;

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.userName, this.password);
    }

    public MyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}

public class Mailer {

    private static Properties prop;

    static {
        prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    //    for staff
    public static void sendMail(Map<String, String> emailCredentials, StaffPojo staff) throws MessagingException {
        final String usrName = emailCredentials.get("emailId");
        final String password = emailCredentials.get("securityKey");
        MyAuthenticator myAuth = new MyAuthenticator(usrName, password);
        Session session = Session.getInstance(prop, myAuth);
        Message message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(staff.getEmailId()));
        String emailSubject = "Staff Details";
        String emailBody = "Dear" + staff.getFname() + ",\n"
                + "\n"
                + "I hope this mail finds you well. I am writing this to provide you with the necessary"
                + "\n"
                + "Staff ID:" + staff.getId() + "\n"
                + "Company Name: Happy Tummy\n"
                + "Email ID:" + staff.getEmailId() + "\n"
                + "These details are crucial for internal record-keeping and ensuring efficient communication" + "\n"
                + "Best Regards,\n"
                + "Happy Tummy";
        message.setSubject(emailSubject);
        message.setText(emailBody);
        Transport.send(message);
        System.out.println("mail sent successfully");
    }

    //    for customer registration
    public static void sendMail(Map<String, String> emailCredentials, CustomerPojo customer) throws MessagingException {
        final String usrName = emailCredentials.get("emailId");
        final String password = emailCredentials.get("securityKey");
        MyAuthenticator myAuth = new MyAuthenticator(usrName, password);
        Session session = Session.getInstance(prop, myAuth);
        Message message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getCustomerEmail()));
        String emailSubject = "Welcome to Happy tummy App - your ultimate food ordering companion!";
        String emailBody = "Dear " + customer.getCustomerName() + ",\n"
                + "\n"
                + "Welcome to Happy Tummy App! We're excited to have you as a new member of our food loving community."
                + "\n" + "Here are your account details" + "\n"
                + "your ID:" + customer.getCustomerId() + "\n"
                + "your Name:" + customer.getCustomerName() + "\n"
                + "Email ID(LoginId):" + customer.getCustomerEmail() + "\n"
                + "your password:" + PasswordEncryption.getDecryptedPassword(customer.getCustomerPassword())
                + "\n"
                + "Happy Ordering!" + "\n"
                + "Best Regards,\n"
                + "\n"
                + "Happy Tummy!";
        message.setSubject(emailSubject);
        message.setText(emailBody);
        Transport.send(message);
        System.out.println("mail sent succssfully");
    }

    //    mail from seller to customer
    public static void sendMail(Map<String, String> emailCredentials, List<OrderPojo> orderList, double total,String ordId)throws MessagingException{
        final String usrName = emailCredentials.get("emailId");
        final String password = emailCredentials.get("securityKey");
        MyAuthenticator myAuth = new MyAuthenticator(usrName, password);
        Session session = Session.getInstance(prop, myAuth);
        Message message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(UserProfile.getCustomerEmail()));

        double taxRate = 0.075;
        double taxAmt = total * taxRate;
        double billAmt = total + taxAmt;
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String orderDate = sdf.format(today);

        String emailSubject = "Order confirmation Mail";
        String emailBody = "Dear " + UserProfile.getCustomerName() + ",\n";
        emailBody += "\nfollowing are your order details:";
        emailBody += "\nOrder ID:"+ordId;
        for(OrderPojo order : orderList){
            emailBody += "\nProduct Name:"+order.getProductName();
            emailBody += "\nQuantity:"+order.getQuantity();
            emailBody += "\nPrice:"+order.getProductPrice();
        }
        emailBody += "\nTax Amount:"+taxAmt;
        emailBody += "\nBill Amount:"+billAmt;
        emailBody += "\nCompany Email:happytummy.foodpartner@gmail.com";
        emailBody += "\nOrder Date:"+orderDate;
        emailBody += "\nThank you for ordering!!\n\nHappy Hunger";
        message.setSubject(emailSubject);
        message.setText(emailBody);
        Transport.send(message);
        System.out.println("mail sent successfully");
    }
}

