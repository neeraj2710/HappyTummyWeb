package org.happytummy.controller;

import org.happytummy.model.CustomerModel;
import org.happytummy.pojo.CustomerPojo;
import org.happytummy.util.Mailer;
import org.happytummy.util.PasswordEncryption;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegistrationControllerServlet", value = "/RegistrationControllerServlet")
public class RegistrationControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String address = req.getParameter("address");
        String mobile = req.getParameter("mobile").trim();
        if(!password.equals(confirmPassword)) {
            out.println("<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "  <title>Alert Box Example</title>\n" +
                    "  <!-- Bootstrap CSS -->\n" +
                    "  <link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<div class=\"container mt-5\">\n" +
                    "  <div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
                    "    Passwords dont match\n" +
                    "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
                    "      <span aria-hidden=\"true\">&times;</span>\n" +
                    "    </button>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<!-- Bootstrap JS (optional if you need to close the alert programmatically) -->\n" +
                    "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    "</body>\n" +
                    "</html>");
            RequestDispatcher rd = req.getRequestDispatcher("signup.html");
            rd.include(req, resp);
        }
        CustomerPojo customer = new CustomerPojo();
        customer.setCustomerAddress(address);
        customer.setCustomerName(name);
        customer.setCustomerEmail(email);
        customer.setCustomerPhone(Integer.parseInt(mobile));
        customer.setCustomerPassword(PasswordEncryption.getEncryptedPassword(password));
        try{
            boolean result=CustomerModel.addCustomer(customer);
            if(result){
                Map<String,String> map=new HashMap<>();
                map.put("emailId","happytummy.foodpartner@gmail.com");
                map.put("securityKey","dsqa sqxm yhki aglv");
                Mailer.sendMail(map,customer);
            }
            resp.sendRedirect("index.html");
        } catch (SQLException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}