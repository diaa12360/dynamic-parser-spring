<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!doctype html>
<%@ page isErrorPage = "true" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Scanner" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Data Table Example</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" href="columnDataStyle.css">
</head>
<body>
  <div class="container">
    <table>
        <thead>
        <tr>
            <th>${columnName}</th>
        </tr>
        </thead>
        <tbody>
                <c:forEach items="${currentData}" var="data">
                    <tr><td>${data}</td><tr>
                </c:forEach>
        </tbody>
    </table>
    <form action="utility" method="GET">
      <div class="pagination">
        <c:forEach var="number" begin="1" end="${totalPages}">
            <button class="pagination-button" name="pageNumber" value="${number}">
                Page ${number}
            </button>
        </c:forEach>
        </div>
    </form>
      </div>

       <form action="utility" method="POST">
            <button class="button" name="button" value="BackFromResultPage">
                Back
            </button>
       </form>
</body>
</html>
