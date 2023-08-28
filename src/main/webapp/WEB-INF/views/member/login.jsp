<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>  
<%@ include file="/WEB-INF/views/layout/header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.html" %>       
  <article>
    <h1>Login</h1>
    <form method="post" action="${contextPath }/member/login_pro">
        <fieldset>
        <legend></legend>
          <label>User ID</label>
          <input name="id" type="text"><br> 
          <label>Password</label> 
          <input name="pwd" type="password"><br> 
        </fieldset>
        <div class="clear"></div>
        <div id="buttons">
            <input type="submit" value="로그인" class="submit">
            <input type="button" value="회원가입" class="cancel"
                 onclick="location='${contextPath }/members/contract.do' ">
            <input type="button" value="아이디 비밀번호 찾기" class="submit"
                 onclick="location='${contextPath }/members/find_id_form'">     
        </div>
    </form>  
  </article>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>      
