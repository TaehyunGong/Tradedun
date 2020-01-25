<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 마이페이지 메뉴</title>
    <meta name="description" content="마이 페이지" />
    
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

	<section class="ftco-section auction auction-section">
	    <div class="container">
	        <div class="row">
	        	<div class="col-md-3 d-flex align-self-stretch ftco-animate">
	                <div class="media block-6 auction auction-2 content-hover-box" onclick="forward('mySetting')">
	                    <div class="media-body py-md-4 text-center  p-3 m-4">
	                        <div class="icon d-flex align-items-center justify-content-center"><span class="fas fa-user-tag"></span></div>
	                        <p class='sub_title'>판매중 아바타</p>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-3 d-flex align-self-stretch ftco-animate">
	                <div class="media block-6 auction auction-2 content-hover-box" onclick="forward('userSearchList')">
	                    <div class="media-body py-md-4 text-center  p-3 m-4">
	                        <div class="icon d-flex align-items-center justify-content-center"><span class="fab fa-searchengin"></span></div>
	                        <p class='sub_title'>내 조회 리스트</p>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-3 d-flex align-self-stretch ftco-animate">
	                <div class="media block-6 auction auction-2 content-hover-box" onclick="forward('userInfo')">
	                    <div class="media-body py-md-4 text-center  p-3 m-4">
	                        <div class="icon d-flex align-items-center justify-content-center"><span class="fas fa-cogs"></span></div>
	                        <p class='sub_title'>내 정보</p>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</section>
      
	<script>
		function forward(page){
			location.href='/user/'+page;
		}
	</script>

	<c:import  url="/footer" />
  </body>
</html>