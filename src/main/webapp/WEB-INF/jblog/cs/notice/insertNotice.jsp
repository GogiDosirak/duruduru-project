<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jblog/layout/header.jsp" %>
    
  
      <br>
      <center>
  <h2>공지사항 등록</h2>
         
         <div class="col-md-8">
            <div class="mb-3 mt-3">
               <label for="noticeTitle"></label> <input type="text"
                  class="form-control" id="noticeTitle" placeholder="제목을 입력하세요"
                  name="noticeTitle">
            </div>
         </div>

<div class="col-md-8">
    <div class="mb-3">
        <label for="noticeContent"></label>
        <textarea id="noticeContent"></textarea>
        <script>
            $(document).ready(function() {
                $("#noticeContent").summernote({
                    height: 500,
                    placeholder: "내용을 입력하세요"
                }).siblings('.note-editor').find('.note-editable').css('text-align', 'left');
            });
        </script>
    </div>
</div>


      </center>
      <center>
         <button id="btn-insertNotice" class="btn btn-warning">글등록</button>
      </center>

      <script src="/js/notice.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp" %>