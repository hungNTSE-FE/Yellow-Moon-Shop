<%-- 
    Document   : createCake
    Created on : Sep 13, 2021, 4:28:28 PM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Food</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body class="m-2">
        <%
            String role = (String) session.getAttribute("ROLE");
            if (role == null) {
                response.sendRedirect("");
            } else if (!role.equals("Admin")) {
                response.sendRedirect("");
            }
        %>
        <h1>Yellow Moon Shop</h1>
        <h2>Create new food</h2>
        <br/>
        <!-- setting default value for convenience--> 
        <form action="MainController" method="POST" enctype='multipart/form-data'>
            <h3>
                <font color="red">
                ${requestScope.ERROR}
                </font>
            </h3>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Cake Name</b></label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" name="txtCakeName" value="${param.txtCakeName}" required="true"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Description</b></label>
                <div class="col-sm-6">
                    <textarea class="form-control" name="txtDescription" 
                          placeholder="Description.." rows="3">${param.txtDescription}</textarea>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Select an image:</b></label>
                <div class="col-sm-6">
                    <input type="file" class="form-control-file" name="image"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Quantity: </b></label>
                <div class="col-sm-6">
                    <input type="number" class="form-control" name="txtQuantity" value="${param.txtQuantity}" min="0" required/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Price: </b></label>
                <div class="col-sm-6">
                    <input type="number" class="form-control" name="txtPrice" value="${param.txtPrice}" min="0" required/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Expiration Date:  </b></label>
                <div class="col-sm-6">
                    <input type="date" class="form-control" name="txtExpirationDate" required/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"><b>Category: </b></label>
                <div class="col-sm-6">
                    <select class="form-control" name="txtCategoryId">
                        <c:forEach items="${sessionScope.CATEGORIES}" var="category">
                            <option value="${category.key}" selected>${category.value}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="hidden" name="btnAction" value="Create"/>
            <input type="submit" class="btn btn-primary" value="Create"/>
        </form>
        <br/>
        <a href="${pageContext.request.contextPath}/">Return to home page</a>
        
    </body>
</html>
