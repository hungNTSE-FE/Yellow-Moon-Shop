<%-- 
    Document   : orderdetail
    Created on : Sep 20, 2021, 3:41:37 PM
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
        <c:set value="${requestScope.ORDER}" var="order"/>
        <c:set value="${requestScope.ORDERDETAIL}" var="orderDetail"/>
        <div class="container">
            <h3 class="title">Order Detail</h3>
            <div class="row">
                <div class="col-sm">
                    <label>Order ID</label>
                    <input type="text" name="txtOrderId" value="${order.orderId}"
                           class="form-control" readonly="true"><br/>
                    
                    <label>Customer Name</label>
                    <input type="text" name="txtCustomerName" value="${order.customerName}"
                           class="form-control" readonly="true"><br/>
                    
                    <label>Phone Number</label>
                    <input type="text" name="txtPhoneNumber" value="${order.phoneNumber}"
                           class="form-control" readonly="true"><br/>
                    
                    <label>Shipping Address</label>
                    <input type=="text" name="txtAddress" value="${order.address}"
                           class="form-control" readonly="true"/><br/>
                    
                    <label>Payment method</label>
                    <c:forEach items="${requestScope.PAYMENTMETHODWITHID}" var="paymentMethod">
                        <input type=="text" name="txtPaymentMethod" value="${paymentMethod.value}"
                           class="form-control" readonly="true"/><br/>
                    </c:forEach>
                    
                    
                    <label>Payment status</label>
                    <c:forEach items="${requestScope.PAYMENTSTATUS}" var="paymentStatus">
                        <input type=="text" name="txtPaymentStatus" value="${paymentStatus.value}"
                           class="form-control" readonly="true"/><br/>
                    </c:forEach>
                    
                    <label>Order Date</label>
                    <input type="text" name="txtOrderDate" value="${order.orderDate}"
                           class="form-control" readonly="true"/><br/>
                </div>
            </div>
                           
            <!--Cake list here-->
            
            <table class="table table-hover">
                <thead class="text-muted">
                    <tr>
                        <th scope="col">Cake Image</th>
                        <th scope="col">Cake Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${requestScope.PRODUCTS != null}">
                        <c:if test="${not empty requestScope.PRODUCTS}">
                            <c:forEach items="${requestScope.PRODUCTS}" var="dto">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>
                                        <img src="${dto.cakeImage}" class="img-thumbnail img-sm"/>
                                    </td>
                                    <td>
                                        <h6>${dto.cakeName}</h6>
                                    </td>
                                    <!--Quantity here-->
                                    <td>
                                        <input class="form-control" type="text" name="txtQuantity"
                                               value="${dto.quantity}" readonly="true"/>
                                    </td>
                                    <!--Price here-->
                                    <td>
                                        <h6>${dto.quantity}</h6>
                                    </td>
                                </tr>
                            </form>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </tbody>
            </table>
        </div>
        <hr>
        <b><p class="text-success">${requestScope.SUCCESS}</p></b>
        <b><a href="${pageContext.request.contextPath}/">Continues shopping</a></b>
    </body>
</html>
