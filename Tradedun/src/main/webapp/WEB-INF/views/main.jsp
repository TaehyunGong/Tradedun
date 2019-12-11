<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun</title>
    <meta charset="UTF-8">
  </head>
  <body>
    <c:import  url="/?page=/common/header" />
    
	<div class="hero-wrap" style="background-image: url('/images/nbg_3.png');" data-stellar-background-ratio="0.5">
	    <div class="overlay"></div>
	    <div class="container">
	        <div class="row no-gutters slider-text justify-content-start align-items-center">
	            <div class="col-lg-6 col-md-6 ftco-animate d-flex align-items-end">
	                <div class="request-form main-notice-left">
	                	<h2>레어 아바타 검색</h2>
			          	<div class="justify-content-end"> 
			              <a href="blog-single.html" class="block-20" style="background-image: url('/images/image_1.jpg');">
			              </a>
			              <div class="text pt-4">
			                <p>여러가지의 레어 아바타를 바로 검색 해보세요</p>
			              </div>
			            </div>
	                </div>
	            </div>
	            <div class="col-lg-1 col"></div>
	            <div class="col-lg-5 col-md-6 md-5 ">
	                <div class="request-form ftco-animate">
	                    <h2>공지사항</h2>
	                    <table class="table main-notice">
	
	                        <c:set var="today" value="<%=new Date(new Date().getTime() - 60*60*24*1000 * 7) %>" />
	                        <fmt:formatDate var="todatFormmat" value="${today}" pattern="yyyyMMdd" />
	
	                        <%-- <c:forEach var="board" items="${boardList}" begin="0" end="10"> --%>
                            <c:forEach var="num" begin="0" end="7">
                                <fmt:formatDate var="createDT" value="${boardList[num].createDT}" pattern="yyyyMMdd" />

                                <c:choose>
                                    <c:when test="${empty boardList[num]}">
                                        <tr>
                                            <td></td>
                                            <td>-</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="used" onclick="location.href='/board/boardDetail?boardNo=${boardList[num].boardNo}&categoryCode=${boardList[num].categoryCode }'">
                                            <td>
                                                <c:if test="${createDT >= todatFormmat}"><i class="fas fa-bell" style="color: red;"></i></c:if>
                                            </td>
                                            <td>${boardList[num].title}</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>

    <section class="ftco-section services-section ftco-no-pb">
      <div class="container">
    	<div class="row block-12 justify-content-center mb-3">
			<div class="col-md-12 heading-section text-center mb-3">
				<span class="subheading">RareAvatar Auction Search</span>
				<h2 class="mb-2">레어아바타 세트 경매장 검색</h2>
         	</div>
       	</div>
    
        <div class="row">
            <div class="col-md-12">
                <div class="search-wrap-1 ftco-animate mb-5">
                
                    <form action="/auction/avatarCharacterSetSearch" class="search-property-1">
                        <div class="row">
                            <div class="col-lg align-items-end">
                                <div class="form-group">
                                    <label for="#">직군</label>
                                    <div class="form-field">
                                        <div class="select-wrap">
                                        
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                            <select name="jobId" id="jobList" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg align-items-end">
                                <div class="form-group">
                                    <label for="#">차수</label>
                                    <div class="form-field">
                                        <div class="select-wrap">
                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                            <select name="categoryCode" id="avatarList" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg align-self-end">
                                <div class="form-group">
                                    <div class="form-field">
                                        <input type="submit" value="Search" class="form-control btn btn-primary">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
      </div>
    </section>

	<section class="ftco-section ftco-no-pt">
		<div class="container">
			<div class="row no-gutters">
				<div class="col-md-6 p-md-5 img img-2 d-flex justify-content-center align-items-center" style="background-image: url(/images/about.jpg);">
				</div>
				<div class="col-md-6 wrap-about py-md-5 ftco-animate">
          <div class="heading-section mb-5 pl-md-5">
          	<span class="subheading">ShowRoom Auction Search</span>
            <h2 class="mb-4">쇼룸 경매장 검색</h2>

            <p>자기가 꾸민 코디의 가격을 한눈에 검색 해보세요</p>
            <p>던전앤파이터 공식 홈페이지의 쇼룸에서 꾸민 코디를 저장하고 위시리스트에서 코디의 리스트를 긁어와 현재 시간기준으로 각각의 아바타 가격을 확인하실수 있습니다.</p>
            <p><a href="http://df.nexon.com/df/showroom#/dressroom" target="_blank" class="btn btn-warning">쇼룸 바로가기</a> <a href="/auction/avatarShowroom" class="btn btn-success">쇼름 검색 바로가기</a></p>
          </div>
				</div>
			</div>
		</div>
	</section>

	<c:import  url="/?page=/common/footer" />

  </body>
</html>