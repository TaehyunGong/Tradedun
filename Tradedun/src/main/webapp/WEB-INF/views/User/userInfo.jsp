<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 내 정보</title>
    <meta name="description" content="내 정보" />
    
    <meta charset="utf-8">
	<c:import  url="/header" />
  </head>
  <body>

    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_2.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="/">메인<i class="ion-ios-arrow-forward"></i></a></span></p>
            <h1 class="mb-3 bread">마이페이지 메뉴</h1>
          </div>
        </div>
      </div>	
    </section>

	<section class="ftco-section ftco-degree-bg">
	    <div class="container">
	        <div class="row">
	
				<!-- 메뉴 -->
	            <div class="col-md-3 sidebar ftco-animate">
	                <div class="sidebar-box ftco-animate">
	                    <div class="categories">
	                        <h3>마이 페이지</h3>
	                        <li><a href="#">판매중 아바타</a></li>
	                        <li><a href="/user/userSearchList?row=1">내 조회 리스트</a></li>
	                        <li>내 정보</li>
	                    </div>
	                </div>
	            </div>
	
				<!-- 내용 -->
	            <div class="col-md-9 order-md-last ftco-animate">
	            
	                <h1 class="mb-3">내 정보</h1>
	                
					<form action="/user/updateUserInfo" id="submitForm" method="post" class="bg-light p-4 contact-form mb-3">
					
	                    <div class="form-group">
	                        <label for="#">이메일</label>
	                        <input type="email" name="email" class="form-control" value="${userInfo.email }" required="required">
	                    </div>
	                    
	                    <div class="form-group">
	                        <label for="#">이메일 수신여부 (클릭)</label>
		                    <div class="col-lg align-items-end">
		                    	<c:choose>
		                    		<c:when test="${userInfo.emailYN eq 'N'}">
				                        <input type="button" onclick="changeEmailButton(this)" class="btn btn-danger" value="이메일을 수신받지 않습니다.">
		                    		</c:when>
		                    		<c:otherwise>
		                    			<input type="button" onclick="changeEmailButton(this)" class="btn btn-success" value="이메일 수신받습니다.">
		                    		</c:otherwise>
		                    	</c:choose>
		                        <input type="hidden" name="emailYN" value="${userInfo.emailYN}">
		                    </div>
	                    </div>
	                    
		            </form>
		            
                    <button onclick="submit()" class="btn btn-primary">저장</button>
	            </div>
	
	        </div>
	    </div>
	</section>
	<!-- .section -->
      
	<script>
	
		//저장
		function submit(){
			var email = $('input[name=email]');
			var contents = $('#contents');
			
			if(! isEmail( $(email).val() )){
				alert('잘못된 이메일입니다.');
				$(email).focus();
				return false;
			}
			
			if(confirm('저장하시겠습니까?')){
				$('#submitForm').submit();
			}
		}
	
		function forward(page){
			location.href='/user/'+page;
		}
		
		//이메일 수신 여부 버튼을 바꾼다. 거부 <-> 승인
		function changeEmailButton(btn){
			var yn = $('input[name=emailYN]');
			
			if($(yn).val() == 'N'){
				$(yn).val('Y');
				$(btn).removeClass('btn-danger').addClass('btn-success').val('이메일 수신받습니다.');
			}else{
				$(yn).val('N');
				$(btn).removeClass('btn-success').addClass('btn-danger').val('이메일을 수신받지 않습니다.');
			}
			
		}
		
		//이메일 정규식
		function isEmail(asValue) {
			var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			return regExp.test(asValue); // 형식에 맞는 경우 true 리턴	

		}
	</script>

	<c:import  url="/footer" />
  </body>
</html>