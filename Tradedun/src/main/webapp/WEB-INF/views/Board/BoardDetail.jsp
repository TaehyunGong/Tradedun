<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>TradeDun - ${board.title}</title>
    <meta charset="utf-8">
    <c:import  url="/header" /> 
  </head>
  <body>
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_6.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="/">메인 <i class="ion-ios-arrow-forward"></i></a></span> <span class="mr-2"><a href="/board/notice">공지사항 <i class="ion-ios-arrow-forward"></i></a></span></p>
            <h1 class="mb-3 bread">${board.categoryName} 상세</h1>
          </div>
        </div>
      </div>
    </section>

	<section class="ftco-section ftco-degree-bg">
	    <div class="container">
	        <div class="row mb-5 justify-content-center">
	            <div class="col-md-10 order-md-last ftco-animate mb-2">
	                <h1 class="mb-3">${fn:escapeXml(board.title)}</h1>
	                <hr> 
	                <div class='mb-3' style='min-height: 200px;'>
	                	${board.contents}
	                </div>
	                <hr>
	                <div class="row mt-3">
		                <div class="col-lg align-self-end">
		                	<div class="form-group">
			                    <input type="button" onclick='forwarding("list");' value="리스트" class="form-control btn btn-primary"/>
		                	</div>
		                </div>
		                <div class="col-lg align-self-end">
		                	<div class="form-group">
		                		<c:if test="${sessionScope.user.userNo eq '1192936782' || session.user.userNo eq board.userNo}">
				                    <input type="button" onclick='forwarding("modify");' value="글 수정" class="form-control btn btn-success"/>
		                		</c:if>
		                	</div>
		                </div>
		                <div class="col-lg align-self-end">
		                	<div class="form-group">
		                		<c:if test="${sessionScope.user.userNo eq '1192936782' || session.user.userNo eq board.userNo}">
				                    <input type="button" onclick='forwarding("delete");' value="글 삭제" class="form-control btn btn-danger"/>
		                		</c:if>
		                	</div>
		                </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</section>

	<c:import  url="/footer" />
    
    <script>
    
    	//누른 버튼에 따라 포워딩
    	function forwarding(page){
    		var result = '/';
    		
    		switch(page){
    			case 'modify' : result = '/board/boardModify?boardNo=${board.boardNo}&categoryCode=${board.categoryCode}'; break;
    			case 'delete' : result = '/board/boardDelete?boardNo=${board.boardNo}&categoryCode=${board.categoryCode}'; break;
    			case 'list' : result = '/board/notice'; break;
    		}
    		
    		location.href = result;
    	}
    </script>
  </body>
</html>