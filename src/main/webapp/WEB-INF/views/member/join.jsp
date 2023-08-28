<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ include file="/WEB-INF/views/layout/header.jsp" %>  
<%@ include file="sub_img.html"%> 
<%@ include file="sub_menu.html" %>
<script>
	$(document).ready(function() {
	    $("#userId").on("input", function() {
	    	document.formm.reid.value = "";
			$("#idAvailabilityMessage").text("");
		});
	});
</script>   
  <article>
    <h2>Join Us</h2>
    <form id="join" action="${contextPath }/member/join_pro" method="post" name="formm">
      <fieldset>
        <legend>Basic Info</legend>
        <label>User ID</label>
        <input type="text"  id="userId"    name="id"        size="12"  >
        <input type="hidden"    name="reid">
        <input type="button"    value="중복 체크"  class="dup" onclick="idcheck()"><br>
        <span id="idAvailabilityMessage" style="margin-left: 145px;"></span><br>
        <label>Password</label> 	
        <input type="password"  name="pwd"><br> 
        <label>Retype Password</label> 
        <input type="password"  name="pwdCheck"><br> 
        <label>Name</label>
        <input type="text"      name="name"><br> 
        <label>E-Mail</label>
        <input type="text"      name="email"><br>
        
      </fieldset>
      <fieldset>
        <legend>Optional</legend>
        <label>Zip Code</label> 
        <input type="text"       name="zipNum"   size="10" >      
        <input type="button"     value="주소 찾기" class="dup" onclick="post_zip()"><br>
        <label>Address</label> 
        <input type="text"        name="addr1"   size="50">
        <input type="text"        name="addr2"   size="25"> <br>
        <label>Phone Number</label> 
        <input  type="text"       name="phone"><br>
      </fieldset>
      <div class="clear"></div>
      <div id="buttons">
        <input type="button"    value="회원가입"   class="submit" onclick="go_save()"> 
        <input type="reset"      value="취소"     class="cancel">
      </div>
    </form>
  </article>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>   
  