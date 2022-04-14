<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset = "UTF-8">
        <title><c:out value = "レシピ投稿サイト（仮）" /></title>
        <link rel = "stylesheet" href = "<c:url value = '/css/reset.css' />">
        <link rel = "stylesheet" href = "<c:url value = '/css/style.css' />">
</head>

<body>
    <div id = "wrapper">
        <div id = "header">
            <div id = "header_menu">

            </div>
        <p><a href = "${pageContext.request.contextPath }/login">ログイン</a></p>
        <p><a href = "${pageContext.request.contextPath }/new_recipe">レシピの投稿</a></p>

        <c:if test = "${login_user != null}" >
                <div id = "user_name" >
                    <c:out value="${login_user.name}" />
                    &nbsp;さん&nbsp;&nbsp;&nbsp;
                    <a href = "${pageContext.request.contextPath }/logout" >ログアウト</a>
                </div>
            </c:if>

        </div>
        <div id = "content">${param.content }</div>
        <div id = "footer">by Koh Aoki</div>
    </div>
</body>