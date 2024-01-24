<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/jblog/layout/header.jsp"%>
	<div class="container mt-3">
   
 <style>
  .form-group {
    display: flex;
    align-items: center; /* 수직 가운데 정렬 */
  }

  h2 {
    margin-right: 1000px; /* 원하는 간격으로 조정 */
  }
</style>
            <div class="form-group">
             <h2>산책인증게시판</h2>  
            <button onclick="location.href='/insertwalkcheckboard'">새글 등록</button> <!-- 자바스크립트로 바꿔주기 -->
            </div>     
		<div class="container-fluid mt-3">
		
				<section id="featured-products" class="product-store position-relative padding-large">
			<div class="row">
				<div id="featured-swiper" class="product-swiper col-md-12">
					<div class="swiper">
						<div class="swiper-wrapper">
						
						<td><div class="swiper-slide">
								<div
									class="product-card image-zoom-effect link-effect d-flex flex-wrap">
									<div class="image-holder">
										<img src="images/고양이낚시대.jpg" alt="product-item"
											class="product-image img-fluid">
									</div>
									<div class="cart-concern">
										<h3 class="card-title text-uppercase pt-3 text-primary">
										
											<a href="single-product.html" class="text-primary">123</a>
										
										</h3>
										<div class="cart-info">
											<a class="pseudo-text-effect" href="#"
												data-after="중고거래게시판"> <span>자세히 보기gggggg</span>
											</a>
										</div>
									</div>
								</div>
							</div></td>
							
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section id="featured-products" class="product-store position-relative padding-large">
		<div class="container-fluid mt-3">
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
											<a href="single-product.html" class="text-primary">얼마 안 쓴 개모차 팔아요</a>
										</h3>
										<div class="cart-info">
											<a class="pseudo-text-effect" href="#"
												data-after="중고거래게시판"><span>자세히 보기</span></a>
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
											<a href="single-product.html" class="text-primary">고양이 낚싯대 무료나눔</a>
										</h3>
										<div class="cart-info">
											<a class="pseudo-text-effect" href="#"
												data-after="중고거래게시판"> <span>자세히 보기</span>
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
											<a href="single-product.html" class="text-primary">편안한 목줄 싸게 가져가세요</a>
										</h3>
										<div class="cart-info">
											<a class="pseudo-text-effect" href="#"
												data-after="중고거래게시판"> <span>자세히 보기</span>
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
											<a href="single-product.html" class="text-primary">캣타워 급처 ㅜㅜ</a>
										</h3>
										<div class="cart-info">
											<a class="pseudo-text-effect" href="#"
												data-after="중고거래게시판"> <span>자세히 보기</span>
											</a>
										</div>
			
									</div>
		
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
		</div>
	</section>

<%@include file = "/WEB-INF/jblog/layout/footer.jsp"%>