<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <title>TradeDun - 공지사항</title>
    <meta charset="utf-8">
  </head>
  <body>
    
	<c:import  url="/header" />
    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/images/nbg_5.png');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-5">
          	<p class="breadcrumbs"><span class="mr-2"><a href="/">메인<i class="ion-ios-arrow-forward"></i></a></span></p>
            <h1 class="mb-3 bread">공지 사항</h1>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section">
      <div class="container">
        <div class="justify-content-center">
        	
        	<table id="boardList" class="display nowrap" style="cursor:pointer" style="width:100%">
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
        					<td>${board.categoryName}</td>
        					<td>${board.title}</td>
        					<td><fmt:formatDate value="${board.createDT}" pattern="yyyy.MM.dd" /></td>
        				</tr>
        			</c:forEach>
        		</tbody>
        	</table>
          
        </div>
        
      </div>
    </section>

	<c:import  url="/footer" />
    <script>
    
		$(document).ready(function() {
			var table = $('#boardList').DataTable({
				"language": {
					"emptyTable": "현재 공지사항이 없습니다."
				},
				"order": [[ 0, "desc" ]],
				"ordering": false,
				"info":     false,
				"lengthChange": false
			});
			
		    $('#boardList tbody').on('click', 'tr', function () {
		        var data = table.row( this ).data();
		        location.href="/board/boardDetail?boardNo="+data[0]+"&categoryCode=B_notice";
		    } );
		});
		
    </script>
  </body>
</html>