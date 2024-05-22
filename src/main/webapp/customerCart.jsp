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
    <h2>Cart Items</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Product Price</th>
            <th>Quantity</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            List<OrderPojo> orderList =(List<OrderPojo>)request.getAttribute("cartItems");
            for(OrderPojo order: orderList){
        %>
        <tr>
            <td><%=order.getProductName()%></td>
            <td><%=order.getProductPrice()%></td>
            <td><%=order.getQuantity()%></td>
            <td>
                <form action="RemoveFromCartControllerServlet">
                <button type="submit" name="remove" value="<%=order.getProductId()%>" class="btn btn-danger btn-sm"><i class="fas fa-times"></i> Remove</button>
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
<div class="con">
    <form action="CustomerOrderAllControllerServlet">
        <button class="btn btn-success ">Order All</button>
    </form>
</div>
</body>
</html>
