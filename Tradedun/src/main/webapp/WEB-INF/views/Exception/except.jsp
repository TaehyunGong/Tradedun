<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - Error</title>
    <meta charset="utf-8">
  </head>
  <body>

	<c:import  url="/header" />    
    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_7.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="/">메인 <i class="ion-ios-arrow-forward"></i></a></span></p>
            <h1 class="mb-3 bread">에러</h1>
          </div>
        </div>
      </div>	
    </section>

    
	 <div id='applyBox' class="row block-9 justify-content-center mt-5 mb-5">
		<div class="request-form">
			<h3>잘못된 접근입니다.</h3>
			<div class="form-group">
                 <a href='/' class="form-control btn btn-success">홈으로 돌아가기</a>
           	</div>
	  	</div>
	  </div>

	<c:import  url="/footer" />
  </body>
</html>