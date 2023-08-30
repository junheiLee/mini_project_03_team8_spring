<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>  

<!--메인 이미지 들어가는 곳 시작 --->
<div class="clear"></div>
<div id="main_img">
	<img src="${contextPath }/resources/static/images/main_img.jpg">
</div>
<!--메인 이미지 들어가는 곳 끝--->

<div class="clear"></div>

<div id="front">
	<h2>New Item</h2>
	<div id="bestProduct">
		<c:forEach items="${newProductList }" var="productVO">
			<div id="item">
				<a
					href="${contextPath }/item/detailProduct?pseq=${productVO.pseq}">
					<img src="${contextPath }/resources/static/images/product_images/${productVO.image}" />
					<h3>${productVO.name}</h3>
					<p>${productVO.price2}</p>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="clear"></div>

	<h2>Best Item</h2>
	<div id="bestProduct">
		<c:forEach items="${bestProductList}" var="productVO">
			<div id="item">
				<a
					href="${contextPath }/items?pseq=${productVO.pseq}">
					<img src="${contextPath }/resources/static/images/product_images/${productVO.image}" />
					<h3>${productVO.name}</h3>
					<p>${productVO.price2}</p>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="clear"></div>
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>      
