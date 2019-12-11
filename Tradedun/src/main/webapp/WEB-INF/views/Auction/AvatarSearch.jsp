<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - ${title}</title>
    <meta charset="utf-8">
	<c:import  url="/header" />
  </head>
	<style>
		.selectListAuction{
			background: red;
		}
	</style>
  <body>

    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_8.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="/">메인 <i class="ion-ios-arrow-forward"></i></a></span> <span><a href="/auction/auctionMenu">컨텐츠 <i class="ion-ios-arrow-forward"></i></a></span></p>
            <h1 class="mb-3 bread">${title}</h1>
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
      	
      	<div class='row mb-3'>
     		<div class='col-md-5 pr-3 pb-3'>
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
      				<thead>
      					<td class='text-center' colspan="3">아바타 리스트</td>
      				</thead>
      				<c:forEach var="avatar" items="${choiceAvatar}">
						<tr>
							<td style='width:60px;'>${avatar.slotName}</td>
						    <td><img class="pr-3" src='https://img-api.neople.co.kr/df/items/${avatar.itemId}' title='${avatar.itemName}' alt='${avatar.itemName}'/></td>
						    <td><font class='pl-2' style='font-weight: bold;'>${avatar.itemName}</font></td>
						</tr>
					</c:forEach>
				</table>
      		</div>
      	</div>
      	
      	<div>
			 <table id="example" class='compact hover cell-border' style="width:100%">
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
			 		
					  <tr id='listOne' class='trLine' data-slotName="${auction.rows[0].itemTypeDetail}">
					  	<td>${auction.rows[0].itemTypeDetail}</td>
					  	<td><img src="https://img-api.neople.co.kr/df/items/${auction.rows[0].itemId}"></td>
					  	<td>${auction.rows[0].itemName}</td>
					  	<td>${auction.rows[0].avatar.ability}</td>
					  	<td>
					  		<c:if test="${!empty auction.rows[0].avatar}">
						  		<c:forEach var="emblem" items="${auction.rows[0].avatar.emblems}">
						  			<c:choose>
						  				<c:when test="${fn:substring(emblem.itemId, fn:length(emblem.itemId) -6, fn:length(emblem.itemId)) == 'emblem'}">
						  					<img src="/images/emblems/${emblem.itemId}.png" class='emblem_list_size' onerror="this.style.display='none'" data-emblemName='${emblem.itemName}'>
						  				</c:when>
						  				<c:otherwise>
						  					<img src="https://img-api.neople.co.kr/df/items/${emblem.itemId}" class='emblem_list_size' onerror="this.style.display='none'" data-emblemName='${emblem.itemName}'>
						  				</c:otherwise>
						  			</c:choose>
						  		</c:forEach>
					  		</c:if>
					  	</td>
					  	<td data-price='auctionSelectPiece'><fmt:formatNumber value="${auction.rows[0].currentPrice}" pattern="#,###" /></td>
					  	<td>${auction.rows[0].expireDate}</td>
					  </tr>
					</c:if>
				
					<c:forEach var="item" items="${auction.rows}" varStatus="itemNum">
							<tr class='auctionList hide' style='display:none;' data-slotName="${item.itemTypeDetail}">
							<c:choose>
								<c:when test="${itemNum.count == 1}">
									<td>선택됨</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						  	<td><img src="https://img-api.neople.co.kr/df/items/${item.itemId}"></td>
						  	<td>${item.itemName}</td>
						  	<td>${item.avatar.ability}</td>
						  	<td>
						  		<c:if test="${!empty item.avatar}">
							  		<c:forEach var="emblem" items="${item.avatar.emblems}">
							  			<c:choose>
							  				<c:when test="${fn:substring(emblem.itemId, fn:length(emblem.itemId) -6, fn:length(emblem.itemId)) == 'emblem'}">
							  					<img src="/images/emblems/${emblem.itemId}.png" class='emblem_list_size' onerror="this.style.display='none'" data-emblemName='${emblem.itemName}'>
							  				</c:when>
							  				<c:otherwise>
							  					<img src="https://img-api.neople.co.kr/df/items/${emblem.itemId}" class='emblem_list_size' onerror="this.style.display='none'" data-emblemName='${emblem.itemName}'>
							  				</c:otherwise>
							  			</c:choose>
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
	
	<script>
	
		$(document).ready(function() {
			var table = $('#example').DataTable({
				"language": {
					"emptyTable": "검색된 결과가 없습니다."
				},
				info:		false,
				ordering:	false,
				searching:	false,
		        paging:         false,
		        scrollX:        true,
		        scrollXInner: "1100px",
		        //fixedColumns: true,
		        columnDefs:[
		        	{targets: [0], width: "60px"},
		        	{targets: [1], width: "20px"},
		        	{targets: [2], width: "330px"},
		        	{targets: [3], width: "250px"},
		        	{targets: [4], width: "140px"},
		        	{targets: [5], width: "100px"},
		        	{targets: [6], width: "200px"}
		        ]
		    });
		    
			//경매장 리스트 첫번째 라인은 하이라이트
			$('.trLine').css('background', '#ffdab8').css('font-weight', 'bold');
			
		} );
		
		//경매장 리스트 클릭시 테이블 down 
		$(document).on('click', 'table tbody .trLine', function(){
			var slotName = $(this).data('slotname');
			$('tr[data-slotName="'+ slotName +'"].hide').fadeToggle();
		});
	
		//경매장 리스트 선택시 이펙트 및 텍스트 수정
		$(document).on('click', 'table tbody .auctionList', function(){
			var slotName = $(this).data('slotname');
			
			//옵션 부터 가격까지 수정
			for(var i=3; i<7; i++){
				var prev = $(this).children('td').eq(i).html();
				var next = $('tr[data-slotName="'+ slotName +'"].trLine').eq(0);
				next.children('td').eq(i).html(prev);
			}
			
			//선택한 슬롯 중 '선택됨' 텍스트를 모두 제거
			$('tr[data-slotName="'+ slotName +'"].hide').each(function(index, item){
				$(item).eq(0).children('td').eq(0).text('')
			});
			$(this).children('td').eq(0).text('선택됨')
			
			$(this).finish().effect( "highlight", {}, 1000 );
			
			var test = $('*[data-price="auctionSelectPiece"]');
			var price = 0;
			test.each(function(i, item){
				price += Number(fn(item.textContent));
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
		
		//경매장 리스트 클릭시 테이블 down 
		$(document).on('click', 'table tbody .emblem_list_size', function(){
			var emblemName = $(this).data('emblemname');
			toastr.info(emblemName, '엠블렘');
		});
		
		//Toast 설정
		toastr.options = {
				  "closeButton": false,
				  "debug": false,
				  "newestOnTop": false,
				  "progressBar": true,
				  "positionClass": "toast-bottom-center",
				  "preventDuplicates": true,
				  "onclick": null,
				  "showDuration": "300",
				  "hideDuration": "500",
				  "timeOut": "2500",
				  "extendedTimeOut": "5000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				}
	</script>
	
  </body>
</html>