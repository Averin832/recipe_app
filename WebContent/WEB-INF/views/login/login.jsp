<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:import url = "/WEB-INF/views/layout/app.jsp" >

    <c:param name = "content" >
        <c:if test = "${loginError }" >
            <div id = "flush_error">
                メールアドレスかパスワードが間違っています
            </div>
        </c:if>

        <c:if test = "${flush != null }" >
            <div id = "flush_success" >
                <c:out value = "${flush } " ></c:out>
            </div>
        </c:if>

        <h2>ログイン</h2>

        <form method = "POST" action = "${pageContext.request.contextPath }/.login">

            <label for = "mail">メールアドレス</label><br/>
            <input type = "text" name = "mail" value = "${mail }" />
            <br/><br/>

            <label for = "password">パスワード</label><br/>
            <input type = "password" name = "password" value = "${password }" />
            <br/><br/>

            <input type = "hidden" name = "_token" value = "${_token }" />
            <button type = submit>ログイン</button><br/>

        </form>

        <p><a href = "${pageContext.request.contextPath }/new">新規登録</a></p><br/>
        <p><a href = "${pageContext.request.contextPath }/index">トップページに戻る</a></p>

    </c:param>
</c:import>