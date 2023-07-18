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
    <style>
        /* Body */
        body {
            font-family: Arial, sans-serif;
            margin: -20px auto;
        }

        /* Container */
        .container {
            margin: 0 auto;
        }

        /* Table */
        table th,
        table td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        table th {
            background-color: #f2f2f2;
            text-align: left;
        }

        table tr:hover {
            background-color: #e6e6e6;
        }
        table {
            border-collapse: collapse;
            width: 90%;
            margin: auto;
            margin-top: 40px;
            font-size: 12px;
            max-width: 60%;
        }
        /* Pagination Container */
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
            flex-wrap: wrap;
            flex-direction: row;
            align-items: center;
            padding: 0 10px;
        }

        /* Pagination Button */
        .pagination-button {
            display: inline-block;
            padding: 8px 12px;
            margin: 0 4px;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
            color: #333;
            text-decoration: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 53px;
            font-size: 7px;
            margin: 3px;
        }

        .pagination-button:hover {
            background-color: #e0e0e0;
        }

        /* Back Button */
        .button {
            display: inline-block;
            padding: 8px 12px;
            margin-top: 20px;
            border: none;
            background-color: #f9f9f9;
            color: #333;
            text-decoration: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #e0e0e0;
        }
    </style>
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
