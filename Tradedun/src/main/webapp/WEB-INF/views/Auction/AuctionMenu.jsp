<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 컨텐츠 메뉴</title>
    <meta name="description" content="경매장 검색 컨텐츠 메뉴" />
    
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
            <h1 class="mb-3 bread">컨텐츠 메뉴</h1>
          </div>
        </div>
      </div>	
    </section>

	<section class="ftco-section auction auction-section">
	    <div class="container">
	        <div class="row justify-content-center mb-5">
	            <div class="col-md-7 text-center heading-section ftco-animate">
	                <span class="subheading">Content Menu</span>
	                <h2 class="mb-3">컨텐츠 메뉴</h2>
	            </div>
	        </div>
	        <div class="row">
	            <div class="col-md-3 d-flex align-self-stretch ftco-animate">
	                <div class="media block-6 auction auction-2 content-hover-box" onclick="forward('rareAvatarSet')">
	                    <div class="media-body py-md-4 text-center  p-3 m-4">
	                        <div class="icon d-flex align-items-center justify-content-center"><span class="fas fa-search"></span></div>
	                        <p class='sub_title'>아바타 세트 검색</p>
	                        <p>각종 차수 레어아바타를 경매장 최저로 검색해보세요!</p>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-3 d-flex align-self-stretch ftco-animate">
	                <div class="media block-6 auction auction-2 content-hover-box" onclick="forward('avatarShowroom')">
	                    <div class="media-body py-md-4 text-center  p-3 m-4">
	                        <div class="icon d-flex align-items-center justify-content-center"><span class="fas fa-search-dollar"></span></div>
	                        <p class='sub_title'>쇼룸 아바타 검색</p>
	                        <p>던파 공홈 쇼룸에 등록한 위시리스트들을 간편히 경매장에서 검색해보세요!</p>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-3 d-flex align-self-stretch ftco-animate">
	            	<h1 class='ready_test'>준비중</h1>
	            	
	                <div class="media block-6 auction auction-2" onclick="">
	                    <div class="media-body py-md-4 text-center p-3 m-4">
	                        <div class="icon d-flex align-items-center justify-content-center"><span class="fas fa-pen"></span></div>
	                        <p class='sub_title'>판매 글 작성</p>
	                        <p>판매하고싶은 아바타를 착용한 캐릭터에서 바로 가져와 글을 등록할수 있습니다.</p>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-3 d-flex align-self-stretch ftco-animate">
	            	<h1 class='ready_test'>준비중</h1>
	            
	                <div class="media block-6 auction auction-2" onclick="">
	                    <div class="media-body py-md-4 text-center  p-3 m-4">
	                        <div class="icon d-flex align-items-center justify-content-center"><span class="fas fa-list"></span></div>
	                        <p class='sub_title'>판매 글 리스트</p>
	                        <p>현재 판매중인 아바타들을 조회해보세요!</p>
	                    </div>
	                </div>
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