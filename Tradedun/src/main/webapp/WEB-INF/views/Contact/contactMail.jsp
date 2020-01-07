<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 문의하기</title>
    <meta charset="utf-8">
    <meta name="description" content="문의, 메일 보내기" />
	<c:import  url="/header" />    
  </head>
  <body>

    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_4.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="/">메인<i class="ion-ios-arrow-forward"></i></a></span></p>
            <h1 class="mb-3 bread">문의 메일 보내기</h1>
          </div>
        </div>
      </div>
    </section>

	<section class="ftco-section contact-section">
	    <div class="container">
	        <div class="row block-9 justify-content-center mb-5">
	            <div class="col-md-8 mb-md-5">
	                <h2 class="text-center">잘 안되는 버그나 필요한 기능이 있으시다면 메일로 알려주세요!</h2>
	                
					<form id="submitForm" action="/contact/sendContact" class="bg-light p-5 contact-form" 
            			method="post" enctype="multipart/form-data">
            			
             			<div class="form-group">
						    <label for="#">문의 종류</label>
						    <div class="form-field">
						        <div class="select-wrap">
			            			<select name="code" class="form-control">
			            				<c:forEach var="code" items="${list}">
				            				<option value="${code.code}">${code.codeName}</option>
			            				</c:forEach>
			            			</select>
						        </div>
						    </div>
						</div>
						
	                    <div class="form-group">
	                    	<label for="#">이메일 *</label>
	                        <input type="email" name="email" class="form-control" placeholder="나의 이메일(필수)" required="required">
	                    </div>
	                    
	                    <div class="form-group">
	                    	<label for="#">제목</label>
	                        <input type="text" name="title" class="form-control" placeholder="제목">
	                    </div>
	                    
	                    <div class="form-group">
	                    	<label for="#">내용 *</label>
	                        <textarea name="contents" id="contents" cols="30" rows="7" class="form-control" placeholder="내용" required="required"></textarea>
	                    </div>
	                    
						<div class="form-group">
	                    	<label for="#">첨부파일 및 이미지 (최대 20MB)</label>
	                        <input type="file" name="attach" class="form-control">
	                    </div>
	                    
	                    <div class="form-group">
	                        <input type="button" id="btn" onclick="send()" value="문의 메일 보내기" class="btn btn-primary py-3 px-5">
	                    </div>
	                </form>
	
	            </div>
	        </div>
	    </div>
	</section>

	<c:import  url="/footer" />
    	
	<script type="text/javascript">
	
		//수동 제출
		function send(){
			var email = $('input[name=email]');
			var contents = $('#contents');
			
			if(! isEmail( $(email).val() )){
				alert('잘못된 이메일입니다.');
				$(email).focus();
				return false;
			}
			
			if($(contents).val().length <= 0){
				alert('내용을 작성해주세요');
				$(contents).focus();
				return false;
			}
			
			$('#btn').removeClass('btn-primary').css('background', 'red').css('color', 'white').val('문의 메일 보내는 중...');
			
			if(confirm('문의메일을 보내시겠습니까?')){
				$('#btn').removeAttr('onclick');
				$('#submitForm').submit();
			}
		}
		
		//이메일 정규식
		function isEmail(asValue) {
			var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			return regExp.test(asValue); // 형식에 맞는 경우 true 리턴	

		}

	</script>
	
  </body>
</html>