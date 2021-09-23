<%-- 
    Document   : checkout
    Created on : Sep 20, 2021, 4:20:53 PM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yellow Moon Shop</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:set var="cartObj" value="${sessionScope.shoppingCart}"/>
        <c:if test="${not empty sessionScope.shoppingCart}">
            <div class="container">
                <form action="MainController" method="POST">
                    <div>
                        <h2 class="text-center p-2">Checkout</h2>
                        <table class="table table-bordered text-center">
                            <thead class="thead-light">
                                <tr>
                                    <th scope="col">Cake Image</th>
                                    <th scope="col">Cake Name</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${sessionScope.shoppingCart.cart}" varStatus="counter">
                                    <tr>
                                        <td>
                                            <img src="${dto.value.cakeImage}" class="img-thumbnail img-sm"/>
                                        </td>
                                        <td>
                                            <p>${dto.value.cakeName}</p>
                                        </td>
                                        <td>
                                            <p>${dto.value.quantity}</p>
                                        </td>
                                        <td>
                                            <p>${dto.value.price} đ</p>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <h4 class="text-right"><b>Total: ${cartObj.getTotal()} đ</b></h4>
                        <hr>
                        <h3 class="text-center">Customer Details</h3>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Payment Method</label>
                            <div class="col-sm-10">
                                <select name="txtPaymentMethodId" class="form-control">
                                    <c:forEach items="${sessionScope.PAYMENTMETHODS}" var="payment">
              
                                        <option value="${payment.key}">${payment.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                         
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Customer Name</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtCustomerName" required="true" maxlength="200"
                                       value="${sessionScope.FULLNAME}" class="form-control"/>
                            </div>
                        </div>
                        
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Phone Number</label>
                            <div class="col-sm-10">
                                <input type="tel" name="txtPhoneNumber" required="true" maxlength="12"
                                       value="${sessionScope.USER.phoneNumber}" class="form-control"
                                       placeholder="ex. 0123456789" pattern="[0-9]{10}"/>
                            </div>
                        </div>
                        
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Address</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtAddress" required="true" maxlength="200"
                                       value="${sessionScope.USER.address}" class="form-control"/>
                            </div>
                        </div>
                        <button type="submit" name="btnAction" value="Check Out" class="btn btn-success">Proceed</button>
                        <hr>
                        <b><a href="${pageContext.request.contextPath}/">Continue shopping</a></b>              
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${empty sessionScope.shoppingCart}">
            <c:redirect url="cart.jsp"></c:redirect>
        </c:if>
    </body>
</html>
