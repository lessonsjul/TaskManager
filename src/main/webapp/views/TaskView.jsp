<%@ page pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ToDo List</title>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/create">Create Task</a>
<table>
    <tr>
        <td>Ready</td>
        <td>Task name</td>
        <td>Priopity</td>
        <td>Description</td>
        <td>Term</td>
        <td colspan="2">Actions</td>
    </tr>
    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task.name}</td>
            <td>${task.taskPriority.value}</td>
            <td>${task.description}</td>
            <td>${task.termEnd}</td>
            <td><a href="${pageContext.servletContext.contextPath}/edit?id=${task.id}">Edit</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/delete?id=${task.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>