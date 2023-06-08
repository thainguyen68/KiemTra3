<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 05/25/2023
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1 style="text-align: center">List Employee</h1>
    <div class="row">
        <div class="col-lg-2">
            <a class="btn btn-primary" style="text-decoration: none; color: white" href="/employees?action=create">Add
                new employee</a>
        </div>
        <div class="col-lg-3">
            <form action="/employees?action=search" method="post">
                <input type="text" name="search">
                <button class="btn btn-info" type="submit">Search</button>
            </form>
        </div>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Salary</th>
            <th>Department</th>
            <th colspan="2" style="text-align: center">Action</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${employee}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.email}</td>
                <td>${s.address}</td>
                <td>${s.phone}</td>
                <td>${s.salary}</td>
                <td>${s.department.name}</td>
                <td>
                    <a class="btn btn-warning" href="/employees?action=update&&id=${s.id}">Edit</a>
                </td>
                <td>
                    <button class="btn btn-danger" onclick="deleteS(${s.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script>
    function deleteS(id) {
        if (confirm("Are you sure?")) {
            window.location.href = `http://localhost:8080/employees?action=delete&&id=` + id
        }
    }
</script>
</html>