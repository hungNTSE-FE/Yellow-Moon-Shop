<%-- 
    Document   : pagination
    Created on : Sep 14, 2021, 3:20:18 PM
    Author     : hungn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class='pagination'>
            <c:if test="${requestScope.PAGENUM != null}" var="checkPageList">
                <!--check if page number is > 1-->
                <c:if test="${requestScope.PAGENUM > 1}">
                    <c:forEach var="i" begin="1" end="${requestScope.PAGENUM}">
                        <c:if test="${i == requestScope.SELECTEDPAGE}" var="checkCurrentPage">
                            <u>${i}</u>
                        </c:if>
                        <c:if test="${!checkCurrentPage}">
                            <form action="MainController" method="POST">
                                <input type="hidden" name="btnAction" value="Search"/>
                                <!--Pass previous search data to servlet-->
                                <c:if test="${requestScope.previousSearch == 'CATEGORY'}">
                                    <input type="hidden" name="txtCategoryId" value="${sessionScope.selectedCategory.categoryId}"/>
                                    <input type="hidden" name="searchAction" value="searchByCategory"/>
                                </c:if>
                                <c:if test="${requestScope.previousSearch == 'PRICE'}">
                                    <input type="hidden" name="txtMinPrice" value="${param.txtMinPrice}"/>
                                    <input type="hidden" name="txtMaxPrice" value="${param.txtMaxPrice}"/>
                                    <input type="hidden" name="searchAction" value="searchByPrice"/>
                                </c:if>
                                <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                <input type="hidden" name="SELECTEDPAGE" id="selectedPage" value="${i}"/>
                                <a href="#" onclick="this.closest('form').submit();return false;">
                                    ${i}
                                </a>
                            </form>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:if>
        </div>
        <!--End pagination-->
    </body>
</html>
