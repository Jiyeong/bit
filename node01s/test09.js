/*
GET 요청과 POST 요청 구분하기
- request.method 프로퍼티의 값을 확인하라!
- 
 */
var http = require('http');
var url = require('url');

http.createServer(function handler(req, res) {
	res.writeHead(200, 'ok', 
			{'Content-Type': 'text/html;charset=UTF-8'});
	res.write('<html><head><title>test09</title></head>');
	res.write('<body>');

	if (req.method == 'GET') { 
		var urlMap = url.parse(req.url, true);

		var v1 = parseInt(urlMap.query.v1, 10);
		var v2 = parseInt(urlMap.query.v2, 10);
		var op = urlMap.query.op;

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
	} else {
		res.write(req.method + ' 요청을 지원하지 않습니다!');
	}
	
	res.write('</body></html>');
	res.end();
	
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');












































