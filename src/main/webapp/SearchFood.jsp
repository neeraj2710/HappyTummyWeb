<%@ page import="org.happytummy.pojo.StaffPojo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.happytummy.pojo.ProductPojo" %><%--
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
        .card {
            height: 100%;
        }
        .card-img-top {
            object-fit: cover;
            height: 150px; /* Adjust the height as needed */
        }
        .card-body {
            height: 150px; /* Adjust the height as needed */
        }
        .card:hover{
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
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
<h2>Search Food</h2>
<div class="row">
    <div class="col-md-6">
        <div class="input-group mb-3">
            <form action="APIControllerServlet">
                <input type="text" name="search" class="form-control" placeholder="Search for food...">
                <div class="input-group-append">
                    <button class="btn btn-primary" type="submit">Search</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container mt-5" ><div class="row">


    <%
        // Get the data for the cards.



        List<ProductPojo> productList= (List<ProductPojo>) request.getAttribute("products");

        // Generate the cards.
        if(productList!=null){
            for (int i=0;i< productList.size();i++) {
                String name = productList.get(i).getpName().length()>25 ? productList.get(i).getpName().substring(0,21) + "...." : productList.get(i).getpName();
    %>
    <div class="col-md-3 mb-4">
        <div class="card">
            <img src="<%=productList.get(i).getpURL()%>" class="card-img-top" alt="Image">
            <div class="card-body">

                <p> <%=name%></p>
                <form action="SelectedFoodControllerServlet"><button type="submit" name="url" value="<%=productList.get(i).getpURL()%>" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
    <%
            }
        }

    %>

</div> </div>
</body>
</html>
