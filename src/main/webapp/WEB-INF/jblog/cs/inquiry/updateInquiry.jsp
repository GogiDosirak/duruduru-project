<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jblog/layout/header.jsp" %>
    
  
      <br>
      <center>
  <h2>1:1문의 수정</h2>
         <input type="hidden" id="inquirySeq" name="inquirySeq" value="${getInquiry.inquirySeq }">
         <div class="col-md-8">
            <div class="mb-3 mt-3">
               <label for="inquiryTitle"></label> <input type="text"
                  class="form-control" id="inquiryTitle" 
                  name="inquiryTitle" value="${getInquiry.inquiryTitle }">
            </div>
         </div>

<div class="col-md-8">
    <div class="mb-3">
        <label for="inquiryContent"></label>
        <textarea id="inquiryContent">${getInquiry.inquiryContent }</textarea>
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
         <button id="btn-updateInquiry" class="btn btn-warning">수정</button>
      </center>

      <script src="/js/inquiry.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp" %>