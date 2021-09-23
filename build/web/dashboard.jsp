<%-- 
    Document   : dashboard
    Created on : Sep 6, 2021, 8:41:49 PM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yellow Moon Shop</title>
        <link rel="stylesheet" href="resources/css/index.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <br/>
        <!-- Content-->
        <div style="margin-top: 30px; margin-left: 10px; margin-right: 30px">
            <div class="row">
                <!--Search by price-->
                <div class="col-3">
                    <h2>Price</h2>
                    <form action="MainController" method="POST">
                        <input type="number" min="0" name="txtMinPrice" placeholder="Min: " value="${param.txtMinPrice}"/><br/>
                        <input type="number" min="0" name="txtMaxPrice" placeholder="Max: " value="${param.txtMaxPrice}"/><br/>
                        <input type="hidden" name="searchAction" value="searchByPrice"/>
                        <input type="submit" name="btnAction" value="Search"/>
                    </form>
                </div>
                <!--End search by price-->
                <!--Cake List-->
                <div class="col">
                    <c:if test="${requestScope.selectedCategory != null}" var="checkSelectedCate">
                        <h2 style="text-align: center">${selectedCategory}</h2>
                    </c:if>
                    <c:if test="${!checkSelectedCate}">
                        <h2 style="text-align: center">All food</h2>
                    </c:if>
                    <br/><br/>
                    <c:if test="${requestScope.INFO != null}">
                        <c:if test="${not empty requestScope.INFO}" var="checkListEmpty">
                            <c:forEach items="${requestScope.INFO}" var="dto" varStatus="rowCounter">
                                <c:if test="${rowCounter.count % 4 == 1}">
                                    <div class="row" style="margin-top: 20px">
                                </c:if>   
                                        <div class="column">
                                            <div class="card">
                                                <div class="container">
                                                    <h4><b>${dto.cakeName}</b></h4>
                                                    <img src="${dto.cakeImage}" onerror="this.style.display='none'">
                                                    <p>${dto.description}</p>
                                                    <p class="price">
                                                        <fmt:formatNumber value="${dto.price}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                                                    </p>
                                                    
                                                    <c:if test="${sessionScope.ROLE == 'Admin'}" var="checkAdminRole">
                                                        <form action="MainController" method="POST">
                                                            <input type="hidden" value="${dto.cakeId}" name="txtCakeId"/>
                                                            <input type="submit" name="btnAction" value="Delete" onclick="return confirm('Are you sure you want to delete?')" style="margin-left: 0px" />
                                                        </form>
                                                        <form action="MainController" method="POST">
                                                            <input type="hidden" value="${dto.cakeId}" name="txtCakeId"/>
                                                            <input type="hidden" name="btnAction" value="Detail"/>
                                                            <input type="submit" value="Update" style="margin-left: 0px"/>
                                                        </form>
                                                    </c:if>
                                                    <c:if test="${!checkAdminRole}">
                                                        <form action="MainController" method="POST">
                                                            <input type="hidden" value="${dto.cakeId}" name="txtCakeId"/>
                                                            <input type="hidden" value="${dto.cakeName}" name="txtCakeName"/>
                                                            <input type="hidden" value="${dto.cakeImage}" name="txtImage"/>
                                                            <input type="hidden" value="${dto.description}" name="txtDescription"/>
                                                            <input type="hidden" value="${dto.price}" name="txtPrice"/>
                                                            <input type="hidden" value="${dto.expirationDate}" name="txtExpirationDate"/>
                                                            <input type="submit" name="btnAction" value="Add to cart" style="margin-left: 0px"/>
                                                        </form>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    <c:if test="${rowCounter.count % 4 == 0 || rowCounter.count == fn:length(requestScope.INFO)}">
                                    </div>                     
                                    </c:if>           
                            </c:forEach>
                            <!--Pagination-->
                            <jsp:include page="pagination.jsp"/>
                            <!--End Pagination-->
                        </c:if>
                        <c:if test="${!checkListEmpty}">
                            <h4 style="text-align: center">No product found.</h4>
                        </c:if>
                    </c:if>
                </div>
                <!--End Cake List-->
            </div>
        </div>
    </body>
</html>
