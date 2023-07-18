<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!doctype html>
<%@ page isErrorPage = "true" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="historyStyle.css">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>History</title>
</head>
<body>

    <h1>Data History</h1>
    <table>
        <thead>
            <tr>
                <c:forEach items="${requestScope.DbColumnNames}" var="column">
                    <th>${column}</th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.tableData}" var="arr">
                <tr>
                    <c:forEach items="${arr}" var="i">
                        <td>${i}</td>
                   </c:forEach>
             </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>