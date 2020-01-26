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
	
				<div class="col-md-9 ftco-animate">
					<div>
						<h2>총 조회 건수 : <span style="color:#f7b71d;">${logCount}</span></h2>
					</div>
                   <table class="table text-center ">
                       <thead class="thead-primary">
                           <tr class="text-center">
                           	<th class="heading">*</th>
                               <th class="heading">날짜</th>
                               <th class="heading">컨텐츠</th>
                               <th class="heading">직업</th>
                               <th class="heading">차수 or 아바타</th>
                               <th class="heading">조회</th>
                           </tr>
                       </thead>
                       <tbody>
                       	<c:forEach var="log" items="${list}" varStatus="num">
                           <tr>
                           	<c:choose>
                           		<c:when test="${log.requestUrl eq '/auction/avatarCharacterSetSearch'}">
	                                <td>${log.ROWNUM }</td>
	                                <td>${log.createDT }</td>
	                                <td>레어아바타 검색</td>
	                                <td>${log.codeName }</td>
	                                <td>${log.categoryName }</td>
		                            <td><a href="${log.requestUrl}?jobId=${log.attr1}&categoryCode=${log.attr2}" class="btn btn-primary">검색</a></td> 
                           		</c:when>
                           		<c:when test="${log.requestUrl eq '/auction/avatarShowroomSearch'}">
									<td>${log.ROWNUM }</td>
	                                <td>${log.createDT }</td>
	                                <td>쇼룸 아바타 검색</td>
	                                <td>${log.codeName }</td>
	                                <td><a href="#${num.count}showroom" rel="modal:open" class='btn btn-success'>상세보기</a></td>
	                                <td><button onclick="submitShowroom('${num.count }')" class="btn btn-primary">검색</button></td>
                           		</c:when>
                           	</c:choose>
                           </tr>
                       	</c:forEach>
                       </tbody>
                   </table>
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
		function submitShowroom(num){
			$('#showroomForm'+num).submit();
		}
	</script>

	<c:forEach var="pop" items="${list}" varStatus="num">
		<c:if test="${pop.requestUrl eq '/auction/avatarShowroomSearch'}">
			<div id="${num.count}showroom" class="modal">
				<form id="showroomForm${num.count }" action="/auction/avatarShowroomSearch" method="post">
					<input type="hidden" name="jobId" value="${pop.attr1 }">
					<textarea name="showroom" style="width:100%; height:350px; border: none; resize: none;" readonly="readonly">${pop.attr2 }</textarea>
				</form>
				<a href="#" rel="modal:close" class='btn btn-danger'>닫기</a>
			</div>
		</c:if>
	</c:forEach>

	<c:import  url="/footer" />
  </body>
</html>