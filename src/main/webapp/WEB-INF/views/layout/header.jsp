<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>	
<meta charset="UTF-8">
<title>Nonage Shop</title>
<link href="${contextPath }/resources/static/css/shopping.css" rel="stylesheet">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var contextPath = "${pageContext.request.contextPath }";
</script>
<script type="text/javascript" src="${contextPath }/resources/static/js/member.js"></script>
<script type="text/javascript" src="#"></script>
</head>
<body>
	<div id="wrap">
		<!--헤더파일 들어가는 곳 시작 -->
		<header>
			<!--로고 들어가는 곳 시작--->
			<div id="logo">
				<a href="${contextPath }/"> <img
					src="${contextPath }/resources/static/images/logo.gif" width="180" height="100" alt="nonageshop">
				</a>
			</div>
			<!--로고 들어가는 곳 끝-->
			<nav id="catagory_menu">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.loginMember}">
							<li><a href="${contextPath }/member/login"
								style="width: 110px;">LOGIN(CUSTOMER</a> <a
								href="${contextPath }/admin/member/login"
								style="width: 100px;">| ADMIN)</a></li>
							<li>/</li>
							<li><a href="#">JOIN</a></li>
						</c:when>
						<c:otherwise>
							<li style="color: orange">
								${sessionScope.loginMember.name}(${sessionScope.loginMember.id})</li>
							<li><a href="${contextPath }/member/logout">LOGOUT</a></li>
						</c:otherwise>
					</c:choose>
					<li>/</li>
					<li><a href="#">CART</a></li>
					<li>/</li>
					<li><a href="#">MY PAGE</a></li>
					<li>/</li>
					<li><a href="${contextPath }/qnas/qnaList">Q&amp;A(1:1)</a>
					</li>
				</ul>
			</nav>

			<nav id="top_menu">
				<ul>
					<li><a href="${contextPath}/items?kind=1">Heels</a>
					</li>
					<li><a href="${contextPath}/items?kind=2">Boots</a>
					</li>
					<li><a href="${contextPath}/items?kind=3">Sandals</a>
					</li>
					<li><a href="${contextPath}/items?kind=4">Slipers</a>
					</li>
					<li><a href="${contextPath}/items?kind=5">Sneakers</a></li>
				</ul>
			</nav>
			<div class="clear"></div>
			<hr>
		</header>
		<!--헤더파일 들어가는 곳 끝 -->
		