<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<html>
	<head>
	 	<title>게시판</title>
	</head>
	<body>
	
		<div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			 
			<nav>
			  홈 - 글 작성
			</nav>
			<hr />
			
			<section id="container">
				<form role="form" method="post">
					<table width="50%">
						<c:forEach var="item" items="${musicItem.items}" varStatus="status">
							<tr>
								<td width="10"></td>
								<td style="color:red;">${item.leftCode}</td>
								<td width="10"></td>
								<td style="color:red;">${item.rightCode}</td>
							</tr>
							<tr>
								<td width="10">${item.leftChorus}</td>
								<td>${item.leftLyrics}</td>
								<td width="10">${item.rightChorus}</td>
								<td>${item.rightLyrics}</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>