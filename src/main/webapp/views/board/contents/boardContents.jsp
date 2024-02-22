<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>게시판 목록</title>
</head>
<body>
<div id=" board_listBox">
    <jsp:include page="contentsSearch.jsp" >
        <jsp:param name="" value=""/>
    </jsp:include>

    <table>
        <thead>
        <tr>
            <th>번호</th>
            <th>작성자</th>
            <th>제목</th>
            <th>내용</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="boardDTO" items="${boardDTOs}">
            <tr>
                <td>${boardDTO.seq}</td>
                <td>${boardDTO.name}</td>
                <td>${boardDTO.subject}</td>
                <td>${boardDTO.content}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<jsp:include page="contentsPaging.jsp">
    <jsp:param name="" value=""/>
</jsp:include>

</div>
</body>
</html>
