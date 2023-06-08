<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 05/25/2023
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h1 style="text-align: center"> Create employee</h1>
    <form style="width: 600px; margin: auto" action="/employees?action=create" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" id="name"  placeholder="Enter name">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="Enter email">
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" name="address" id="address" placeholder="Enter address">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" name="phone" id="phone" placeholder="Enter phone">
        </div>
        <div class="mb-3">
            <label for="salary" class="form-label">Salary</label>
            <input type="text" class="form-control" name="salary" id="salary" placeholder="Enter salary">
        </div>
        <div class="mb-3">
            <label for="department" class="form-label">Department</label>
            <select class="form-select" id="department" name="department"
                    aria-label="Default select example">
                <option selected>--- Choice Department ---</option>
                <c:forEach items="${department}" var="c">
                    <option value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <button class="btn btn-primary" type="submit">Create</button>
            <a class="btn btn-secondary" href="/employees">Back to home</a>
        </div>
    </form>
</div>
</body>
</html>