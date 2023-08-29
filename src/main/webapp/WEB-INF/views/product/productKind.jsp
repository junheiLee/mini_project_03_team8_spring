<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="/WEB-INF/views/layout/header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.html" %>       
  <article>
    <h2> Item</h2>     
    <c:forEach items="${listKindProduct}"  var="productVO">
      <div id="item">
        <a href="${contextPath}/item/detailProduct?pseq=${productVO.pseq}"> 
          <img src="${contextPath }/resources/static/images/product_images/${productVO.image}" />
          <h3>${productVO.name} </h3>        
          <p>${productVO.price2} 원 </p>
        </a>  
      </div>
    </c:forEach>    
    <div class="clear"></div>
  </article>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>    