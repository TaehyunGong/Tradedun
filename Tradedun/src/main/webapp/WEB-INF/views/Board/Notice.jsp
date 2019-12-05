<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 공지사항</title>
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
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Blog <i class="ion-ios-arrow-forward"></i></span></p>
            <h1 class="mb-3 bread">공지 사항</h1>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section">
      <div class="container">
        <div class="justify-content-center">
        	
        	<table id="example" class="display nowrap" style="width:100%">
        		<thead>
        			<tr>
        				<th>글 번호</th>
        				<th>분류</th>
        				<th>제목</th>
        				<th>작성 일</th>
        			</tr>
        		</thead>
        		<tbody>
        			<c:forEach var="board" items="${boardList}">
        				<tr>
        					<td>${board.boardNo}</td>
        					<td>${board.categoryCode}</td>
        					<td>${board.title}</td>
        					<td>${board.createDT}</td>
        				</tr>
        			</c:forEach>
        		</tbody>
        	</table>
          
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

	<c:import  url="/footer" />
    <script>
    
		$(document).ready(function() {
			$('#example').DataTable();
		});
    </script>
  </body>
</html>