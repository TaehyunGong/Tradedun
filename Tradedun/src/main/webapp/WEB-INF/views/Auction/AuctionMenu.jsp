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
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_2.png');" data-stellar-background-ratio="0.5">
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

    
	<section class="ftco-section contact-section">
		<div class='container'>
		     <div class="row">
				<div class='col-lg-4  mr-3 mb-3'>
		           	<button onclick='forward("AuctionWriter")' class="btn-success">판매 글 작성</button>
				</div>
				
				<div class='col-lg-4'>
					<button onclick='forward("AuctionList")' class="btn-success">판매 글 리스트 보기</button>
				</div>
				
				<div class='col-lg-4'>
					<button onclick='forward("avatarCharacterSet")' class="btn-success">아바타 세트 검색</button>
				</div>
				
				<div class='col-lg-4'>
					<button onclick='forward("avatarShowroom")' class="btn-success">던파 쇼룸 아바타 검색</button>
				</div>
			</div>
	     </div>
    </section>
      
	<script>
		function forward(page){
			location.href='/auction/'+page;
		}
	</script>

	<c:import  url="/footer" />
  </body>
</html>