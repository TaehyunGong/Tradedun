<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="/css/animate.css">
    
    <link rel="stylesheet" href="/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/css/magnific-popup.css">

    <link rel="stylesheet" href="/css/aos.css">

    <link rel="stylesheet" href="/css/ionicons.min.css">

    <link rel="stylesheet" href="/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/css/jquery.timepicker.css">

    <link rel="stylesheet" href="/css/flaticon.css">
    <link rel="stylesheet" href="/css/icomoon.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/jquery-ui.min.css">
    
    <link rel="stylesheet" href="https://cdn.remixicon.com/releases/v2.0.0/remixicon.css">
    <link rel="stylesheet" href="/css/datatables.min.css"/>
    <link rel="stylesheet" href="/css/tradedun.css"/>

	<!-- jQuery Modal -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

	<!-- fontawsome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	
	<!-- Toasts -->
	<link rel="stylesheet" href="/css/toastr.min.css"/>

	<!-- favicon -->
	<link rel="shortcut icon" type="image/png" sizes="16x16" href="/resources/images/common/favicon-16x16.png">
	<link rel="icon" type="image/png" sizes="16x16" href="/resources/images/common/favicon-16x16.png">

 <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
   <div class="container">
     <a class="navbar-brand" href="/">Trade<ruby><span>Dun</span><rt>BETA</rt></ruby></a>
     <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
       <span class="oi oi-menu"></span> Menu
     </button>

     <div class="collapse navbar-collapse" id="ftco-nav">
       <ul class="navbar-nav ml-auto">
         <li class="nav-item active"><a href="/" class="nav-link">메인</a></li>
         <li class="nav-item"><a href="/board/notice" class="nav-link">공지</a></li>
         <li class="nav-item"><a href="/auction/auctionMenu" class="nav-link">컨텐츠</a></li>
         <li class="nav-item"><a href="/auction/rareAvatarSet" class="nav-link">레압 검색</a></li>
         <li class="nav-item"><a href="/auction/avatarShowroom" class="nav-link">쇼룸 검색</a></li>
         <li class="nav-item"><a href="/contact/contactMail" class="nav-link">문의</a></li>
         <c:choose>
         	<c:when test="${empty user.userNo}">
		    	<li class="nav-item"><a href="/login?SNS=kakao" class="nav-link"><img src="/images/kakao_login_btn_small.png" alt="카카오톡 로그인"/></a></li>
         	</c:when>
         	<c:otherwise>
         		<li class="nav-item"><a href="/user/userMenu" class="nav-link">마이페이지</a></li>
         		<li class="nav-item"><a href="/logout" class="nav-link">로그아웃</a></li>
         	</c:otherwise>
         </c:choose>
       </ul>
     </div>
   </div>
 </nav>
 
  <script src="/js/jquery.min.js"></script>
  <script src="/js/jquery-migrate-3.0.1.min.js"></script>
  <script src="/js/popper.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  <script src="/js/jquery.easing.1.3.js"></script>
  <script src="/js/jquery.waypoints.min.js"></script>
  <script src="/js/jquery.stellar.min.js"></script>
  <script src="/js/owl.carousel.min.js"></script>
  <script src="/js/jquery.magnific-popup.min.js"></script>
  <script src="/js/aos.js"></script>
  <script src="/js/jquery.animateNumber.min.js"></script>
  <script src="/js/bootstrap-datepicker.js"></script>
  <script src="/js/jquery.timepicker.min.js"></script>
  <script src="/js/scrollax.min.js"></script>
  <script src="/js/jquery-ui.min.js"></script>
  <script src="/js/datatables.min.js"></script>
  <script src="/js/tradedun.js" ></script>
  
  <!-- jQuery Modal -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
  
  <!-- Toasts -->
  <script src="/js/toastr.min.js" ></script>
  
  <%--메시지 전용 jstl이다. --%>
  ${msg}
