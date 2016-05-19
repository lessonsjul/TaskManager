<%@ page pageEncoding="UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post" >
    <input type="hidden" name="id">
<table>
    <tr>
        <td>
            <input type="text" name="name"/>
        </td>
    </tr>
    <tr>
        <td>
            <select name="taskPriority">
                <c:forEach items="${priorities}" var="p" varStatus="status">
                    <option value="${p}">${p.value}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>
            <textarea name="description" cols="30" rows="10"></textarea>
        </td>
    </tr>
    <tr>
        <td>
            <input type="date" name="termEnd"/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>
</form>
</body>
</html>
