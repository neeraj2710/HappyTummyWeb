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
</head>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        /*display: flex;*/
        align-items: center;
        justify-content: center;
        height: 100vh;
        margin: 0; /* Remove default margin */
    }
    .content-container {
        text-align: center;
    }
    .image-container {
        max-width: 300px;
        height: 250px; /* Fixed height for image container */
        overflow: hidden; /* Hide overflow if image is larger */
        display: inline-block;
        margin-top: 30px;
        padding: 0;
        margin-bottom: 0/* Add margin bottom */
    }
    .image-container img {
        width: 100%;
        height: auto;
        object-fit: cover; /* Maintain aspect ratio */
        border-radius: 10px;
    }
    .form-container {
        max-width: 300px;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 10px;
        display: inline-block;
    }
    .container{
        display: flex;
    }
</style>
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
            <li><a href="#">Update Menu</a></li>
            <li><a href="ViewStaffControllerServlet">View Staff</a></li>
            <li><a href="ViewPendingOrderControllerServlet">Pending Orders</a></li>
            <li><a href="ViewOrderHistoryControllerServlet">Order History</a></li>
        </ul>
    </nav>
</div>
<!-- navbar ends here -->
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 content-container">
            <div class="image-container">
                <img src="<%=request.getAttribute("url")%>" class="img-fluid" alt="Placeholder Image">
            </div>
            <div class="form-container">
                <form action="AddFoodControllerServlet">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter your name">
                    </div>
                    <div class="form-group">
                        <label for="price">Price:</label>
                        <input type="text" class="form-control" id="price" name="price" placeholder="Enter the price">
                    </div>
                    <button type="submit" name="url" value="<%=request.getAttribute("url")%>" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>