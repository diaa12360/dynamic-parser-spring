<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!doctype html>
<%@ page isErrorPage = "true" %>


<html lang="en">
<head>
    <title>Training Application Form</title>
    <link rel="stylesheet" href="tablePagStyle.css">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<table>
    <thead>
    <tr>
        <c:forEach items="${columnsName}" var="column">
            <th>${column}</th>
        </c:forEach>
    </tr>
    <tbody>
    <c:forEach items="${currentMainData}" var="arr">
        <tr>
            <c:forEach items="${arr}" var="i">
                <td>${i.value}</td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="data" method="GET">
      <div class="pagination">
        <c:forEach var="number" begin="1" end="${totalPages}">
            <button class="pagination-button" name="pageNumber" value="${number}">
                Page ${number}
            </button>
        </c:forEach>
        </div>
    </form>
<div class="container">
    <form action="utility" method="POST">
        <select class="selectItem" name="column" id="1234" name="item">
            <c:forEach items="${columnsName}" var="column">
                <option>${column}</option>
            </c:forEach>
        </select>
        <button class="button" name="button" value="Summation">
            Get Summation
        </button>
        <button class="button" name="button" value="Average">
            Get Average
        </button>
        <button class="button" name="button" value="Column">
            Get Column
        </button>
        <button class="button" name="button" value="BackFromDataPage">
            Back
        </button>
    </form>
    <form action="history" method="POST">
        <button class="button" name="button" value="BackFromDataPage">
            Get History
        </button>
    </form>
</div>
</body>
</html>