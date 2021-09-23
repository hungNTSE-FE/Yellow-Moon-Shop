<%-- 
    Document   : header.jsp
    Created on : Sep 14, 2021, 11:42:41 AM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/index.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </head>
    <body>
        <!-- Welcome and login/logout section -->
        <div class="jumbotron text-center" style="margin-bottom:0">
            <c:if test="${sessionScope.EMAIL != null}" var="checkLoggedIn">
                <div style="float: right; text-align: right">
                    <i>Welcome, ${sessionScope.FULLNAME}</i><br/>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="btnAction" value="Logout"/>
                        <a href="#" onclick="this.closest('form').submit();return false;">
                            Logout
                        </a>  
                    </form>
                </div>
            </c:if>
            <c:if test="${!checkLoggedIn}">
                <div style="float: right">
                    <a href="login.html"><b>Login</b></a>
                </div>
            </c:if>
            <h1>Yellow Moon Shop</h1>
        </div>
        <!-- End welcome and login/logout section -->
        <!-- Navigation bar -->
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <c:forEach items="${sessionScope.CATEGORIES}" var="cate">
                        <form method="POST" action="MainController">
                            <input type="hidden" name="btnAction" value="Search"/>
                            <input type="hidden" name="searchAction" value="searchByCategory"/>
                            <input type="hidden" name="txtCategoryId" value="${cate.key}"/>
                            <input type="hidden" name="txtCategoryName" value="${cate.value}"/>
                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="this.closest('form').submit();return false;">
                                    ${cate.value}
                                </a>  
                            </li>
                        </form>
                    </c:forEach>
                    <c:if test="${sessionScope.EMAIL != null}">
                        <form method="POST" action="MainController">
                            <input type="hidden" name="btnAction" value="Order History"/>
                            <li class="nav-item">
                                <a class="nav-link" href="#" onclick="this.closest('form').submit();return false;">
                                    Order History
                                </a>  
                            </li>
                        </form>
                    </c:if>
                </ul>
            </div>  
            <div class="search-container">
                <form action="MainController" method="POST">
                    <input type="hidden" name="btnAction" value="Search"/>
                    <input type="text" placeholder="Food name..." name="txtSearch">
                    <button type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
            </div>
            <c:if test="${sessionScope.ROLE == 'Admin'}" var="checkRole">
                <a class="nav-link" href="createCake.jsp">Create</a>
            </c:if>
            <c:if test="${!checkRole}">
                <button onclick="location.href = 'cart.jsp'"><i class="fa fa-shopping-cart" aria-hidden="true">Cart</i></button>
            </c:if>
        </nav>
        <!-- End navigation bar -->
    </body>
</html>
