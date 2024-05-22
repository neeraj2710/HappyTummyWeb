<%@ page import="org.happytummy.pojo.StaffPojo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.happytummy.pojo.OrderPojo" %><%--
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
<div class="container mt-5">
    <h2>Pending Orders</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Quantity</th>
            <th>Customer ID</th>
            <th>Order Date & Time</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            List<OrderPojo> orderList =(List<OrderPojo>)request.getAttribute("pendingOrders");
            for(OrderPojo order: orderList){
        %>
        <tr>
            <td><%=order.getOrderId()%></td>
            <td><%=order.getProductName()%></td>
            <td><%=order.getProductPrice()%></td>
            <td><%=order.getQuantity()%></td>
            <td><%=order.getStatus()%></td>
            <td><%=order.getOrderDate()%></td>
            <td>
                <form action="DeliverOrderControllerServlet">
                    <button type="submit" name="deliver" value="<%=order.getCustomerId()%>" class="btn btn-success btn-sm"><i class="fas fa-times"></i>Delivered</button>
                </form>
            </td>
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
