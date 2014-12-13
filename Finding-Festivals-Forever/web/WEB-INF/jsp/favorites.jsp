<%-- 
    Document   : favourites
    Created on : Nov 13, 2014, 5:19:46 PM
    Author     : CharaKatiri
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favorites</title>
    </head>
    <body>
        <h1>Favorites</h1>
        <table style="width: 400px">
            <tr>
                <td>Festival</td>
                <td>&nbsp;</td>
            </tr>
                <c:url value="/cart/decrease" var="d_url" context="/Lab_Cart">
                    <c:param name="pid" value="${festival.id}"/>
                </c:url>
                <tr>
                    <td>${festivals.description}</td>
                        <a href="${r_url}">[X]</a>
                    </td>
                </tr>
        </table>
        <p><a href="/Finding-Festivals-Forever/index">Back to home</a></p>
    </body>
</html>