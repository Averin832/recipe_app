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

                <c:if test = "${login_user == null }" >

                    <p><a href = "${pageContext.request.contextPath }/login">ログイン</a></p>

                </c:if>

                <c:if test = "${login_user != null}" >

                        <div id = "user_name" >

                            <c:out value = "${login_user.name}" />
                            &nbsp;さん&nbsp;&nbsp;&nbsp;

                        </div>

                    <a href = "${pageContext.request.contextPath }/user_page" >投稿レシピ一覧</a>
                    <a href = "${pageContext.request.contextPath }/bookmarks" >ブックマーク一覧</a>
                    <a href = "${pageContext.request.contextPath }/logout" >ログアウト</a>

                </c:if>

                <p><a href = "${pageContext.request.contextPath }/new_recipe">レシピの投稿</a></p>

            </div>
        </div>
    </div>
        <div id = "content">${param.content }</div>
        <div id = "footer">by Koh Aoki</div>
</body>