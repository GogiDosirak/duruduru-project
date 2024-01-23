<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>

<div class="container mt-3">
    <h2>두루두루 쇼핑몰</h2>
<style>
    .product-image {
          width: 300px;
        height: 300px;
        object-fit: cover;
    }
</style>
    <div class="container-fluid mt-3">
        <section id="featured-products" class="product-store position-relative padding-large">
            <div class="container-fluid">
                <div class="row">
                    <div id="featured-swiper" class="product-swiper col-md-12">
                        <div class="swiper">
                            <div class="swiper-wrapper">
                                <c:forEach var="productItem" items="${productList}">
                                    <div class="swiper-slide">
                                        <div class="product-card image-zoom-effect link-effect d-flex flex-wrap">
                                            <div class="image-holder">
                                                <img src="${productItem.filepath }" alt="product-item" class="product-image img-fluid">
                                            </div>
                                            <div class="cart-concern">
                                                <h3 class="card-title text-uppercase pt-3 text-primary">
                                                    <a href="single-product.html" class="text-primary">${productItem.product_name}</a>
                                                </h3>
                                                <div class="cart-info">
                                                    <a class="pseudo-text-effect" href="#" data-after="ADD TO CART">
                                                        <span>${productItem.product_price}</span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <c:if test="${not empty principal and principal.role eq 'ADMIN'}">
        <div class="container mt-3">
            <div class="row justify-content-end">
                <div class="col-auto">
                    <button type="button" onclick="location.href='/insertProduct'" id="btn-insertProduct" class="btn btn-danger">상품 등록하기</button>
                 
                </div>
            </div>
        </div>
    </c:if>
</div>

<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>