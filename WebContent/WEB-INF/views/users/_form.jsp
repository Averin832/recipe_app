<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test = "${errors != null }" >
    <div id = "flush_error" >
        入力内容にエラーがあります。<br/>
        <c:forEach var = "error" items = "${errors }" >
            ・<c:out value = "${error }" /><br/>
        </c:forEach>

    </div>
</c:if>

<label for = "title">ニックネーム</label><br/>
<input type = "text" name = "name" value = "${user.name }" />
<br/><br/>

<label for = "mail">メールアドレス</label><br/>
<input type = "text" name = "mail" value = "${user.mail }" id = "mail" />
<br/><br/>

<%--
<label for = "mailCheck">メールアドレス(確認用)</label><br/>
<input type = "text" name = "mailCheck" id = "mailCheck" />
<br/><br/>
--%>

<label for = "password">パスワード</label><br/>
<input type = "password" name = "password" value = "${user.password }" id = "password" />
<br/><br/>

<%--
<label for = "passwordCheck">パスワード(確認用)</label><br/>
<input type = "password" name = "passwordCheck" id = "passwordCheck" />
<br/><br/>
--%>

<input type = "hidden" name = "id" value = "${user.id }" />
<input type = "hidden" name = "_token" value = "${_token }" />
<!--input type = "hidden" name = "adminFlag" value = ${adminFlag } value = "0"/-->

<button type = "submit">登録</button>