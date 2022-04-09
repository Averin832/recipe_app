<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<label for = "title">ニックネーム</label><br/>
<input type = "text" name = "name" value = "${user.name }" />
<br/><br/>

<label for = "mail">メールアドレス</label><br/>
<input type = "text" name = "mail" value = "${user.mail }" />
<br/><br/>

<label for = "password">パスワード</label><br/>
<input type = "text" name = "password" value = "${user.password }" />
<br/><br/>

<input type = "hidden" name = "id" value = "${user.id }" />
<input type = "hidden" name = "_token" value = "${_token }" />
<!--input type = "hidden" name = "adminFlag" value = ${adminFlag } value = "0"/-->

<button type = "submit">登録</button>