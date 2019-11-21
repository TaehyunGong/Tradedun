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
            <h1 class="mb-3 bread">판매 글 상세</h1>
          </div>
        </div>
      </div>	
    </section>

    
	<section class="ftco-section contact-section">
      <div class="container">
        
        <div class="row block-9 justify-content-center mb-5">
          <div class="col-md-8 mb-md-5">
          	<h2 class="text-center">던전앤파이터 공식홈페이지에서 꾸민 아바타를<br>여기서 쉽게 조회해보세요!</h2>
          	
            <form action="/auction/avatarShowroomSearch" method="post" class="bg-light p-5 contact-form ">
              <div class="form-group">
                 <label for="#">직군</label>
                 <select name="jobId" id="jobId" class="form-control">
                 	<c:forEach var="job" items="${jobList}">
	                    <option value="${job.code}">${job.codeName}</option>
                 	</c:forEach>
                 </select>
             </div>
              <div class="form-group">
                <textarea name="showroom" id="showroom" cols="10" rows="7" class="form-control" placeholder="예시)"></textarea>
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