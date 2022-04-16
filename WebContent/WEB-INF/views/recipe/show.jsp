<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:import url = "../layout/app.jsp">
    <c:param name = "content">

    <c:choose>

        <c:when test = "${recipe != null }">
        <h2>${recipe.title }</h2>

        <table>
            <tbody>
                <tr>
                    <th>タイトル</th>
                    <td><c:out value = "${recipe.title }" /></td>
                </tr>

                <tr>
                    <th>作成日時</th>
                    <fmt:parseDate value = "${recipe.createdAt }" pattern = "yyyy-MM-dd" var = "createdAt" type = "date" />
                    <td><fmt:formatDate value = '${createdAt }' pattern = 'yyyy-MM-dd' /></td>
                </tr>

                <tr>
                    <th>制作者</th>
                    <td><c:out value = "${recipe.user }" /></td>
                </tr>

                <tr>
                    <th>材料</th>
                    <td><c:out value = "${recipe.ingredient }" /></td>
                </tr>

                <tr>
                    <th>作り方</th>
                    <td><c:out value = "${recipe.howTo }" /></td>
                </tr>



            </tbody>
        </table>


        <p><a href = "${pageContext.request.contextPath }/index">一覧に戻る</a></p>
        <p><a href = "${pageContext.request.contextPath }/edit_recipe?id=${recipe.id }">このレシピを編集する</a></p>
        </c:when>

        <c:otherwise>
            <h2>お探しのデータは見つかりませんでした</h2>
        </c:otherwise>
    </c:choose>
    </c:param>
</c:import>