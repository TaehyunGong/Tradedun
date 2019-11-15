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
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/bg_1.jpg');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Contact <i class="ion-ios-arrow-forward"></i></span></p>
            <h1 class="mb-3 bread">AvatarSearch</h1>
          </div>
        </div>
      </div>	
    </section>

    
	<section class="ftco-section contact-section">
      <div class="container">
      
      	<div class='row justify-content-center'>
      		<div class="col-md-12 heading-section text-center ftco-animate mb-5 fadeInUp ftco-animated">
          	<span class="subheading">조회 결과</span>
            <h2 class="mb-2">아바타 경매장 조회 결과</h2>
          </div>
      	</div>
      	
      	<div class='row'>
     		<div class='col-md-5 pr-3'>
				<!-- 판매 설정 -->
		      	<div class="request-form">
		      		<h3>조회 결과</h3>
		      		<div class="form-group">
		                <label for="" class="label">경매장에 조회된 아바타 부위</label>
		                <input type="text" class="form-control" id="" value="${searchCount}부위" readonly>
		            </div>
		            
		            <div class="form-group">
		                <label for="" class="label">선택된 아바타 가격</label>
		                <input type="text" class="form-control" id="auctionSumPrice" value="<fmt:formatNumber value="${rowPriceSum}" pattern="#,###" />" readonly>
		            </div>
				
					<div class="d-flex">
						<div class="form-group ml-2">
							<input type="button" onclick="allClose()" value="리스트 모두 닫기" class="btn btn-primary">
						</div>
						
						<div class="form-group ml-2">
							<input type="button" onclick="allOpen()" value="리스트 모두 열기" class="btn btn-primary">
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
			 <table id="example" class='stripe row-border order-column' style="width:100%">
			 	<thead>
			 		<tr>
			 			<td>부위</td>
			 			<td></td>
			 			<td>아바타명</td>
			 			<td>옵션</td>
			 			<td>엠블렘</td>
			 			<td>가격</td>
			 			<td>만료일자</td>
			 		</tr>
			 	</thead>
			 	<c:forEach var="auction" items="${auctions}">
			 		<c:if test="${!empty auction.rows[0]}">
			 		
					  <tr class='trLine' data-slotName="${auction.rows[0].itemTypeDetail}">
					  	<td>${auction.rows[0].itemTypeDetail}</td>
					  	<td><img src="https://img-api.neople.co.kr/df/items/${auction.rows[0].itemId}"></td>
					  	<td>${auction.rows[0].itemName}</td>
					  	<td>${auction.rows[0].avatar.ability}</td>
					  	<td>
					  		<c:if test="${!empty auction.rows[0].avatar}">
						  		<c:forEach var="emblem" items="${auction.rows[0].avatar.emblems}">
						  			<img src="https://img-api.neople.co.kr/df/items/${emblem.itemId}">
						  		</c:forEach>
					  		</c:if>
					  	</td>
					  	<td data-price='auctionSelectPiece'><fmt:formatNumber value="${auction.rows[0].currentPrice}" pattern="#,###" /></td>
					  	<td>${auction.rows[0].expireDate}</td>
					  </tr>
					</c:if>
				
					<c:forEach var="item" items="${auction.rows}" varStatus="itemNum">
						<c:choose>
							<c:when test="${itemNum.count == 1}">
								<tr class='auctionList selectListAuction hide' style='display:none;' data-slotName="${item.itemTypeDetail}">
							</c:when>
							<c:otherwise>
								<tr class='auctionList hide' style='display:none;' data-slotName="${item.itemTypeDetail}">
							</c:otherwise>
						</c:choose>
						  	<td>${item.itemTypeDetail}</td>
						  	<td><img src="https://img-api.neople.co.kr/df/items/${item.itemId}"></td>
						  	<td>${item.itemName}</td>
						  	<td>${item.avatar.ability}</td>
						  	<td>
						  		<c:if test="${!empty item.avatar}">
							  		<c:forEach var="emblem" items="${item.avatar.emblems}">
							  			<img src="https://img-api.neople.co.kr/df/items/${emblem.itemId}">
							  		</c:forEach>
						  		</c:if>
						  	</td>
						  	<td><fmt:formatNumber value="${item.currentPrice}" pattern="#,###" /></td>
						  	<td>${item.expireDate}</td>
						  </tr>
					</c:forEach>
					
				</c:forEach>
			 </table>
		 </div>
      </div>
    </section>

	<c:import  url="/footer" />
	<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/fixedheader/3.1.6/js/dataTables.fixedHeader.min.js"></script>
	<script src="/js/tradedun.js" ></script>
	
	<script>
	
		$(document).ready(function() {
			var table = $('#example').DataTable({
				//info:		false,
				ordering:	false,
				searching:	false,
		        paging:         false,
		        scrollX:        true,
		        scrollXInner: "1100px",
		        fixedColumns: true,
		        columnDefs:[
		        	{targets: [0], width: "60px"},
		        	{targets: [1], width: "20px"},
		        	{targets: [2], width: "330px"},
		        	{targets: [3], width: "260px"},
		        	{targets: [4], width: "140px"},
		        	{targets: [5], width: "100px"},
		        	{targets: [6], width: "200px"}
		        ]
		    });
		    
		} );
		
		//경매장 리스트 클릭시 테이블 down 
		$(document).on('click', 'table tbody .trLine', function(){
			var slotName = $(this).data('slotname');
			console.log(slotName);
			//$('#charInfo'+num).data('server')
		    //$(this).parents().next('.hide').fadeToggle(); 
			//$('tr[data-slotName="'+ slotName +'"]').parents().next('.hide').fadeToggle();
			$('tr[data-slotName="'+ slotName +'"].hide').fadeToggle();
		});
	
		//경매장 리스트 선택시 이펙트 및 텍스트 수정
		$(document).on('click', 'table tbody .auctionList', function(){
			var tbody = $(this).parents().prev('.selectAuction')[0];
			
			//옵션 부터 가격까지 수정
			for(var i=2; i<6; i++){
				var prev = $(this).children('td').eq(i).html();
				var next = $(tbody).children().children('td').eq(i).html(prev);
			}
			$(this).siblings().removeClass('selectListAuction');
			$(this).addClass('selectListAuction')
			$(tbody).finish().effect( "highlight", {}, 1000 );
			
			var test = $('*[data-price="auctionSelectPiece"]');
			var price = 0;
			test.each(function(i, item){
				price += Number(fn(item.textContent));
				console.log(Number(fn(item.textContent)))
			});
			
			$('#auctionSumPrice').val(numberWithCommas(price));
		});
		
		//펼쳐있는 경매장 리스트를 모두 닫는다.
		function allClose(){
			$('.hide').hide();
		}
		
		//닫혀있는 경매장 리스트를 모두 펼친다.
		function allOpen(){
			$('.hide').show();
		}
	</script>
	
  </body>
</html>