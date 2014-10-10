var http = require('http');

http.createServer(function handler(req, res) {
    res.writeHead(200, 'ok', {'Content-Type': 'text/plain'});
    res.end('Hello World\n');
	console.log('okoko');
}).listen(1337, '192.168.0.32');
console.log('Server running at http://127.0.0.1:1337/');

/*
1. HTTP 프로토콜 - 요청
1) 형식
----------------------------
request-line(엔터)
헤더:값(엔터)
헤더:값(엔터)
...
(엔터)
서버에 보내는 데이터 본문
----------------------------

2) 예제
----------------------------
GET http://192.168.0.32:1337/ HTTP/1.1
Host: 192.168.0.32:1337
Connection: keep-alive
Cache-Control: max-age=0
Accept: text/html,application/xhtml...
User-Agent: Mozilla/5.0 (X11; Linux ...
Accept-Encoding: gzip,deflate,sdch
Accept-Language: ko-KR,ko;q=0.8,en-...

----------------------------
-참고-
헤더의 종류: 일반헤더, 요청헤더, 응답헤더, 본문헤더(entity header)

2. HTTP 프로토콜 - 응답
1) 형식
---------------------------- 
상태 라인(엔터)
헤더:값(엔터)
헤더:값(엔터)
...
(엔터)
브라우저가 출력할 본문 데이터
----------------------------

2) 예제
----------------------------
HTTP/1.1 200 ok
Content-Type: text/plain
Date: Wed, 08 Oct 2014 05:58:49 GMT
Connection: keep-alive
Content-Length: 12

Hello World
----------------------------


*/


























