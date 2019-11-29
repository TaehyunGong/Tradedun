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
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_3.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="/">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Auction <i class="ion-ios-arrow-forward"></i></span></p>
            <h1 class="mb-3 bread">위시리스트 아바타 검색</h1>
          </div>
        </div>
      </div>	
    </section>

    
	<section class="ftco-section contact-section">
      <div class="container">
        
     	<div class="row block-12 justify-content-center mb-3">
         <div class="col-md-12 heading-section text-center mb-3">
         	<span class="subheading">ShowRoom Auction Search</span>
           <h2 class="mb-2">쇼룸 경매장 검색</h2>
         </div>
         <div class='p-4'>
         	<h3>※ 주의사항</h3>
         	<ul>
         		<li>던파 공식 홈페이지 쇼룸의 위시리스트에서 가져온 아바타만 가능합니다.</li>
         		<li>염색이 적용된 아바타는 염색을 제외한 아바타로 보여줍니다.</li>
         		<li>교환불가 및 경매장에 현재 없는 아바타는 조회되지 않습니다.</li>
         		<li>이 페이지는 PC에 최적화 되어있습니다.</li>
         	</ul>
         	
         	<button class='form-control btn btn-success'>사용 방법</button>
         </div>
       	</div>
       	
        <div class="row block-9 justify-content-center mb-5">
          <div class="col-md-8 mb-md-5">
          
          	
            <form action="/auction/avatarShowroomSearch" method="post" class="bg-light p-4 contact-form ">
              <div class="form-group">
                 <label for="#">직군</label>
                 <select name="jobId" id="jobId" class="form-control">
                 	<c:forEach var="job" items="${jobList}">
	                    <option value="${job.code}">${job.codeName}</option>
                 	</c:forEach>
                 </select>
             </div>
              <div class="form-group">
                <textarea name="showroom" id="showroom" cols="10" rows="10" class="form-control" placeholder="예시) 	
머리	오메가센타우리 헤어
모자	뿔달린 헤드기어[C타입]
얼굴	상처 눈화장[B타입]
목가슴	우직한 NINJA의 붉은색 휘날리는 머플러[A타입]
상의	화룡의 용의 뿔 상의[A타입]
허리	올림푸스 제우스의 벨트[C타입]
하의	사영의 바지[B타입]
신발	용의 전사 화이트 본 부츠[B타입]
피부	회색빛 피부"></textarea>
              </div>
              <div class="form-group">
                <input type="submit" value="검색" class="btn btn-primary py-3 px-5">
              </div>
            </form>
          
          </div>
        </div>
        
      </div>
    </section>

	<c:import  url="/footer" />
  </body>
</html>