<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>Autoroad - Free Bootstrap 4 Template by Colorlib</title>
    <meta charset="utf-8">
  </head>
  <body>

	<c:import  url="/header" />    
	<script type="text/javascript" src="/js/ckeditor/ckeditor.js"></script>
    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Contact <i class="ion-ios-arrow-forward"></i></span></p>
            <h1 class="mb-3 bread">글작성</h1>
          </div>
        </div>
      </div>
    </section>

	<section class="ftco-section contact-section">
      <div class="container">
        <div class="row block-9 justify-content-center mb-5">
          <div class="col-md-10 mb-md-5">
          
            <form id="submitForm" action="/board/boardInsert" class="bg-light p-4 contact-form" 
            	method="post" enctype="multipart/form-data" onsubmit="return false;">
            	
              <div class="form-group">
              	<label for="#">제목</label>
                <input type="text" class="form-control" placeholder="제목" name="title" >
              </div>
              
              <div class="form-group">
                <textarea class="form-control" id="p_content" name="contents" ></textarea>
              </div>
              
              <div class="form-group">
                <input type="button" onclick="submit()" value="글 작성" class="btn btn-primary py-3 px-5">
              </div>
            </form>
          
          </div>
        </div>
      </div>
	   </section>

	<c:import  url="/footer" />
	
	<script type="text/javascript">
	
		//ckEditor load
		$(function(){
			CKEDITOR.replace('p_content', {height: 400});
		})
		
		//수동 제출
		function submit(){
			$('#submitForm').submit();
		}
	</script>
    
  </body>
</html>