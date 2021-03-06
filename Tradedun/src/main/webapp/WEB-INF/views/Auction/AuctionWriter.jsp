<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 판매 글 작성</title>
    <meta charset="utf-8">
	<c:import  url="/header" />    
  </head>
  <body>

    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_6.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="/">메인 <i class="ion-ios-arrow-forward"></i></a></span> <a href="/auction/auctionMenu"><span>컨텐츠 <i class="ion-ios-arrow-forward"></i></span></a></p>
            <h1 class="mb-3 bread">판매 글 작성</h1>
          </div>
        </div>
      </div>	
    </section>

	<section id="charBoxList" class="ftco-section contact-section">
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
		                        <option value="diregie">디레지에</option>
		                        <option value="siroco">시로코</option>
		                        <option value="prey">프레이</option>
		                        <option value="casillas">카시야스</option>
		                        <option value="hilder">힐더</option>
		                      </select>
		                    </div>
		               </div>
	            	</div>
	             	<div class="form-group ml-2"></div>
            	</div>
   				<div class="form-group">
   					<label for="" class="label">캐릭터</label>
   					<input type="text" id="character1" onkeyup="enterkey(1);"  name='character' class="form-control" placeholder="Your Character Name">
   				</div>
	            <div class="form-group">
	              	<input type="button" onclick="search(1)" value="검색" class="btn btn-primary py-3 px-4">
	            </div>
  			</div>
  			
  			<!--  수정 중 -->
  			<div id='charBox1' class="request-form ftco-animate container" style='display:none;'>
  			</div>
  			
      </div>
      

    </section>
    
	  <div id='applyBox' class="row block-9 justify-content-center mb-5">
		<div class="request-form">
            <div class="">
            	<input type="button" onclick='addCharBox()' value="판매 캐릭 추가" class="btn-success py-1 px-4">
              	<input type="button" onclick='salesBoardSubmit()' value="판매 글 등록" class="btn-primary py-1 px-4">
            </div>
 		</div>
	  </div>

	<c:import  url="/footer" />
	
    <script>
    	
    	//charBox갯수
    	var number = 1;
    	var numberList = new Array();
		
    	//jquery ui tooltip, init
   	    $(function () {
    	    $( document ).tooltip();
   		});
    
    	//경매장 리스트 클릭시 테이블 down 
    	$(document).on('click', 'table tbody .trLine', function(){
            $(this).parents().next('.hide').fadeToggle();
        });

		//경매장 리스트 선택시 이펙트 및 텍스트 수정
    	$(document).on('click', 'table tbody .auctionList', function(){
    		var tbody = $(this).parents().prev('.selectAuction')[0];

			//옵션 부터 가격까지 수정
			for(var i=2; i<5; i++){
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
			});
			
			var charBox = $(this).parents()[4];
			var charBoxId = $(charBox).attr('id');
			var charBoxNumber = charBoxId.substr(charBoxId.length - 1);
			
			$('#auctionSumPrice' + charBoxNumber).val(numberWithCommas(price));
        });
    	
    	//캐릭터 선택창에서 뒤로가기
		function backSearch(num){
		    $('#charBox'+num).hide();
			$('#charBox'+num + ' *').remove();
		    numberList = numberList.filter(function(numberList) {
		    	  return numberList !== num
	    	})
		    
		    console.log(numberList)
		    $('#charSearch'+num).show();
		}
    	
    	//charBox 삭제
    	function deleteCharBox(num){
    		$('#search'+num).remove();
    	}
    
    	//캐릭터 조회창
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
   			    	
   			    	$('#charBox'+num).append(data);	
   			    },
   			    error: function (request, status, error){       
   			    	alert(request)
   			    }
    		});
    	}
    	
    	//아바타 가격 리스트 가져옴
    	function avartar(num, server, character, kind){
    		
    		$.ajax({
   			  	url: "/auction/charAvatarSeach",
   			    data: {
   			    	server:server,
   			    	character:character,
   			    	number:num,
   			    	kind:kind
   			    },
   			    type: "GET",
   			 	async: false,
   			    cache: false,
   			    success: function(data){
   			    	if(data == '0'){
   			    		alert('착용한 아바타가 없습니다.');
   			    		return false;
   			    	}
   			    	
   			    	$('#charBox'+num+' .row').hide();
   			    	
   			    	$('#charBox'+num).append(data);
   			    	numberList.push(num);
   			    	console.log(numberList)
   			    },
   			    error: function (request, status, error){     
   			    	alert(request)
   			    }
    		});
    	}
    	
    	//charBox 추가
    	function addCharBox(){
    		number+=1;
    		
    		$.ajax({
   			  	url: "/auction/addCharBox",
   			    data: {
   			    	number:number
   			    },
   			    type: "GET",
   			 	async: false,
   			    cache: false,
   			    success: function(data){
   			    	$('#charBoxList').append(data);
   			    },
   			    error: function (request, status, error){
   			    	alert('잘못된 경로입니다.\n다시 시도해주세요.')
   			    }
    		});
    	}
    	
    	//charBox의 모든 리스트를 가져와 JSON으로 변환 후 서버에 submit
    	function salesBoardSubmit(){
			//판매가격을 안적은 input으로 포커싱
			for(var index=0; index < numberList.length; index++){
                if($('#resultPrice'+numberList[index]).val().length == 0){
					alert('판매 가격을 설정해 주세요.');
					$('#resultPrice'+numberList[index]).focus();
					return false;
				}
            }
			
    		var totalJsonArray = new Array();
    		//유효한 charBox 번호 를 반복
			numberList.forEach(function (num, index, array) {

    			var jsonArray = new Array();
	    		for(var i=1; i<10; i++){
	    			var jsonObj = new Object();
	    			jsonObj.slotName = $('#charBoxAvatarList'+num+i).data('slotname');
	    			jsonObj.emblemName = $('#charBoxAvatarList'+num+i).data('emblemname');
	    			jsonObj.optionAbility = $('#charBoxAvatarList'+num+i).data('optionability');
	    			jsonObj.itemId = $('#charBoxAvatarList'+num+i).data('itemid');
	    			jsonObj.itemName = $('#charBoxAvatarList'+num+i).data('itemname');
	    			
					jsonArray.push(jsonObj);
	    		}
	    		
				var jsonObj = {
					'server'  : $('#charInfo'+num).data('server'),
					'slotName': $('#charInfo'+num).data('slotname'),
					'charId'  : $('#charInfo'+num).data('charid'),
					'charName': $('#charInfo'+num).data('charname'),
					'jobId': $('#charInfo'+num).data('jobid'),
					'jobGrowId': $('#jobGrow'+num).val(),
					'avatar'  : jsonArray,
					'resultPrice' : fn( $('#resultPrice'+num).val() ),
					'category' : $('#category'+num).val(),
					'comment' : $('#comment'+num).val()
				};

				totalJsonArray.push(jsonObj);
			});
			
    		//아무것도 등록하지 않고 판매 글 등록했을때 경고 출력 후 빽
    		if(totalJsonArray.length == 0){
    			alert('최소 하나 이상의 캐릭터를 등록하셔야합니다.');
    			$('#character1')[0].scrollIntoView({
    				behavior: 'smooth'
    			});
				$('#character1').focus();
    			return false;
    		}
    		console.log(totalJsonArray)
			var json = JSON.stringify(totalJsonArray);
			
			//동적 form submit Post
			var form = $(document.createElement('form'));
		    $(form).attr("action", "/auction/insertBoardWrite");
		    $(form).attr("method", "POST");

		    var jsonInput = $("<input>").attr("type", "hidden").attr("name", "submitJson").val(json);
		    var subjectInput = $("<input>").attr("type", "hidden").attr("name", "subject").val('');
		    
		    $(document.body).append(form);
		    $(form).append($(jsonInput));
		    $(form).append($(subjectInput));
		    
		    $(form).submit();
    	}
    	
    </script>
    
  </body>
</html>