<%-- 
    Document   : orderhistory
    Created on : Sep 21, 2021, 10:41:22 AM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yellow Moon Shop</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            String role = (String) session.getAttribute("ROLE");
            if (role != null) {
                if (role.equals("Admin")) {
                    response.sendRedirect("error403.html");
                }
            } else {
                response.sendRedirect("login.html");
            }
        %>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <div class="text-center">
                <form action="MainController" method="POST">
                    <input type="number" class="form-control" name="txtSearchOrder" placeholder="Search Order Id..."/>
                    <input type="hidden" name="searchAction" value="searchByOrder"/>
                    <button type="submit" name="btnAction" value="Search">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
            </div><br/>
            
            <table class="table table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Order ID</th>
                        <th scope="col">Date Time</th>
                        <th scope="col">Total</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.ORDERS}" var="dto">
                        <tr>
                            <td>
                                <p>${dto.orderId}</p>
                            </td>
                             <td>
                                <p>${dto.orderDate}</p>
                            </td>
                             <td>
                                <p>${dto.total} đ</p>
                            </td>
                             <td>
                                 <c:url var="orderDetailLink" value="MainController">
                                     <c:param name="btnAction" value="Order Detail"/>
                                     <c:param name="txtOrderId" value="${dto.orderId}"/>
                                 </c:url>
                                 
                                 <a href="${orderDetailLink}" class="btn btn-outline-success">Detail</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
