<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="row justify-content-center mb-5">
        	
        	<c:forEach var="charBox" items="${boardDetail.auctionBoardCharBox}">
        		<div id='charBox${charBox.charBox}' class='col-md-12 heading-section text-center ftco-animate mb-5 fadeInUp ftco-animated'>
        			<span class="subheading">${charBox.jobGrowName}</span>
        			<h2 class="mb-2">${charBox.categoryName }</h2>
        		</div>
        	
        		<!-- 
				##################
				## 1. 총 가격 및 전체적인 판매 설정
				##################
				 -->
				
				<div class='col-md-12 mb-3'>
					
			        <div class="row d-flex">
			          <div class="col-md-3 d-flex align-self-stretch ftco-animate">
			            <img src="/upImage/CharacterImages/${charBox.imageName}"/>
			          </div>
			          <div class="col-md-3 d-flex align-self-stretch ftco-animate">
			            <div class="media block-6 services">
			              <div class="media-body py-md-4">
			              	<div class="d-flex mb-3 align-items-center">
				              	<div class="icon"><span class="flaticon-placeholder"></span></div>
				                <h3 class="heading mb-0 pl-3">가격</h3>
			                </div>
			                <p><fmt:formatNumber value="${charBox.totalPrice}" pattern="#,###" /></p>
			              </div>
			            </div>      
			          </div>
			          <div class="col-md-3 d-flex align-self-stretch ftco-animate">
			            <div class="media block-6 services">
			              <div class="media-body py-md-4">
			              	<div class="d-flex mb-3 align-items-center">
				              	<div class="icon"><span class="flaticon-placeholder"></span></div>
				                <h3 class="heading mb-0 pl-3">판매자의 말</h3>
			                </div>
			                <p>${charBox.comment}</p>
			              </div>
			            </div>      
			          </div>
			          <div class="col-md-3 d-flex align-self-stretch ftco-animate">
			            <div class="media block-6 services">
			              <div class="media-body py-md-4">
			              	<div class="d-flex mb-3 align-items-center">
				              	<div class="icon"><span class="flaticon-placeholder"></span></div>
				                <h3 class="heading mb-0 pl-3">구매요청</h3>
			                </div>
			                <p><a href='#'>대충 구매요청하는 이미지</a></p>
			              </div>
			            </div>      
			          </div>
			        </div>
				</div>
				
				<!-- 
				##################
				## 2. 판매 아바타 상세 리스트
				##################
				 -->
				 
				<div class='col-md-12 mb-3'>
				 <table id="avatarTable" class='avatarTable compact hover' style="width:100%">
				 	<thead>
				 		<tr>
				 			<td>부위</td>
				 			<td></td>
				 			<td>아바타명</td>
				 			<td>옵션</td>
				 			<td>엠블렘</td>
				 		</tr>
				 	</thead>
				 	<c:forEach var="avatar" items="${charBox.auctionAvatarList}">
				 		
						  <tr id='listOne' class='trLine' data-slotName="${avatar.slotName}">
						  	<td>${avatar.slotName }</td>
						  	
						  	<c:choose>
						  		<c:when test="${!empty avatar.avatarNo }">
								  	<td><img src="https://img-api.neople.co.kr/df/items/${avatar.avatarNo}"></td>
								  	<td>${avatar.avatarName}</td>
								  	<td>${avatar.optionAbility}</td>
								  	<td><c:forEach var="emblem" items="${avatar.emblemList}">
								  			<img src="https://img-api.neople.co.kr/df/items/${emblem.itemId}">
								  		</c:forEach>
								  	</td>
							  	</c:when>
							  	
							  	<c:otherwise>
							  		<td></td>
							  		<td></td>
							  		<td></td>
							  		<td></td>
							  	</c:otherwise>
						  	</c:choose>
						  </tr>
					
					</c:forEach>
				 </table>
				</div>
				
			</c:forEach>
			
			</div>
        </div>
      
    </section>

	<c:import  url="/footer" />
	
	<script>
		$(document).ready(function() {
			//페이지 로드시 해당 charBox로 focus
			$('#charBox${charBox}')[0].scrollIntoView({
				behavior: 'smooth'
			});
			
			var table = $('table.avatarTable').DataTable({
				info:		false,
				ordering:	false,
				searching:	false,
		        paging:     false,
		        scrollX:    true,
		        scrollXInner: "1100px",
		        //fixedColumns: true,
		        columnDefs:[
		        	{targets: [0], width: "60px"},
		        	{targets: [1], width: "20px"},
		        	{targets: [2], width: "430px"},
		        	{targets: [3], width: "350px"},
		        	{targets: [4], width: "140px"}
		        ]
		    });
		    
			//경매장 리스트 첫번째 라인은 하이라이트
			$('.trLine').css('background', '#ffdab8').css('font-weight', 'bold');
		} );
	</script>
  </body>
</html>