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
            <h1 class="mb-3 bread">판매 글 작성</h1>
          </div>
        </div>
      </div>	
    </section>

	<section class="ftco-section contact-section">
      <div class="container">
        <div class="row d-flex mb-5 contact-info justify-content-center">
        	<div class="col-md-8">
        		<div class="row mb-5">
		          <div class="col-md-4 text-center py-4">
		          	<div class="icon">
		          		<span class="icon-map-o"></span>
		          	</div>
		            <p><span>아바타 조회:</span>판매할 아바타를 입고 있는 캐릭터를 검색</p>
		          </div>
		          <div class="col-md-4 text-center border-height py-4">
		          	<div class="icon">
		          		<span class="icon-mobile-phone"></span>
		          	</div>
		            <p><span>금액 조정:</span>경매장의 최소가 확인 후 조정</p>
		          </div>
		          <div class="col-md-4 text-center py-4">
		          	<div class="icon">
		          		<span class="icon-envelope-o"></span>
		          	</div>
		            <p><span>등록:</span> 판매할 아바타 추가 및 등록</p>
		          </div>
		        </div>
          </div>
        </div>
      </div>    
        
      <div id="search1" class="row block-9 justify-content-center mb-5">
			<div id='charSearch1' class="request-form ftco-animate" >
          		<h2>판매 하실 아바타가 착용된 캐릭터 조회</h2>
          		
   				<div class="d-flex">
   					<div class="form-group mr-2">
		                <label for="" class="label">서버</label>
		                <div class="form-field">
          					<div class="select-wrap">
		                      <select id="server1" class="form-control">
		                      	<option value="bakal">바칼</option>
		                        <option value="cain">카인</option>
		                        <option value="anton">안톤</option>
		                      </select>
		                    </div>
		               </div>
	            	</div>
	             	<div class="form-group ml-2"></div>
            	</div>
   				<div class="form-group">
   					<label for="" class="label">캐릭터</label>
   					<input type="text" id="character1" name='character' class="form-control" placeholder="Your Character Name">
   				</div>
	            <div class="form-group">
	              	<input type="button" onclick="search(1)" value="검색" class="btn btn-primary py-3 px-4">
	            </div>
  			</div>
  			
  			<!--  수정 중 -->
  			<div id='charBox1' class="request-form ftco-animate container" style='display:none;'>
          		<div class="row">
	    		</div>
  			</div>
  			
      </div>
      
	  <div class="row block-9 justify-content-center mb-5">
		<div class="request-form ftco-animate">
            <div class="form-group">
              	<input type="button" value="등록하기" class="btn-primary py-3 px-4">
            </div>
 		</div>
	  </div>

    </section>

	<c:import  url="/footer" />
	
    <script>
    	
    	$(document).on('click', 'table tbody .trLine', function(){
            $(this).parents().next('.hide').fadeToggle();
        });
    
    	function search(num){
    		var server = $('#server'+num).val();
    		var character = $('#character'+num).val();
    		
    		$.ajax({
   			  	url: "/auction/charSeachList",
   			    data: {
   			    	server:server,
   			    	character:character,
   			    	number:num
   			    },
   			    type: "GET",
   			 	async: false,
   			    cache: false,
   			    success: function(data){
   			    	$('#charSearch'+num).hide();
   			    	$('#charBox'+num).show();
   			    	
   			    	$('#charBox'+num+' .row').append(data);	
   			    },
   			    error: function (request, status, error){        
   			    }
    		});
    	}
    	
    	function avartar(num, charNum){
    		var server = $('#charServer'+charNum).val();
    		var character = $('#charName'+charNum).val();
    		
    		$.ajax({
   			  	url: "/auction/charAvatarSeach",
   			    data: {
   			    	server:server,
   			    	character:character,
   			    	number:num
   			    },
   			    type: "GET",
   			 	async: false,
   			    cache: false,
   			    success: function(data){
   			    	$('#charBox'+num+' .row').remove();
   			    	
   			    	$('#charBox'+num).append(data);
   			    },
   			    error: function (request, status, error){        
   			    }
    		});
    	}
    </script>
    
  </body>
</html>