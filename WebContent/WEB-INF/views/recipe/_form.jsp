<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<label for = "title">料理名</label><br/>
<input type = "text" name = "title" value = "${recipe.title }" />
<br/><br/>

<label for = "ingredient">材料</label><br/>
<textarea name = "ingredient" rows = "10" cols = "50" >${recipe.ingredient }</textarea>
<br/><br/>

<label for = "how_to">作り方</label><br/>
<textarea name = "how_to" rows = "10" cols = "50">${recipe.howTo }</textarea>
<br/><br/>

<label for = "word">詳細</label><br/>
<textarea name = "word" rows = "10" cols = "50">${recipe.word }</textarea>
<br/><br/>

<input type = "hidden" name = "id" value = "${recipe.id }" />
<input type = "hidden" name = "_token" value = "${_token }" />


<button type = "submit">投稿</button>