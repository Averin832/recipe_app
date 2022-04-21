<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:import url = "../layout/app.jsp" >
    <c:param name = "content" >

        <c:if test = "${flush != null }" >
            <div id = "flush_success" >
                <c:out value = "${flush }" ></c:out>

            </div>
        </c:if>

        <h2>${login_user.name }さんのブックマーク一覧</h2>

        <ul>
            <c:forEach var = "bookmark" items = "${bookmarks }" >
                <li>
                    <a href = "${pageContext.request.contextPath }/recipe?id=${boomark.recipe.id }" >
                        <c:out value = "${bookmark.recipe.id }" />
                    </a>
                    :<c:out value = "${bookmark.recipe.title }" ></c:out> &gt;

                </li>
            </c:forEach>
        </ul>

        <div id = "pagination" >
            (全 ${myBookmarksCount } 件)<br/>

            <c:forEach var = "i" begin = "1" end = "${((myRecipesCount - 1) / 10 ) + 1 }" step = "1" >
                <c:choose>
                    <c:when test = "${i == page }" >
                        <c:out value = "${i }" /> &nbsp;
                    </c:when>

                    <c:otherwise>
                        <a href = "${pageContext.request.contextPath }/user_page?page=${i }" >
                        <c:out value = "${i }" /></a> &nbsp;

                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href = "${pageContext.request.contextPath }/index">トップページに戻る</a></p>

    </c:param>
</c:import>