<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/jblog/layout/header.jsp" %>
    
  
      <br>
      <center>
  <h2>공지사항 수정</h2>
         <input type="hidden" id="noticeSeq" name="noticeSeq" value="${getNotice.noticeSeq }">
         <div class="col-md-8">
            <div class="mb-3 mt-3">
               <label for="noticeTitle"></label> <input type="text"
                  class="form-control" id="noticeTitle" 
                  name="noticeTitle" value="${getNotice.noticeTitle }">
            </div>
         </div>

<div class="col-md-8">
    <div class="mb-3">
        <label for="noticeContent"></label>
        <textarea id="noticeContent" >${getNotice.noticeContent}</textarea>
        <script>
            $(document).ready(function() {
                $("#noticeContent").summernote({
                    height: 500,
                }).siblings('.note-editor').find('.note-editable').css('text-align', 'left');
            });
        </script>
    </div>
</div>


      </center>
      <center>
         <button id="btn-updateNotice" class="btn btn-warning">수정</button>
      </center>

      <script src="/js/notice.js"></script>
<%@include file="/WEB-INF/jblog/layout/footer.jsp" %>