<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>

<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
            
    <link
   href="https://fonts.googleapis.com/css2?family=Cinzel:wght@400;500;600;700&family=Poppins:wght@200;300;400;500&display=swap"
   rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
   rel="stylesheet">
    
    <script th:inline="javascript">

        // 채팅방 생성
        function createRoom() {

            let name = $("#crName").val();
            let $crCount = $("#crCount");


            if (name === "") {
                alert("방 이름은 필수입니다")
                return false;
            }
            if ($("#" + name).length > 0) {
                alert("이미 존재하는 방입니다")
                return false;
            }
            if (pwd === "") {
                alert("비밀번호는 필수입니다")
                return false;
            }

            // 최소 방 인원 수는 2
            if($crCount.val() <= 1){
                alert("혼자서는 채팅이 불가능해요ㅠ.ㅠ");
                return false;
            }
            return true;
        }
        
        function enterRoom(){
            let chatCode = $("#chatCode").val();

            $.ajax({
                type : "post",
                url : "/chat/chatCode/"+ chatCode,
                async : false,
                success : function(result){
                    console.log("동작완료")
                    // console.log("확인 : "+chkRoomUserCnt(roomId))

                    if(result){
                        
                            location.href = "/chat/room/"+ chatCode;
                        
                    }else {
                        alert("채팅 정원 초과");
                    }
               
                }
            })
        }


     

    </script>
    <style>
        a {
            text-decoration: none;
        }

        #table {
            margin-top: 40px;
        }

        h2 {
            margin-top: 40px;
        }

        input#crCount {
            width: 160px;
        }

        span.input-group-text.input-password-hide {
            height: 40px;
        }

        span.input-group-text.input-password-show {
            height: 40px;
        }
        body {
   font-family: 'Jua', sans-serif; /* 변경할 폰트 지정 */
	}

    </style>
</head>
<body>
<div class="container">
    <div class="container">
	<center>
        <h1>${principal.nickname} 님의 채팅방</h1>
</center>
        <table class="table table-hover" id="table">
            <tr>
                <th scope="col"><h3>채팅방명</h3></th>

                <th scope="col"><h3>참여 인원</h3></th>
                
                <th scope="col"><h3>등록일</h3></th>
                
            </tr>
         
	<c:forEach var="room" items="${list}">
                <tr>
                    <span class="hidden" id="${room.chatRoom.crName}"></span>
                    <td>
                        <a href="/chat/room/${room.chatRoom.crSeq}">[[${room.chatRoom.crName}]]</a>
                    </td>
                  
                    <td>
                        <span class="badge bg-primary rounded-pill">${room.chatRoom.crCount}</span>
                    </td>
                    
                      <td>
                        <span class="badge bg-primary rounded-pill">${room.chatRoom.crDate}</span>
                    </td>
                </tr>
   </c:forEach>

        </table>
        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#roomModal">방 생성</button>
        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#enterRoomModal">방 입장하기</button>

    </div>
</div>

<div class="modal fade" id="roomModal" tabindex="-1" aria-labelledby="roomModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">채팅방 생성</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="post" action="/chat/createroom" onsubmit="return createRoom()">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="crName" class="col-form-label">방 이름</label>
                        <input type="text" class="form-control" id="crName" name="crName">
                    </div>

                    <div class="mb-3">
                        <label for="crCount" class="col-form-label">채팅방 인원 설정</label>
                        <input type="text" class="form-control" id="crCount" name="crCount">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-primary" value="방 생성하기">
                </div>
            </form>
        </div>
    </div>
</div>


                <div class="modal fade" id="enterRoomModal" tabindex="-1" aria-labelledby="enterRoomModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">채팅방 코드를 입력해주세요</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="chatCode" class="col-form-label">채팅 코드</label>
                    <div class="input-group">
                        <input type="text" name="chatCode" id="chatCode" class="form-control" data-toggle="chatCode">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="enterRoom()">입장하기</button>
                </div>
            </div>
        </div>
    </div>
</div>
   


</body>
</html>