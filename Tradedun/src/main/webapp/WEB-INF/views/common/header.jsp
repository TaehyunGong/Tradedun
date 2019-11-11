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
    
    <link href="https://cdn.remixicon.com/releases/v2.0.0/remixicon.css" rel="stylesheet">

 <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
   <div class="container">
     <a class="navbar-brand" href="index.html">Auto<span>road</span></a>
     <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
       <span class="oi oi-menu"></span> Menu
     </button>

     <div class="collapse navbar-collapse" id="ftco-nav">
       <ul class="navbar-nav ml-auto">
         <li class="nav-item active"><a href="/" class="nav-link">Home</a></li>
         <li class="nav-item"><a href="/AuctionMenu" class="nav-link">Auction</a></li>
         <li class="nav-item"><a href="/?page=about" class="nav-link">About</a></li>
         <li class="nav-item"><a href="/?page=pricing" class="nav-link">Pricing</a></li>
         <li class="nav-item"><a href="/?page=car" class="nav-link">Our Car</a></li>
         <li class="nav-item"><a href="/?page=blog" class="nav-link">Blog</a></li>
         <li class="nav-item"><a href="/?page=contact" class="nav-link">Contact</a></li>
         <c:choose>
         	<c:when test="${empty user.userNo}">
		    	<li class="nav-item"><a href="/login?SNS=kakao" class="nav-link"><img src="/images/kakao_login_btn_small.png" alt="카카오톡 로그인"/></a></li>
         	</c:when>
         	<c:otherwise>
         		<li class="nav-item"><a href="/logout" class="nav-link"><img src="/images/kakao_login_btn_small" alt="로그아웃"/></a></li>
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

