<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jblog/layout/header.jsp"%>
<style>
    .image-container {
        text-align: center;
    }

    .image-container img {
        max-width: 500px; /* 최대 너비 500px */
        max-height: 500px; /* 최대 높이 500px */
        width: auto;
        height: auto;
    }
</style>
<div class="container mt-3">
    <h2>두루두루 SNS</h2>
    <body class="text-center">
        <!-- 각 이미지에 적용 -->
        <div class="container d-flex align-items-center justify-content-center" style="min-height: 100vh;">
            <div class="text-center">
                <div class="image-container">
                    <img src="images/post-item3.jpg" alt="Banner Image" class="img-fluid">
                </div>
                <br>
                <div class="text-container">
                    <h3>우리 코코 너무 귀엽죠</h3>
                    <p>#귀여워 #비글코코</p>
                    <p>작성자: yyjjmm2003</p>
                </div>
            </div>
        </div>
        <hr>

        <!-- 다른 이미지들도 동일하게 처리 -->
        <div class="container d-flex align-items-center justify-content-center" style="min-height: 100vh;">
            <div class="text-center">
                <div class="image-container mx-auto">
                    <img src="images/post-item1.jpg" alt="Banner Image" class="img-fluid">
                </div>
                <br>
                <div class="text-container">
                    <h3>봉투에 든 코코</h3>
                    <p>#말티즈 #봉투 #코코</p>
                    <p>작성자: syj2024</p>
                </div>
            </div>
        </div>
        <hr>

        <div class="container d-flex align-items-center justify-content-center" style="min-height: 100vh;">
            <div class="text-center">
                <div class="image-container mx-auto">
                    <img src="images/post-item2.jpg" alt="Banner Image" class="img-fluid">
                </div>
                <br>
                <div class="text-container">
                    <h3>뚱냥이 핑크</h3>
                    <p>#핑크 #뚱냥이 </p>
                    <p>작성자: psh2002</p>
                </div>
            </div>
        </div>
        <hr>
    </body>
</html>
<%@ include file="/WEB-INF/jblog/layout/footer.jsp"%>