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
    <style>
        .con{
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
<!-- navbar starts here -->
<div class="mycontainer" style="padding-bottom: 0">
    <div class="mylogo">
        <p>The Food Wala</p>
    </div>
    <nav>
        <ul>
            <li><a href="customerDashboard.html">Home</a></li>
            <li><a href="CustomerMenuControllerServlet">Menu</a></li>
            <li><a href="CustomerCartControllerServlet">Cart</a></li>
            <li><a href="CustomerPendingOrderServlet">Pending Orders</a></li>
            <li><a href="CustomerOrderHistoryServlet">Order History</a></li>
        </ul>
    </nav>
</div>
<!-- navbar ends here -->
<div class="container mt-5">
    <h2>Pending Orders</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Quantity</th>
            <th>Status</th>
            <th>Order Date & Time</th>
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
