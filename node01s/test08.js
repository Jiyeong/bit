/*
요청 파라미터의 값 꺼내기
- 클라이언트가 보낸 데이터(요청 파라미터)
- 
 */
var http = require('http');
//1. URL 모듈을 가져온다
var url = require('url');

http.createServer(function handler(req, res) {
	// 클라이언트가 보낸 name 파라미터 값을 변수에 저장하라!
	//2. URL 정보를 분석한다.
	var urlMap = url.parse(req.url, true);
	
	//3. 쿼리 객체에서 파라미터 값을 추출한다.
	var v1 = parseInt(urlMap.query.v1, 10);
	var v2 = parseInt(urlMap.query.v2, 10);
	var op = urlMap.query.op;
	
    res.writeHead(200, 'ok', 
    		{'Content-Type': 'text/html;charset=UTF-8'});
    res.write('<html><head><title>test08</title></head>');
    res.write('<body>');
   
    switch(op){
    case 'plus':
    	res.write(v1 + ' + ' + v2 + ' = ' + (v1 + v2));
    	break;
    case 'minus':
    	res.write(v1 + ' - ' + v2 + ' = ' + (v1 - v2));
    	break;
    case 'multiple':
    	res.write(v1 + ' * ' + v2 + ' = ' + (v1 * v2));
    	break;
    case 'divide':
    	res.write(v1 + ' / ' + v2 + ' = ' + (v1 / v2));
    	break;
    default:
    	res.write('해당 연산자가 없습니다!');
    }
    res.write('</body></html>');
    res.end();
	console.log('okoko');
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');












































