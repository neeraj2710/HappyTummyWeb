<%@ page import="org.happytummy.pojo.StaffPojo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 15-05-2024
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food Wala</title>
    <link rel="stylesheet" href="FoodWalaStyles.css" />
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- navbar starts here -->
<div class="mycontainer" style="padding-bottom: 0">
    <div class="mylogo">
        <p>The Food Wala</p>
    </div>
    <nav>
        <ul>
            <li><a href="adminDashboard.html">Home</a></li>
            <li><a href="addStaff.html">Add Staff</a></li>
            <li><a href="SearchFood.jsp">Search Food</a></li>
            <li><a href="ViewFoodControllerServlet">Menu</a></li>
            <li><a href="ViewFoodToUpdateControllerServlet">Update Menu</a></li>
            <li><a href="ViewStaffControllerServlet">View Staff</a></li>
            <li><a href="ViewPendingOrderControllerServlet">Pending Orders</a></li>
            <li><a href="ViewOrderHistoryControllerServlet">Order History</a></li>
        </ul>
    </nav>
</div>
<!-- navbar ends here -->
<div class="container mt-5">
    <h2>Employee Table</h2>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email ID</th>
            <th>Gender</th>
            <th>Role</th>
            <th>Salary</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<StaffPojo> staffList=(List<StaffPojo>)request.getAttribute("staffList");
            for(StaffPojo staff: staffList){
        %>
            <tr>
                <td><%=staff.getId()%></td>
                <td><%=staff.getFname()%></td>
                <td><%=staff.getLname()%></td>
                <td><%=staff.getEmailId()%></td>
                <td><%=staff.getGender()%></td>
                <td><%=staff.getRole()%></td>
                <td><%=staff.getSalary()%></td>
            </tr>
        <%
            }
        %>
        <!-- Add more rows as needed -->
        </tbody>
    </table>
</div>

</body>
</html>
