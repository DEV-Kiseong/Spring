<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width="600" border=1 align="center">
	<tr><td>번호</td><td>문의제목</td><td>제품번호</td></tr>
	<c:forEach items = "${dtos }" var="dto" varStatus="status">
		<tr><td>${status.count }</td>
			<td><a href="inquireAnswer.inq?inquireNum=${dto.inquireNum }">${dto.inquireKind } : ${dto.inquireSubject }</a></td>
			<td>${dto.goodsNum }</td></tr>
	</c:forEach>
</table>
</body>
</html>