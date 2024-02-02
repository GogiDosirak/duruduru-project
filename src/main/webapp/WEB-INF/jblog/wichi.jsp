<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jblog/layout/header.jsp"%>
<!DOCTYPE html>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/js/addressapi.js"></script>
<script src="http://code.jquery.com/jquery-1.11.0.js"></script>


<div class="form-group">
   <input class="form-control" style="width: 40%; display: inline;"
      placeholder="우편번호" name="zipcode" id="zipcode" type="text"
      readonly="readonly">
   <button type="button" class="btn btn-default" onclick="execPostCode();">
      <i class="fa fa-search"></i> 우편번호 찾기
   </button>
</div>
<div class="form-group">
   <input class="form-control" style="top: 5px;" placeholder="도로명 주소"
      name="address" id="address" type="text" readonly="readonly" />
</div>
<div class="form-group">
   <input class="form-control" placeholder="상세주소" name="addressDetail"
      id="addressDetail" type="text" />
</div>

<script type="text/javascript"
   src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a3243da68975b13824a001ce877b890&libraries=services"></script>
<script>
   var comsido ='';
$(function() {        
    // Geolocation API에 액세스할 수 있는지를 확인
    if (navigator.geolocation) {
        // 위치 정보를 얻기
        navigator.geolocation.getCurrentPosition (function(pos) {
            // 위도와 경도를 얻은 후에 주소로 변환
            var geocoder = new kakao.maps.services.Geocoder();
            var coord = new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude);
            var callback = function(result, status) {
                if (status === kakao.maps.services.Status.OK) {
                   //region_1depth_name 지역까지, address_name 전체 주소
                    console.log('현재 위치 주소: ' + result[0].address.region_1depth_name);
                    comsido = result[0].address.region_1depth_name;
                    
                }
            };
            geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
        });
    } else {
        alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.")
    }
});

</script>

<div class="form-group">
   <button id="btn-wichi">회원가입</button>
</div>
<script src="wichi.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp"%>