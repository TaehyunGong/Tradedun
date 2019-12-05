<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
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
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span class="mr-2"><a href="blog.html">Blog <i class="ion-ios-arrow-forward"></i></a></span> <span>Blog Single <i class="ion-ios-arrow-forward"></i></span></p>
            <h1 class="mb-3 bread">글 상세</h1>
          </div>
        </div>
      </div>
    </section>

	<section class="ftco-section ftco-degree-bg">
	    <div class="container">
	        <div class="row mb-5 justify-content-center">
	            <div class="col-md-8 order-md-last ftco-animate mb-2">
	                <h1 class="mb-3">${board.title }</h1>
	                <hr> 
	                <div class='' style='min-height: 200px;'>
	                	${board.contents}
	                </div>
	                <hr>
	                <div class="row">
		                <div class="col-lg align-self-end">
		                	<div class="form-group">
			                    <input type="button" onclick='forwarding("modify");' value="글 수정" class="form-control btn btn-primary"/>
		                	</div>
		                </div>
		                <div class="col-lg align-self-end">
		                	<div class="form-group">
			                    <input type="button" onclick='forwarding("list");' value="리스트" class="form-control btn btn-primary"/>
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
    			case 'modify' : result = '/board/modifyBoard?boardNo=${board.boardNo}&category=${board.categoryCode}'; break;
    			case 'list' : result = '/board/notice'; break;
    		}
    		
    		location.href = result;
    	}
    </script>
  </body>
</html>