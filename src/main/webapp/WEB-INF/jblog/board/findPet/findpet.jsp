<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>

<div class="container mt-3">
    <h2>산책 인증 게시판</h2>

    <style>
        .product-image {
            width: 300px;
            height: 300px;
            object-fit: cover;
        }
    </style>
    <div class="container-fluid mt-3">
        <div class="row">
            <c:forEach var="wachbo" items="${getwachList}">
                <div class="col-md-3 mb-4">
                    <div class="product-card image-zoom-effect link-effect d-flex flex-wrap">
                        <div class="image-holder">
                            <img src="${wachbo.wach_filepath}" alt="product-item" class="product-image img-fluid">
                        </div>
                        <div class="cart-concern">
                            <h3 class="card-title text-uppercase pt-3 text-primary">
                                <a href="single-product.html" class="text-primary">${wachbo.wachbo_title}</a>
                            </h3>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<br>
<br>
<br>

<center>
  <button type="button" class="btn btn-warning" onclick="location.href='/insertwalkcheckboard'">글등록</button>
</center>

<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>