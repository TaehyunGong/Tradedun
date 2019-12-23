<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 컨텐츠 메뉴</title>
    <meta charset="utf-8">
	<c:import  url="/header" />    
  </head>
  <body>

    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
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
            			method="post" enctype="multipart/form-data" onsubmit="return false;">
            			
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
	                        <textarea name="contents" id="" cols="30" rows="7" class="form-control" placeholder="내용" required="required"></textarea>
	                    </div>
	                    
						<div class="form-group">
	                    	<label for="#">첨부파일</label>
	                        <input type="file" name="attach" class="form-control">
	                    </div>
	                    
	                    <div class="form-group">
	                        <input type="button" onclick="submit()" value="문의 메일 보내기" class="btn btn-primary py-3 px-5">
	                    </div>
	                </form>
	
	            </div>
	        </div>
	    </div>
	</section>

	<c:import  url="/footer" />
    	
	<script type="text/javascript">
	
		//수동 제출
		// mode = modify || insert(default)
		function submit(){
			$('#submitForm').submit();
		}
	</script>
	
  </body>
</html>