<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jblog/layout/header.jsp" %>
    
  
      <br>
      <center>
  <h2>1:1문의 등록</h2>
         
         <div class="col-md-8">
            <div class="mb-3 mt-3">
               <label for="inquiryTitle"></label> <input type="text"
                  class="form-control" id="inquiryTitle" placeholder="제목을 입력하세요"
                  name="inquiryTitle">
            </div>
         </div>

<div class="col-md-8">
    <div class="mb-3">
        <label for="inquiryContent"></label>
        <textarea id="inquiryContent"></textarea>
        <script>
            $(document).ready(function() {
                $("#inquiryContent").summernote({
                    height: 500,
                    placeholder: "내용을 입력하세요"
                }).siblings('.note-editor').find('.note-editable').css('text-align', 'left');
            });
        </script>
    </div>
</div>


      </center>
      <center>
         <button id="btn-insertInquiry" class="btn btn-warning">글등록</button>
      </center>

      <script src="/js/inquiry.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp" %>