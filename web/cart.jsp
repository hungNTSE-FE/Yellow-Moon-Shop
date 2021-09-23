<%-- 
    Document   : cart
    Created on : Sep 16, 2021, 11:40:29 AM
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            String role = (String) session.getAttribute("ROLE");
            if (role != null) {
                if (role.equals("Admin")) {
                    response.sendRedirect("error403.html");
                }
            }
        %>
        <jsp:include page="header.jsp"/>
        <c:set var="cart" value="${sessionScope.shoppingCart.cart}"/>
        <c:set var="cartObj" value="${sessionScope.shoppingCart}"/>
        
        <div class="container">
<!--            <div class="card">-->
                <table class="table">
                    <thead>
                        <tr class="text-center">
                            <th>Cake Image</th>
                            <th>Cake Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody class="text-center">
                        <c:forEach items="${cart}" var="dto">
                        <form action="MainController" method="POST">
                            <tr>
                                <td>
                                     <img src="${dto.value.cakeImage}" class="img-thumbnail img-sm">
                                </td>
                                <td>
                                    <h6 class="title text truncate">${dto.value.cakeName}</h6>
                                    <h6>Expiration Date: ${dto.value.expirationDate}</h6>
                                </td>
                                <!--Quantity here-->
                                <td>
                                    <input class="form-control" type="number" name="txtQuantity" min="0"
                                           value="${dto.value.quantity}"/>
                                </td>
                                <!--Price here-->
                                <td>
                                        <h6>${dto.value.price} đ</h6>
                                </td>
                                <td class="text-center">
                                    <div class="flex flex-row">
                                        <input type="hidden" name="txtCakeId" value="${dto.value.cakeId}"/>
                                        <input type="submit" name="btnAction" value="Update Cart" class="btn btn-outline-success" style="margin-bottom: 5px;"/>
                                    
                                        <c:url var="deleteLink" value="MainController">
                                            <c:param name="btnAction" value="Delete Cart"/>
                                            <c:param name="txtCakeId" value="${dto.value.cakeId}"/>
                                        </c:url>
                                    
                                        <a href="${deleteLink}" onclick="return confirm('Are you sure to delete this cake?');"
                                            class="btn  btn-outline-danger">Remove</a>
                                    </div>
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>
<!--            </div>-->
            <a href="${pageContext.request.contextPath}/">Continue shopping</a>
            <b class="float-right">
                Total: <fmt:formatNumber value="${cartObj.getTotal()}"
                                  type="currency" maxFractionDigits="0" pattern="VND #,### đ"/>
            </b>
            <br/>
            <b><a href="${pageContext.request.contextPath}/checkout.jsp">Checkout</a></b>
            
            <b><p class="text-success">${requestScope.SUCCESS}</p></b>
            
            <b><p class="text-danger">${requestScope.ERROR}</p></b>

        </div>
    </body>
</html>
