<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Training Application Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: -20px auto;
        }

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
        .form {
            margin-left: 20px;
            margin-right: 20px;
            right: 0px;
            margin-top: 20px;
            margin-bottom: 20px;
            background-color: ;
        }
        .button{
            position: flexible;
            margin-left: auto;
            margin-right: 0px;
            right: 0px;
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            border-style: solid;
            border-width: 1px;
            border-color: #888888;
            box-shadow: 0px 1px 2px #888888;
        }

        select.selectItem {
            font-family: Arial, sans-serif;
            font-size: 16px;
            color: #333;
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 8px;
            width: 200px;
            height: 50px;
            cursor: pointer;
        }

        /* Optional styles for when the select box is hovered or focused */
        select.selectItem:hover,
        select:focus {
            border-color: #666;
            outline: none;
        }

        div.container{
            width: fit-content;
            margin: auto;
            margin-top: 20px;
            margin-bottom: 100px;
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
    </style>
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