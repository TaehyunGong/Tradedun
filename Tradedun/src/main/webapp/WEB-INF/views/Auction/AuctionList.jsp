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
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>About us <i class="ion-ios-arrow-forward"></i></span></p>
            <h1 class="mb-3 bread">아바타 판매</h1>
          </div>
        </div>
      </div>
    </section>

	<section class="ftco-section ftco-no-pb ftco-no-pt">
	    <div class="container">
	        <div class="row">
	            <div class="col-md-12">
	                <div class="search-wrap-1 ftco-animate mb-5">
	                
	                    <form action="/auction/AuctionList" class="search-property-1">
	                        <div class="row">
	                            <div class="col-lg align-items-end">
	                                <div class="form-group">
	                                    <label for="#">직군</label>
	                                    <div class="form-field">
	                                        <div class="select-wrap">
	                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
	                                            <select name="jobId" id="jobList" class="form-control">
	                                                <option value="all">전체</option>
	                                            </select>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-lg align-items-end">
	                                <div class="form-group">
	                                    <label for="#">직업</label>
	                                    <div class="form-field">
	                                        <div class="select-wrap">
	                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
	                                            <select name="jobGrowId" id="jobGrowList" class="form-control">
	                                                <option value="">검신</option>
	                                            </select>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-lg align-items-end">
	                                <div class="form-group">
	                                    <label for="#">아바타 종류</label>
	                                    <div class="form-field">
	                                        <div class="select-wrap">
	                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
	                                            <select name="categoryCode" id="avatarList" class="form-control">
	                                                <option value="">이벤트 아바타</option>
	                                            </select>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="col-lg align-items-end">
	                                <div class="form-group">
	                                    <label for="#">가격</label>
	                                    <div class="form-field">
	                                        <div class="select-wrap">
	                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
	                                            <select name="priceRange" id="priceRange" class="form-control">
	                                                <option value="0">제한없음</option>
	                                                <option value="1">1000만 이하</option>
	                                                <option value="2">2000만 이하</option>
	                                                <option value="3">3000만 이하</option>
	                                                <option value="5">5000만 이하</option>
	                                                <option value="7">7000만 이하</option>
	                                                <option value="10">1억 이하</option>
	                                                <option value="99">1억 초과</option>
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

    <section class="ftco-section">
    
    	<div class="container">
    		<div id='listSpace' class="row">
    			<c:forEach var="board" items="${boardList}">
	    			<div class="col-md-3">
	    				<div class="car-wrap ftco-animate">
	    					<div class="img d-flex align-items-end" style="background-image: url(/upImage/CharacterImages/${board.imageName});">
	    						<div class="price-wrap d-flex">
	    							<span class="rate"><fmt:formatNumber value="${board.totalPrice}" pattern="#,###" /></span>
	    							<p class="from-day">
	    								<span>-</span>
	    								<span>/Gold</span>
	    							</p>
	    						</div>
	    					</div>
	    					<div class="text p-4 text-center">
	    						<h2 class="mb-0"><a href="/auction/AuctionDetail?boardNo=${board.boardNo}&charBox=${board.charBox}">${board.subject }</a></h2>
	    						<span>다크 임페리얼</span>
	    						<p class="d-flex mb-0 d-block">
		    						<a href="#" class="btn btn-black btn-outline-black mr-1">스크랩</a> 
		    						<a href="/auction/AuctionDetail?boardNo=${board.boardNo}&charBox=${board.charBox}" class="btn btn-black btn-outline-black ml-1">상세보기</a>
	    						</p>
	    					</div>
	    				</div>
	    			</div>
    			</c:forEach>
    			
    		</div>
    		<div id="loadBox" style='display:none;'>
    			<div class="text-center align-items-center animated fadeInDown">
    				<img src="/images/loading.gif">
    			</div>
    		</div>
    	</div>
    </section>
    
	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px">
		<circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
		<circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/>
		</svg>
	</div>
	
	<script src="/js/main.js"></script>
    <script>
		
    	var page = 1;
    	var pageValiable = true;
    	
		$(function(){
			
			//무한 페이징 처리
	        $(window).scroll(function() {
	        	
	            if ($(document).height() - $(window).height() - 1 <= $(window).scrollTop()) {
	               //중복 실행 방지를 위해 조건 추가
	            	if(pageValiable){
	            		pageValiable = false;
	            		$('#loadBox').show();
	            		
		        		$.ajax({
		       			  	url: "/auction/auctionAvatarListPaging",
		       			    data: {
		       			    	page : page,
		       			    	jobId : $('#jobList').val(),
		       			    	jobGrowId : $('#jobGrowList').val(),
		       			    	categoryCode : $('#avatarList').val(),
		       			    	priceRange : $('#priceRange').val()
		       			    },
		       			    type: "GET",
		       			 	async: false,
		       			    cache: false,
		       			    success: function(data){
		       			    	$('#loadBox').hide();
		       			    	page += 1;
		       			    	$('#listSpace').append(data);
		       			    	
		       			    	//템플릿을 가져지 못했거나 더이상 board가 없다면 더이상 이 ajax를 실행하지 않도록 한다.
		       			    	if(data.length <= 100){
			       			    	pageValiable = false;
			       			    	
			       			    	//$('#listSpace').append('<div class="col-md-12"><div class="text-center align-items-center animated fadeInDown"><img src="/images/loading.gif"></div></div>');
		       			    	}else{
		       			    		pageValiable = true;
		       			    	}
		       			    },
		       			    error: function (request, status, error){
		       			    	alert('잘못된 경로입니다.\n다시 시도해주세요.');
		       			    	pageValiable = true;
		       			    }
		        		});
	            	}
	            }
	        });			
			
			//DB에서 직군+차수 리스트를 가져와 json배열로 삽입
			var rareAvatarList = JSON.parse('${jobGrowAvatarList}');
			
			//페이지 로딩시 리스트중 가장 첫번째 직군리스트를 select에 삽입
			rareAvatarList.forEach(function(item, index, array){
				$('#jobList').append("<option value='" + item.jobId + "'>" + item.jobName + "</option>");
			});
			//페이징 로딩시 삽입된 직군에 맞는 차수 리스트를 select에 삽입
			changeOption(rareAvatarList[0].jobId);
			
			//직군 select change 이벤트
			$('#jobList').change(function(){
				changeOption(this.value);
			})
			
			//페이지 로드시 이전에 필터링했던 조건을 그대로 적용시켜준다.
			$('#jobList').val('${jobId}').prop('selected', true).trigger('change');
			$('#jobGrowList').val('${jobGrowId}').prop('selected', true);
			$('#avatarList').val('${categoryCode}').prop('selected', true);
			$('#priceRange').val('${priceRange}').prop('selected', true);
			
			//직군의 value값에 맞쳐 차수 select option을 바꿔준다.(캐스캐이딩 적용)
			function changeOption(jobId){
				$('#jobGrowList').empty();
				$('#avatarList').empty();
				
				$('#jobGrowList').append("<option value='all'>전체</option>");
				$('#avatarList').append("<option value='all'>전체</option>");
				
				//json배열 전체를 반복하여 jobId에 일치하는 객체를 찾는다.
				rareAvatarList.forEach(function(item, index, array){
					
					//jobId와 일치하는 avatarList 배열을 찾는다.
					if(item.jobId == jobId){
						item.avatarList.forEach(function(avatar){
							var value = avatar.categoryCode;
							var name = avatar.categoryName;
							$('#avatarList').append("<option value='" + value + "'>" + name + "</option>");
						});
						
						item.jobGrowList.forEach(function(jobGrow){
							var jobGrowId = jobGrow.jobGrowId;
							var jobGrowName = jobGrow.jobGrowName;
							$('#jobGrowList').append("<option value='" + jobGrowId + "'>" + jobGrowName + "</option>");
						});
						return false;
					}
				});
				
			}
			
		});
		
	</script>
  </body>
</html>