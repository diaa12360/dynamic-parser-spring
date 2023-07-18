<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!doctype html>
<%@ page isErrorPage = "true" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>History</title>
    <style>
        body {
            display: flex;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f2f2f2;
            flex-wrap: nowrap;
            align-content: flex-start;
            flex-direction: column;
        }

        h1 {
            text-align: center;
            font-family: Arial, sans-serif;
        }

        table {
            border-collapse: collapse;
            width: 80%;
            max-width: 800px;
            margin-top: 20px;
            background-color: #ffffff;
            border:none;
            border-radius: 4px;
        }

        th, td {
            padding: 18px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
            text-align: center;
        }

        thead th {
            background-color: #f2f2f2;
        }

        tbody tr:hover {
            background-color: #f9f9f9;
        }

        @media only screen and (max-width: 600px) {
            table {
                width: 95%;
            }
        }
    </style>
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