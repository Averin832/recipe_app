<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<label for = "title">料理名</label><br/>
<input type = "text" name = "title" value = "${recipe.title }" />
<br/><br/>

<label for = "ingredient">材料</label><br/>
<input type = "text" name = "ingredient" value = "${recipe.ingredient }" />
<br/><br/>

<label for = "how_to">作り方</label><br/>
<input type = "text" name = "how_to" value = "${recipe.howTo }" />
<br/><br/>

<input type = "hidden" name = "id" value = "${recipe.id }" />
<input type = "hidden" name = "user_mail" value = "${recipe.user }" />
<input type = "hidden" name = "_token" value = "${_token }" />


<button type = "submit">投稿</button>