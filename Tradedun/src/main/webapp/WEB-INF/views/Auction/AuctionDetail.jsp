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
        <div class="row block-9 justify-content-center mb-5">
        	
        	<c:forEach var="charBox" items="${boardDetail.auctionBoardCharBox}">
        	
        		<!-- 
				##################
				## 1. 총 가격 및 전체적인 판매 설정
				##################
				 -->
				
				<div class='col-md-5 pr-3'>
					<!-- 판매 설정 -->
			      	<form action="#" class="request-form">
			      		<h3>판매 설정</h3>
			      		<div class="form-group">
			              	<label class="label">카테고리</label>
							<div class="form-field">
			  					<div class="select-wrap">
			                      <select id="category${charBox.charBox}" class="form-control">
				                      	<option value="$category.categoryCode">${charBox.categoryName}</option>
			                      </select>
			                    </div>
			               </div>
			            </div>
			            <div class="form-group">
			              	<label class="label">직업</label>
							<div class="form-field">
			  					<div class="select-wrap">
			                      <select id="jobGrow${charBox.charBox}" class="form-control">
				                      	<option value="$jobGrow.jobGrowId">${charBox.jobGrowName}</option>
			                      </select>
			                    </div>
			               </div>
			            </div>
						<div class="form-group">
							<label for="" class="label">판매 금액</label>
							<input type="text" id='resultPrice${charBox.charBox}' class="form-control" value="<fmt:formatNumber value="${charBox.totalPrice}" pattern="#,###" />" readonly>
						</div>
			            <div class="form-group">
			              	<label for="" class="label">판매자 말</label>
							<input type="text" id='comment${charBox.charBox}' class="form-control" value="${charBox.comment}" maxlength="30" readonly>
			            </div>
					</form> 
				</div>
				
				<!-- 
				##################
				## 2. 판매 아바타 상세 리스트
				##################
				 -->
				 
				<div class='col-md-7'>
				 <table id="avatarTable" class='compact hover' style="width:100%">
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
						  	<td><img src="https://img-api.neople.co.kr/df/items/${avatar.avatarNo}"></td>
						  	<td>${avatar.avatarName}</td>
						  	<td>${avatar.optionAbility}</td>
						  	<td><img src="https://img-api.neople.co.kr/df/items/${avatar.platinum}">
						  		<img src="https://img-api.neople.co.kr/df/items/${avatar.emblemOne}">
						  		<img src="https://img-api.neople.co.kr/df/items/${avatar.emblemTwo}">
						  	</td>
						  </tr>
					
					</c:forEach>
				 </table>
				</div>
				
			</c:forEach>
			
			</div>
        </div>
      </div>
    </section>

	<c:import  url="/footer" />
	
	<script>
		$(document).ready(function() {
			var table = $('#avatarTable').DataTable({
				info:		false,
				ordering:	false,
				searching:	false,
		        paging:     false,
		        scrollX:    true,
		        scrollXInner: "800px",
		        //fixedColumns: true,
		        columnDefs:[
		        	{targets: [0], width: "60px"},
		        	{targets: [1], width: "20px"},
		        	{targets: [2], width: "330px"},
		        	{targets: [3], width: "250px"},
		        	{targets: [4], width: "140px"}
		        ]
		    });
		    
			//경매장 리스트 첫번째 라인은 하이라이트
			$('.trLine').css('background', '#ffdab8').css('font-weight', 'bold');
		} );
	</script>
  </body>
</html>