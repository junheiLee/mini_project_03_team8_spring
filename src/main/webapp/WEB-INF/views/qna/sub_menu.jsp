<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<meta charset="UTF-8">
  <nav id="sub_menu">
    <ul>    
      <li><a href="${contextPath }/qnas/qnaList?id=${sessionScope.loginMember.id}">Q&amp;A 게시글 리스트</a></li>
      <li><a href="${contextPath }/qnas/qnaWriteForm">Q&amp;A 게시글 쓰기</a></li> 
    </ul>
  </nav>