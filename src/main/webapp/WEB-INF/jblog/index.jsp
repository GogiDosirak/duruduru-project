<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jblog/layout/header.jsp" %>
	<style>
.product-image2 {
	width: 400px;
	height: 600px;
	object-fit: cover;
}
.post-image {
    width: 400px;
    height: 600px;
    object-fit: cover;
}
</style>
	<section id="billboard" class="overflow-hidden">
		<div class="swiper main-swiper">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-12">
								<div class="banner-item"
									style="background-image: url(images/banner-image1.jpg); background-repeat: no-repeat; background-position: right; height: 682px;">
									<div class="banner-content padding-large">
										<br> <br> <br> <br>
										<class ="display-1 text-uppercasetext-darkpb-2"></class>
										<h2>소중한 친구를 위한 놀이터</h2>

										<class ="display-1 text-uppercasetext-darkpb-2"></class>
										<h1>'두루두루'</h1>
										<h2>펫 커뮤니티</h2>
										<c:if test="${principal == null }">
										<br> <a href="/login"
											class="btn btn-medium btn-arrow position-relative mt-5">
											<span class="text-uppercase">로그인</span> <svg
												class="arrow-right position-absolute" width="18" height="20">
                          <use xlink:href="/login"></use>
                        </svg>
										</a>
										</c:if>
										<c:if test="${principal != null }">
										<br> <a href="/logout"
											class="btn btn-medium btn-arrow position-relative mt-5">
											<span class="text-uppercase">로그아웃</span> <svg
												class="arrow-right position-absolute" width="18" height="20">
                          <use xlink:href="/logout"></use>
                        </svg>
										</a>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="swiper-pagination position-absolute"></div>
	</section>

	<br>
	<br>
	<br>

	<section id="featured-products"
		class="product-store position-relative padding-large">
		<div class="container-fluid">
			<div class="row">
				<div
					class="display-header pb-3 d-flex justify-content-between flex-wrap col-md-12">
					<h2 class="display-2 text-dark text-uppercase">두루두루 쇼핑몰</h2>
					<c:if test="${principal != null }">
					<a href="/mall"
						class="btn btn-medium btn-arrow btn-normal position-relative">
						<span class="text-uppercase">쇼핑하러가기</span> <svg
							class="arrow-right position-absolute" width="18" height="20">
                <use xlink:href="#arrow-right"></use>
              </svg>
					</a>
					</c:if>
										<c:if test="${principal == null }">
											<a href="/login"
						class="btn btn-medium btn-arrow btn-normal position-relative">
						<span class="text-uppercase">쇼핑하러가기</span> <svg
							class="arrow-right position-absolute" width="18" height="20">
                <use xlink:href="#arrow-right"></use>
              </svg>
              </a>
								</c:if>
				</div>
			</div>
			<div class="row">
				<div id="featured-swiper" class="product-swiper col-md-12">
					<div class="swiper">
						<div class="swiper-wrapper">
												<c:forEach var="productItem" items="${productList }">
				<div class="col-md-3 mb-4">
					<div
						class="product-card image-zoom-effect link-effect d-flex flex-wrap">
						<div class="image-holder2">
							<a href="/getProduct/${productItem.productSeq }"><img
								src="${productItem.productFilepath}" alt="product-item"
								class="product-image2 img-fluid"></a>
						</div>
						<div class="cart-concern">
							<h3 class="card-title text-uppercase pt-3 text-primary">
							<c:if test="${principal != null }">
								<a href="/getProduct/${productItem.productSeq}" class="text-primary">${productItem.productName}</a>
								</c:if>
								<c:if test="${principal == null }">
								<a href="/login" class="text-primary">${productItem.productName}</a>
								</c:if>

							</h3>
							<div class="cart-info">
								<a class="pseudo-text-effect" href="#" data-after="ADD TO CART">
									<span>${productItem.productPrice}</span>
								</a>
	
						</div>
					</div>
					</div>
					</div>
					</c:forEach>
					<div class="swiper-pagination text-center mt-5"></div>
				</div>
			</div>
		</div>
	</section>

	<section id="testimonials" class="position-relative">
		<div class="container">
			<div class="row">
				<div class="review-content position-relative">
					<div class="swiper testimonial-swiper">
						<div class="quotation text-center">
							<svg class="quote">
                  <use xlink:href="#quote" />
                </svg>
						</div>
						<div class="swiper-wrapper">
							<div
								class="swiper-slide text-center d-flex justify-content-center">
								<div class="review-item col-md-10">
									<i class="icon icon-review"></i>
									<blockquote class="fs-4">개는요, 사람이 키워요. 개를 존중할 줄 아는 사람이 사람을 존중할줄 알고, 사람을 존중할 줄 아는 사람이 동물을 키울 수 있어요.</blockquote>
									<div class="author-detail">
										<div class="name text-primary text-uppercase pt-2">강형욱</div>
									</div>
								</div>
							</div>
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="swiper-pagination text-center position-absolute"></div>
	</section>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
<section id="latest-blog">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="display-header d-flex flex-wrap justify-content-between pb-3">
                    <h2 class="display-2 text-dark text-uppercase">우리동네 HOT 반려동물</h2>
                    <c:if test="${principal != null }">
                    <a href="/sns" class="btn btn-medium btn-arrow btn-normal position-relative">
                        <span class="text-uppercase">SNS</span> 
                        <svg class="arrow-right position-absolute" width="18" height="20">
                            <use xlink:href="#arrow-right"></use>
                        </svg>
                        </c:if>
                                            <c:if test="${principal == null }">
                    <a href="/login" class="btn btn-medium btn-arrow btn-normal position-relative">
                        <span class="text-uppercase">SNS</span> 
                        <svg class="arrow-right position-absolute" width="18" height="20">
                            <use xlink:href="#arrow-right"></use>
                        </svg>
                        </c:if>
                    </a>
                </div>
            </div>
        </div>
        <div class="row g-3 post-grid">
            <c:forEach var="sns" items="${snsList}">
                <div class="col-lg-4 col-md-6 col-sm-12 mb-5">
                    <div class="card-item">
                        <div class="card border-0 bg-transparent">
                            <div class="card-image">
                                <img src="${sns.filepath}" alt="" class="post-image img-fluid">
                            </div>
                        </div>
                        <div class="card-body p-0 mt-4">
                            <h3 class="card-title text-uppercase">
                                <p>${sns.snsboContent}</p>
                            </h3>
                            <p>${sns.user.nickname}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
	<%@ include file = "./layout/footer.jsp" %>
