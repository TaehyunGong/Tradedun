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
            <h1 class="mb-3 bread">AuctionSetSearch</h1>
          </div>
        </div>
      </div>	
    </section>

    
	<section class="ftco-section ftco-no-pb ftco-no-pt">
	    <div class="container">
	        <div class="row">
	            <div class="col-md-12">
	                <div class="search-wrap-1 ftco-animate mb-5">
	                    <form action="#" class="search-property-1">
	                        <div class="row">
	                            <div class="col-lg align-items-end">
	                                <div class="form-group">
	                                    <label for="#">직군</label>
	                                    <div class="form-field">
	                                        <div class="select-wrap">
	                                            <div class="icon"><span class="ion-ios-arrow-down"></span></div>
	                                            <select name="" id="" class="form-control">
	                                                <option value="">Year Model</option>
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
	                                            <select name="" id="" class="form-control">
	                                                <option value="">$1</option>
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
  </body>
</html>