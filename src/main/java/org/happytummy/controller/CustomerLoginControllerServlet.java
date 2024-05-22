package org.happytummy.controller;

import org.happytummy.model.CustomerModel;
import org.happytummy.pojo.CustomerPojo;
import org.happytummy.util.PasswordEncryption;
import org.happytummy.util.UserProfile;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "CustomerLoginControllerServlet", value = "/CustomerLoginControllerServlet")
public class CustomerLoginControllerServlet extends HttpServlet {
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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            CustomerPojo customer = CustomerModel.validate(username, PasswordEncryption.getEncryptedPassword(password));
            System.out.println(customer);
            if(customer == null) {
                RequestDispatcher rd = req.getRequestDispatcher("index.html");
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
                        "    Invalid credentials.\n" +
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
                rd.include(req, resp);
            }
            else{
                System.out.println(customer);
                UserProfile.setCustomerId(customer.getCustomerId());
                UserProfile.setCustomerName(customer.getCustomerName());
                UserProfile.setCustomerEmail(customer.getCustomerEmail());
                resp.sendRedirect("customerDashboard.html");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}