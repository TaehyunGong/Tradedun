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
            <h1 class="mb-3 bread">AvatarShowroomSearch</h1>
          </div>
        </div>
      </div>	
    </section>

    
	<section class="ftco-section contact-section">
      <div class="container">
      
      	<div class='row justify-content-center'>
      		<div class="col-md-12 heading-section text-center ftco-animate mb-5 fadeInUp ftco-animated">
          	<span class="subheading">조회 결과</span>
            <h2 class="mb-2">쇼룸 리스트 경매장 조회 결과</h2>
          </div>
      	</div>
      	
      	<div class='row'>
     		<div class='col-md-5 pr-3'>
				<!-- 판매 설정 -->
		      	<div class="request-form">
		      		<h3>판매 설정</h3>
		      		<div class="form-group">
		              	<label class="label">카테고리</label>
						<div class="form-field">
		  					<div class="select-wrap">
		                      <select id="category${number}" class="form-control">
			                      <option value="$category.categoryCode">$category.categoryName</option>
		                      </select>
		                    </div>
		               </div>
		            </div>
					<div class="form-group">
						<label for="" class="label">판매하실 금액</label>
						<input type="text" id='resultPrice${number}' class="form-control" placeholder="판매하실 금액" onlyNumber>
					</div>
					<div class="d-flex">
		              <div class="form-group ml-2">
		                <label for="" class="label">경매장에 조회된 아바타 부위</label>
		                <input type="text" class="form-control" id="" value="${availAvatar}부위" readonly>
		              </div>
		              <div class="form-group ml-2">
		                <label for="" class="label">선택된 아바타 가격</label>
		                <input type="text" class="form-control" id="auctionSumPrice1" value="$numberTool.format('#,##0', $minTotalSales)" readonly>
		              </div>
		      		</div>
				</div>
			</div>
      		
      		<div class='col-md-7'>
      			<table class="table table-condensed " >
      				<c:forEach var="avatar" items="${choiceAvatar}">
						<tr>
							<td style='width:60px;'>${avatar.slotName}</td>
						    <td><img class="pr-3" src='https://img-api.neople.co.kr/df/items/${avatar.itemId}' title='${avatar.itemName}' alt='${avatar.itemName}'/>
						    </td>
						    <td><font class='pl-2' style='font-weight: bold;'>${avatar.itemName}</font></td>
						</tr>
					</c:forEach>
				</table>
      		</div>
      	</div>
      	
      	<div class='pt-5'>
			 <table class="table" >
			 	<c:forEach var="auction" items="${auctions}">
			 		<c:if test="${!empty auction.rows[0]}">
			 		
						<tbody id='' class="selectAuction auctionList">
						  <tr class='trLine'>
						  	<td>${auction.rows[0].itemTypeDetail}</td>
						  	<td><img src="https://img-api.neople.co.kr/df/items/${auction.rows[0].itemId}"></td>
						  	<td>${auction.rows[0].itemName}</td>
						  	<td>
						  		<c:if test="${!empty auction.rows[0].avatar}">
							  		<c:forEach var="emblem" items="${auction.rows[0].avatar.emblems}">
							  			<img src="https://img-api.neople.co.kr/df/items/${emblem.itemId}">
							  		</c:forEach>
						  		</c:if>
						  	</td>
						  	<td>${auction.rows[0].currentPrice}</td>
						  </tr>
						</tbody>
					</c:if>
				
					<tbody class="hide" style='display:none;'>
					<c:forEach var="item" items="${auction.rows}" varStatus="itemNum">
						<c:choose>
							<c:when test="${itemNum.count == 1}">
								<tr class='trLine selectListAuction'>
							</c:when>
							<c:otherwise>
								<tr class='trLine'>
							</c:otherwise>
						</c:choose>
						  	<td>${item.itemTypeDetail}</td>
						  	<td><img src="https://img-api.neople.co.kr/df/items/${item.itemId}"></td>
						  	<td>${item.itemName}</td>
						  	<td>
						  		<c:if test="${!empty item.avatar}">
							  		<c:forEach var="emblem" items="${item.avatar.emblems}">
							  			<img src="https://img-api.neople.co.kr/df/items/${emblem.itemId}">
							  		</c:forEach>
						  		</c:if>
						  	</td>
						  	<td>${item.currentPrice}</td>
						  </tr>
					</c:forEach>
					</tbody>
				</c:forEach>
			 </table>
		 </div>
      </div>
    </section>

	<c:import  url="/footer" />
	<script src="/js/tradedun.js" ></script>
  </body>
</html>