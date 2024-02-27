'use strict';

// document.write("<script src='jquery-3.6.1.js'></script>")
document.write("<script\n" +
    "  src=\"https://code.jquery.com/jquery-3.6.1.min.js\"\n" +
    "  integrity=\"sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=\"\n" +
    "  crossorigin=\"anonymous\"></script>")



var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var userSeqInput = document.querySelector('#userSeq');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

// crSeq 파라미터 가져오기
var crSeqInput = document.getElementById('crSeq');
const crSeq = crSeqInput.value;
  

function connect(event) {
    username = document.querySelector('#name').value.trim();
	console.log("값 출력하기13");

    // usernamePage 에 hidden 속성 추가해서 가리고
    // chatPage 를 등장시킴
    usernamePage.classList.add('hidden');
    // 입장 알림 포인트 1
    chatPage.classList.remove('hidden');

    // 연결하고자하는 Socket 의 endPoint
    var socket = new SockJS('/ws-stomp');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);


    event.preventDefault();


}

function onConnected() {
    // sub로 구독할 url을 지정
    stompClient.subscribe('/sub/chat/room/' + crSeq, onMessageReceived);

    // 서버에 username 을 가진 유저가 들어왔다는 것을 알림
    // /pub/chat/enterUser 로 메시지를 보냄
    stompClient.send("/pub/chat/enterUser",
        {},
        JSON.stringify({
            "crSeq": crSeq,
            userSeq : userSeqInput.value,
            cuSender: username,
            cuRole: 'ENTER'
        })
        
       
    )

    

}

function disconnected() {

  	stompClient.subscribe('/sub/chat/room/' + crSeq, onMessageReceived);

    // 서버에 username 을 가진 유저가 들어왔다는 것을 알림
    // /pub/chat/enterUser 로 메시지를 보냄
    stompClient.send("/pub/chat/leaveUser",
        {},
        JSON.stringify({
            "crSeq": crSeq,
            userSeq : userSeqInput.value,
            cuSender: username,
            cuRole: 'LEAVE'
        }),
        
         location = '/chat'
    )



}

//저장된 채팅 내역 불러오기
function getChatList() {
    $.ajax({
        type: "GET",
        url: "/chat/chatlist",
        data: {
            "crSeq": crSeq
        },
        success: function (data) {
            // 서버로부터 받은 대화 내역을 스타일시트에 맞게 처리하여 표시
            displayChatList(data);
        }
    })
}

function displayChatList(chatList) {
    chatList.forEach(function(chat) {
        var messageElement = document.createElement('li');

        // 채팅 메시지의 종류에 따라 스타일 적용
        if (chat.cuRole === 'ENTER') {
            messageElement.classList.add('event-message');
        } else if (chat.cuRole === 'LEAVE') {
            messageElement.classList.add('event-message');
        } else {
            messageElement.classList.add('chat-message');

            var avatarElement = document.createElement('i');
            var avatarText = document.createTextNode(chat.cuSender[0]);
            avatarElement.appendChild(avatarText);
            avatarElement.style['background-color'] = getAvatarColor(chat.cuSender);
            messageElement.appendChild(avatarElement);

            var usernameElement = document.createElement('span');
            var usernameText = document.createTextNode(chat.cuSender);
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);
        }

        var contentElement = document.createElement('p');
        if (chat.s3DataUrl != null) {
            var imgElement = document.createElement('img');
            imgElement.setAttribute("src", chat.s3DataUrl);
            imgElement.setAttribute("width", "300");
            imgElement.setAttribute("height", "300");

            var downBtnElement = document.createElement('button');
            downBtnElement.setAttribute("class", "btn fa fa-download");
            downBtnElement.setAttribute("id", "downBtn");
            downBtnElement.setAttribute("name", chat.fileName);
            downBtnElement.setAttribute("onclick", `downloadFile('${chat.fileName}', '${chat.fileDir}')`);

            contentElement.appendChild(imgElement);
            contentElement.appendChild(downBtnElement);
        } else {
            var messageText = document.createTextNode(chat.cuMessage);
            contentElement.appendChild(messageText);
        }

        messageElement.appendChild(contentElement);

        messageArea.appendChild(messageElement);
    });

    messageArea.scrollTop = messageArea.scrollHeight;
}

getChatList();



function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

// 메시지 전송때는 JSON 형식을 메시지를 전달한다.
function sendMessage(event) {
		console.log("값 출력하기111");
    var messageContent = messageInput.value.trim();

    if (messageContent && stompClient) {
        var chatMessage = {
            "crSeq": crSeq,
            userSeq : userSeqInput.value,
            cuSender: username,
            cuMessage: messageInput.value,
            cuRole: 'TALK'
        };
		
		// pub으로 메세지 전달
        stompClient.send("/pub/chat/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

// 메시지를 받을 때도 마찬가지로 JSON 타입으로 받으며,
// 넘어온 JSON 형식의 메시지를 parse 해서 사용한다.
function onMessageReceived(payload) {
    //console.log("payload 들어오냐? :"+payload);
    var chat = JSON.parse(payload.body);
	console.log("값 출력하기1");
    var messageElement = document.createElement('li');

    if (chat.cuRole === 'ENTER') {  // chatType 이 enter 라면 아래 내용
        messageElement.classList.add('event-message');
    } else if (chat.cuRole === 'LEAVE') { // chatType 가 leave 라면 아래 내용
        messageElement.classList.add('event-message');
    } else { // chatType 이 talk 라면 아래 내용
    console.log("값 출력하기2");
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(chat.cuSender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(chat.cuSender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(chat.cuSender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var contentElement = document.createElement('p');

    // 만약 s3DataUrl 의 값이 null 이 아니라면 => chat 내용이 파일 업로드와 관련된 내용이라면
    // img 를 채팅에 보여주는 작업
    if(chat.s3DataUrl != null){
        var imgElement = document.createElement('img');
        imgElement.setAttribute("src", chat.s3DataUrl);
        imgElement.setAttribute("width", "300");
        imgElement.setAttribute("height", "300");

        var downBtnElement = document.createElement('button');
        downBtnElement.setAttribute("class", "btn fa fa-download");
        downBtnElement.setAttribute("id", "downBtn");
        downBtnElement.setAttribute("name", chat.fileName);
        downBtnElement.setAttribute("onclick", `downloadFile('${chat.fileName}', '${chat.fileDir}')`);


        contentElement.appendChild(imgElement);
        contentElement.appendChild(downBtnElement);

    }else{
        // 만약 s3DataUrl 의 값이 null 이라면
        // 이전에 넘어온 채팅 내용 보여주기기
       var messageText = document.createTextNode(chat.cuMessage);
        contentElement.appendChild(messageText);
    }

    messageElement.appendChild(contentElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messagecuSender) {
    var hash = 0;
    for (var i = 0; i < messagecuSender.length; i++) {
        hash = 31 * hash + messagecuSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

        function copyToClipboard() {
     
            // 숨겨진 input 요소에서 텍스트를 가져옵니다.
            var copyInput = document.getElementById("copyInput");
            var textToCopy = copyInput.value;

            // 텍스트를 클립보드에 복사합니다.
            navigator.clipboard.writeText(textToCopy)
                .then(function() {
                    // 복사 완료 메시지를 표시합니다.
                    alert("텍스트가 클립보드에 복사되었습니다: " + copyInput.value);
                })
                .catch(function(error) {
                    // 복사 실패 메시지를 표시합니다.
                    alert("텍스트 복사 중 오류가 발생했습니다: " + error);
                });
        }
        
    

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)

/// 파일 업로드 부분 ////
function uploadFile(){
    var file = $("#file")[0].files[0];
    var formData = new FormData();
    formData.append("file",file);
    formData.append("crSeq", crSeq);

    // ajax 로 multipart/form-data 를 넘겨줄 때는
    //         processData: false,
    //         contentType: false
    // 처럼 설정해주어야 한다.

    // 동작 순서
    // post 로 rest 요청한다.
    // 1. 먼저 upload 로 파일 업로드를 요청한다.
    // 2. upload 가 성공적으로 완료되면 data 에 upload 객체를 받고,
    // 이를 이용해 chatMessage 를 작성한다.
    $.ajax({
        type : 'POST',
        url : '/s3/upload',
        data : formData,
        processData: false,
        contentType: false
    }).done(function (data){
        // console.log("업로드 성공")

        var chatMessage = {
            "crSeq": crSeq,
            cuSender: username,
            message: username+"님의 파일 업로드",
            type: 'TALK',
            s3DataUrl : data.s3DataUrl, // Dataurl
            "fileName": file.name, // 원본 파일 이름
            "fileDir": data.fileDir // 업로드 된 위치
        };

        // 해당 내용을 발신한다.
        stompClient.send("/pub/chat/sendMessage", {}, JSON.stringify(chatMessage));
    }).fail(function (error){
        alert(error);
    })
}

// 파일 다운로드 부분 //
// 버튼을 누르면 downloadFile 메서드가 실행됨
// 다운로드 url 은 /s3/download+원본파일이름
function downloadFile(name, dir){
    // console.log("파일 이름 : "+name);
    // console.log("파일 경로 : " + dir);
    let url = "/s3/download/"+name;

    // get 으로 rest 요청한다.
    $.ajax({
        url: "/s3/download/"+name, // 요청 url 은 download/{name}
        data: {
            "fileDir" : dir // 파일의 경로를 파라미터로 넣는다.
        },
        dataType: 'binary', // 파일 다운로드를 위해서는 binary 타입으로 받아야한다.
        xhrFields: {
            'responseType': 'blob' // 여기도 마찬가지
        },
        success: function(data) {

            var link = document.createElement('a');
            link.href = URL.createObjectURL(data);
            link.download = name;
            link.click();
        }
    });
}