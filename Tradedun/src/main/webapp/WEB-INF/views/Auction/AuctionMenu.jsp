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
    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/bg_1.jpg');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Contact <i class="ion-ios-arrow-forward"></i></span></p>
            <h1 class="mb-3 bread">Auction Menu Choice</h1>
          </div>
        </div>
      </div>	
    </section>

    
	 <div id='applyBox' class="row block-9 justify-content-center mt-5 mb-5">
		<div class="request-form">
           	<a href='/auction/AuctionWriter' class="btn-success py-3 px-3">판매 글 작성</a>
           	<a href='/auction/avatarSetSearch' class="btn-success py-3 px-3">아바타 세트 검색</a>
	  	</div>
	  </div>

	<c:import  url="/footer" />
  </body>
</html>