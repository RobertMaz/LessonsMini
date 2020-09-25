<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="allClasses.UserDBConnection" %>
<%@ page import="allClasses.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>

<body>
<%
    ServletContext servletContext = request.getServletContext();
    UserDBConnection users = (UserDBConnection) servletContext.getAttribute("users");
%>
<table border="1" style="border-color: black">
    <tbody>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>City</td>
        <td>Age</td>
    </tr>

    <% for (User user : users.select()) {%>
    <tr>
        <td><%= user.getId() %>
        </td>
        <td><%= user.getName() %>
        </td>
        <td><%= user.getCity() %>
        </td>
        <td><%= user.getAge() %>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<br/>
<form method="get" action="/LetsCode/">
    <button type="submit">Go back</button>
</form>
</body>
</html>
