<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 내 정보</title>
    <meta name="description" content="내 정보" />
    
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
            <h1 class="mb-3 bread">내 조회 리스트</h1>
          </div>
        </div>
      </div>	
    </section>

	<section class="ftco-section ftco-degree-bg">
	    <div class="container">
	        <div class="row">
	
	            <!-- 메뉴 -->
	            <div class="col-md-3 sidebar ftco-animate">
	                <div class="sidebar-box ftco-animate">
	                    <div class="categories">
	                        <h3>마이 페이지</h3>
	                        <li><a href="#">판매중 아바타</a></li>
	                        <li>내 조회 리스트</li>
	                        <li><a href="/user/userInfo">내 정보</a></li>
	                    </div>
	                </div>
	            </div>
	
	            <div class="col-md-9 text-center d-flex ftco-animate">
	                <div class="car-list">
	                    <table class="table">
	                        <thead class="thead-primary">
	                            <tr class="text-center">
	                            	<th class="heading">&nbsp;</th>
	                                <th class="heading">날짜</th>
	                                <th class="heading">컨텐츠</th>
	                                <th class="heading">직업</th>
	                                <th class="heading">차수 or 아바타</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        	<c:forEach var="log" items="${list}" varStatus="num">
	                            <tr>
	                                <td>${num.count }</td>
	                                <td>${log.createDT }</td>
	                                <td>${log.requestUrl }</td>
	                                <td>${log.codeName }</td>
	                                <td>${log.categoryName }</td>
	                            </tr>
	                        	</c:forEach>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	
	        </div>
	
	        <div class="row mt-5">
	            <div class="col text-center">
	                <div class="block-27">
	                    <ul>
	                        <li><a href="#">&lt;</a></li>
	                        <li class="active"><span>1</span></li>
	                        <li><a href="#">2</a></li>
	                        <li><a href="#">3</a></li>
	                        <li><a href="#">4</a></li>
	                        <li><a href="#">5</a></li>
	                        <li><a href="#">&gt;</a></li>
	                    </ul>
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