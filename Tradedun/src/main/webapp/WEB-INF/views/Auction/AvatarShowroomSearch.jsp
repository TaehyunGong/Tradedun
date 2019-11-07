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
		 <table class="table" >
			<c:forEach var="auction" items="${auctions}">
				<tbody id='' class="selectAuction auctionList">
				<c:forEach var="item" items="${auction.rows}">
				  <tr class='trLine'>
				  	<td>${item.itemTypeDetail}</td>
				  	<td><img src="https://img-api.neople.co.kr/df/items/${item.itemId}"></td>
				  	<td>${item.itemName}</td>
				  	<td>${item.itemTypeDetail}</td>
				  	<td>${item.currentPrice}</td>
				  </tr>
				</c:forEach>
				</tbody>
			</c:forEach>
			
			<tbody class="hide" style='display:none;'>
				<tr>
		 		</tr>
			</tbody>
		 </table>
      </div>
    </section>

	<c:import  url="/footer" />
  </body>
</html>