<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Cache-Control" content="no-cache, must-revalidate, private, no-store, s-maxage=0, max-age=0" />
        <meta http-equiv="Pragma" content="no-cache" />
        <title>Chat</title>
        <spring:url value="resources/js/chat.js" var="chatJs" />
        <spring:url value="resources/css/style.css" var="styleCss" />
        <link rel="stylesheet" type="text/css" href="${styleCss}" />

        <script type="application/javascript" >
            var nickname = '${user.nickname}';
        </script>
        <script type="application/javascript" src="${chatJs}"></script>

    </head>
    <body>
        <div id="main">
            <div id="header">
                <h2>Welcome to chat, ${user.nickname}!</h2>
                <a href="/logout" rel="Log out"><button>Log out</button></a>
            </div>
            <div class="noscript">
                <h2>Seems your browser doesn't support Javascript!
                Websockets rely on Javascript being enabled.
                Please enable Javascript and reload this page!</h2>
            </div>
            <div id="console-container">
                <div id="console"></div>
            </div>
            <div>
                <input type="text" placeholder="type and press enter to chat" id="chat"/>
            </div>
        </div>
        <div id="aside">
            <h3>Users on-line</h3>
            <div id="loggedUsers"></div>
        </div>
    </body>
</html>