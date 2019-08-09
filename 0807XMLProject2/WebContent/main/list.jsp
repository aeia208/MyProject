<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="main/css/bootstrap.min.css" >
<style type="text/css">
.row {
	margin: 0px auto;
	width: 900px;
}
</style>
</head>
<body>
<div class="container" >
	<div class="row" >
		<table class="table" >
			<tr class="info">
				<th class="text-center" >학번</th>
				<th class="text-center" >이름</th>
				<th class="text-center" >국어</th>
				<th class="text-center" >영어</th>
				<th class="text-center" >수학</th>
				<th class="text-center" >총점</th>
				<th class="text-center" >평균</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td class="text-center" >${vo.hakbun }</td>
					<td class="text-center" >${vo.name }</td>
					<td class="text-center" >${vo.kor }</td>
					<td class="text-center" >${vo.eng }</td>
					<td class="text-center" >${vo.math }</td>
					<td class="text-center" >${vo.total }</td>
					<td class="text-center" >${vo.avg }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>