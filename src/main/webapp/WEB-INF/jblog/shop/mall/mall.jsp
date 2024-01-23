<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "/WEB-INF/jblog/layout/header.jsp"%>
	<div class="container mt-3">
  <h2>두루두루 쇼핑몰</h2>         
		<div class="container-fluid mt-3">
		
				<section id="featured-products"
		class="product-store position-relative padding-large">
		<div class="container-fluid">
			<div class="row">
				<div
					class="display-header pb-3 d-flex justify-content-between flex-wrap col-md-12">
					</a>
				</div>
			</div>
			<div class="row">
				<div id="featured-swiper" class="product-swiper col-md-12">
					<div class="swiper">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<div
									class="product-card image-zoom-effect link-effect d-flex flex-wrap">
									<div class="image-holder">
										<img src="images/product-item1.jpg" alt="product-item"
											class="product-image img-fluid">
									</div>
									<div class="cart-concern">
										<h3 class="card-title text-uppercase pt-3 text-primary">
											<a href="single-product.html" class="text-primary">강아지 사료</a>
										</h3>
										<div class="cart-info">
											<a class="pseudo-text-effect" href="#"
												data-after="ADD TO CART"><span>29000</span></a>
										</div>
									</div>
								</div>
							</div>
							<div class="swiper-slide">
								<div
									class="product-card image-zoom-effect link-effect d-flex flex-wrap">
									<div class="image-holder">
										<img src="images/product-item2.jpg" alt="product-item"
											class="product-image img-fluid">
									</div>
									<div class="cart-concern">
										<h3 class="card-title text-uppercase pt-3 text-primary">
											<a href="single-product.html" class="text-primary">고양이 사료</a>
										</h3>
										<div class="cart-info">
											<a class="pseudo-text-effect" href="#"
												data-after="ADD TO CART"> <span>32000</span>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div class="swiper-slide">
								<div
									class="product-card image-zoom-effect link-effect d-flex flex-wrap">
									<div class="image-holder">
										<img src="images/product-item3.jpg" alt="product-item"
											class="product-image img-fluid">
									</div>
									<div class="cart-concern">
										<h3 class="card-title text-uppercase pt-3 text-primary">
											<a href="single-product.html" class="text-primary">강아지 담요</a>
										</h3>
										<div class="cart-info">
											<a class="pseudo-text-effect" href="#"
												data-after="ADD TO CART"> <span>15000</span>
											</a>
										</div>
									</div>
								</div>
							</div>
							<div class="swiper-slide">
								<div
									class="product-card image-zoom-effect link-effect d-flex flex-wrap">
									<div class="image-holder">
										<img src="images/product-item4.jpg" alt="product-item"
											class="product-image img-fluid">
									</div>
									<div class="cart-concern">
										<h3 class="card-title text-uppercase pt-3 text-primary">
											<a href="single-product.html" class="text-primary">사르르 쮸르</a>
										</h3>
										<div class="cart-info">
											<a class="pseudo-text-effect" href="#"
												data-after="ADD TO CART"> <span>8000</span>
											</a>
										</div>
									</div>
								</div>
							</div>
							</section>

<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>