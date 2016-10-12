<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <h1>Chat</h1>
        <form:form method="post" modelAttribute="user" action="/chat">
            <table>
                <tr>
                    <td>Nickname:</td>
                    <td><form:input path="nickname" /></td>
                    <td><form:errors path="nickname" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:password path="password" /></td>
                    <td><form:errors path="password" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Chat"></td>
                </tr>
            </table>
        </form:form>
        <a href="/regForm">Get registered</a>
    </body>
</html>
