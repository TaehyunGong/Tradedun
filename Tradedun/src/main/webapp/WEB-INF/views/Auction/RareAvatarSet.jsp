<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 레어아바타 검색</title>
    <meta charset="utf-8">
    <meta name="description" content="레어아바타, 레압 경매장 검색" />
	<c:import  url="/header" />    
  </head>
  <body>

    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_4.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="/">메인 <i class="ion-ios-arrow-forward"></i></a></span> <a href="/auction/auctionMenu"><span>컨텐츠 <i class="ion-ios-arrow-forward"></i></span></a></p>
            <h1 class="mb-3 bread">레어아바타 세트 검색</h1>
          </div>
        </div>
      </div>	
    </section>

	<section class="ftco-section">
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

	<c:import  url="/footer" />
	<script>
		
		$(function(){
			//DB에서 직군+차수 리스트를 가져와 json배열로 삽입
			var rareAvatarList = JSON.parse('${avatarList}');
			
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
			
			//직군의 value값에 맞쳐 차수 select option을 바꿔준다.(캐스캐이딩 적용)
			function changeOption(jobId){
				$('#avatarList').empty();
				//json배열 전체를 반복하여 jobId에 일치하는 객체를 찾는다.
				rareAvatarList.forEach(function(item, index, array){
					
					//jobId와 일치하는 avatarList 배열을 찾는다.
					if(item.jobId == jobId){
						item.avatarList.forEach(function(avatar){
							var value = avatar.categoryCode;
							var name = avatar.categoryName;
							$('#avatarList').append("<option value='" + value + "'>" + name + "</option>");
						});
						return false;
					}
				})
			}
		});
		
	</script>
  </body>
</html>