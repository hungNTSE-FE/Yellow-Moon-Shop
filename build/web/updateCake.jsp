<%-- 
    Document   : updateCake
    Created on : Sep 14, 2021, 8:42:58 PM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Cake</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body class="m-2">
        <%
            String role = (String) session.getAttribute("ROLE");
            if (role == null) {
                response.sendRedirect("login.html");
            } else if (!role.equals("Admin")) {
                response.sendRedirect("error403.html");
            }
        %>
        <h1>Yellow Moon Shop</h1>
        <h3>Update Cake</h3>
        <br/>
        <!--Setting default value for convenience-->
        <form action="MainController" method="POST" enctype="multipart/form-data">
            <font color="red">
                ${requestScope.ERROR}
            </font>
            <c:set var="dto" value="${requestScope.INFO}"/>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Cake Name: </b></label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="txtCakeName" value="${dto.cakeName}" required="true"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Description</b></label>
                <div class="col-sm-6">
                    <textarea class="form-control" name="txtDescription" 
                          placeholder="Description.." rows="3">${dto.description}</textarea>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Select an image:</b></label>
                <div class="col-sm-6">
                    <input type="file" class="form-control-file" name="image" value="${dto.cakeImage}"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Quantity: </b></label>
                <div class="col-sm-6">
                    <input type="number" class="form-control" name="txtQuantity" value="${dto.quantity}" min="0" required/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Price: </b></label>
                <div class="col-sm-6">
                    <input type="number" class="form-control" name="txtPrice" value="${dto.price}" min="0" required/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Expiration Date:  </b></label>
                <div class="col-sm-6">
                    <input type="date" class="form-control" name="txtExpirationDate" value="${dto.expirationDate}" required/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Create Date:  </b></label>
                <div class="col-sm-6">
                    <input type="date" class="form-control" name="txtCreateDate" value="${dto.createDate}" readonly="true"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Category: </b></label>
                <div class="col-sm-6">
                    <select class="form-control" name="txtCategoryId">
                        <c:forEach items="${sessionScope.CATEGORIES}" var="category">
                            <c:if test="${category.key != dto.categoryId}" var="checkCurrentCategory">
                                <option value="${category.key}">${category.value}</option>
                            </c:if>
                            <c:if test="${!checkCurrentCategory}">
                                <option value="${category.key}" selected>${category.value}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Status: </b></label>
                <div class="col-sm-6">
                    <select class="form-control" name="txtStatusId">
                        <c:forEach items="${requestScope.STATUS}" var="status">
                            <c:if test="${status.key != dto.statusId}" var="checkCurrentStatus">
                                <option value="${status.key}">${status.value}</option>
                            </c:if>
                            <c:if test="${!checkCurrentStatus}">
                                <option value="${status.key}" selected>${status.value}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="hidden" name="txtCakeId" value="${dto.cakeId}"/>
            <input type="submit" class="btn btn-primary" name="btnAction" value="Update"/>
        </form>
        <br/>
        <a href="${pageContext.request.contextPath}/">Return to home page</a>
    </body>
</html>
