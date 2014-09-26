"use strict";

// 목표 : 라이브러리화?
// 자주 사용할 함수는 별도의 파일에 분리하여 둔다.

// 목표6 : 리팩토링 => 반복되는 코드를 함수로 추출하여 정리한다.
// extract method => changeState
// 엘리먼트 찾는 코드 => 간단한 함수로 정의 => bit()

// 목표5 : 자바스크립트와 HTML 소스 분리

// 목표4 : <a>클릭할 때 해당 게시물을 배열에서 가져와서 폼에 출력한다.
// 태그를 모둠으로 묶는다. => class 속성

// 목표3 : 제목에 링크(<a>태그 추가) 추가
// <a>에 게시물 번호를 속성으로 추가하기

// 목표2 : DOM API를 사용하여 tr태그 추가 및 삭제

// 목표1 : UI컴포넌트에게 이벤트 전달
// dispatchEvent() 소개

changeState('create');

var toYYYYMMDD = new Date(); //test69_common에 있으므로 이게 그것을 덮어씌워진다.

var elements = document.querySelectorAll('.detail');
//detail이라는 모든 라벨이 붙은 모든 태그를 안보이게 한다.
for (var i = 0; i < elements.length; i++) {
  elements[i].style.display = 'none';
}

function changeState(state) {
	var stateMap = {
		create: 'none',
		detail: 'none'
	};
	
	stateMap[state] = '';
	/*
	var createState = 'none';
	var detailState = 'none';
	
	if (state == 'create'){
		createState = '';
	} else if(state == 'detail'){
		detailState = '';
	}
	*/
	 var detailClass = document.querySelectorAll('.detail');
	 var createClass = document.querySelectorAll('.create');
	  
	  for (var i = 0; i < detailClass.length;i++) {
		    detailClass[i].style.display = stateMap.detail;
		  }
		  
		  for (var i = 0; i < createClass.length;i++) {
		      createClass[i].style.display = stateMap.create;
		    }
}

// 게시글을 저장하는 객체 생성자 함수
function Board(title, content, writer, password) {
	this.title = title;
	this.content = content;
	this.writer = writer;
	this.password = password;
	this.date = new Date();
	this.count = 0;
}

function resetForm() {
	  // reset 버튼에게 click 이벤트를 전달
	  // 1) MouseEvent 객체 생성
	  var event = new MouseEvent('click',{
		  'view':window,
		  'bubble':true,
		  'cancelable':true
	  });
	  // 2) reset 버튼에게 이벤트 전달 
    document.getElementById('btnCancel').dispatchEvent(event);
	  /* 
	  //입력폼 초기화
    document.getElementById('title').value = '';
    document.getElementById('content').value = '';
    document.getElementById('writer').value = '';
    document.getElementById('password').value = '';
    document.getElementById('title').focus(); // 초기화 후 포커스 첫칸으로.
    */
}

var boardList = [];
/* ver.1
document.getElementById('btnAdd').onclick = function(event) {
	var title = document.getElementById('title').value;
	var content = document.getElementById('content').value;
	var writer = document.getElementById('write').value;
	var password = document.getElementById('password').value;

	//console.log(title, content, writer, password);	
	boardList.push([title, content, writer, password])
};
 */
 
/*  ver.2
 document.getElementById('btnAdd').onclick = function(event) {
	 var board = {
			 'title': document.getElementById('title').value,
			 'content': document.getElementById('content').value,
			 'writer': document.getElementById('writer').value,
			 'password': document.getElementById('password').value
	 };

	  boardList.push(board);
	};
 */	
 document.getElementById('btnCancel').onclick = function(event) {
	 changesState('create');
	 /*
	  var detailClass = document.querySelectorAll('.detail');
	  var createClass = document.querySelectorAll('.create');
	  
	  for (var i = 0; i < detailClass.length;i++) {
		    detailClass[i].style.display = 'none';
		  }
		  
		  for (var i = 0; i < createClass.length;i++) {
		      createClass[i].style.display = '';
		    }
		 */ 
 }
 
document.getElementById('btnAdd').onclick = function(event) {
	   var board = new Board(
	       document.getElementById('title').value,
	       document.getElementById('content').value,
	       document.getElementById('writer').value,
	       document.getElementById('password').value);

	    boardList.push(board);
	    
	    resetForm();
	    
	    refreshBoardList();
	  };
	
function refreshBoardList() {
	var boardTable = document.getElementById('boardTable');
	//var tbody = boardTable.children[0]; // <tbody>
	var tbody = boardTable.firstElementChild; 
	for(var i = tbody.children.length -1; i > 0; i--) {
		//console.log(tbody.children[i]); // 안만들면 브라우저가 알아서 집어넣어줌
		tbody.removeChild(tbody.children[i]);
	} // 장점 건너뛰며 다닐 수 있다.
	

	var tr = null;
	var td = null;
	var text = null;
	var a
	for(var i in boardList) {
		// 배열을 반복하면서 tr태그 
		tr = document.createElement('tr');
		
		td = document.createElement('td');
		text = document.createTextNode(i);
		td.appendChild(text);
		tr.appendChild(td);
		
		
	  td = document.createElement('td');//td 생성
		a = document.createElement('a');//a생성
	  // 일반 객체가 아니라 엘리먼트에 없던 속성을 추가할 때는
	  // setAttribute()를 사용한다.
		a.setAttribute('bno', new String(i));
		a.href = '#'; //링크 보이려면 이렇게 설정해야해
		a.onclick = loadBoardDetail;//a.onclick = loadBoardDetail();이거아님
		// onclick은 loadBoardDetail 함수를 가리킴(주소)
	  text = document.createTextNode(boardList[i].title);//제목 생성
	  a.appendChild(text);//a에 붙인다.
	  td.appendChild(a);//a를 td에 붙인다.
	  tr.appendChild(td);//td를 tr에 붙인다.
	    
	  td = document.createElement('td');
	  text = document.createTextNode(boardList[i].writer);
	  td.appendChild(text);
	  tr.appendChild(td);

	  // $. = bit. / 없으면 window.
	  td = document.createElement('td');
	  text = document.createTextNode($.toYYYYMMDD(boardList[i].date));
	  td.appendChild(text);
	  tr.appendChild(td);
	  
	  td = document.createElement('td');
	  text = document.createTextNode(boardList[i].count);
	  td.appendChild(text);
	  tr.appendChild(td);
	  
	  
	  tbody.appendChild(tr);
	  
/* 	  text = document.createTextNode();
		console.log(boardList[i].title, 
				boardList[i].content, 
				boardList[i].writer, 
				boardList[i].password, 
				boardList[i].date, 
				boardList[i].count); */
	} // 단점 무조건 차례대로

}	


function loadBoardDetail(event) {//이벤트를 누르면 게시판 상세정보 가져오기
	//원래 하던 기능 하지마!
	event.preventDefault(); // 기억해!!**preventDefault 실무에서 잘 씀.
	
	changeState('detail');
	/*
	var detailClass = document.querySelectorAll('.detail');
	var createClass = document.querySelectorAll('.create');
	
	for (var i = 0; i < detailClass.length;i++) {
		detailClass[i].style.display = '';
	}
	
	for (var i = 0; i < createClass.length;i++) {
	    createClass[i].style.display = 'none';
	  }
	*/
	var board = boardList[this.getAttribute('bno')];
	document.getElementById('no').value = this.getAttribute('bno');
	document.getElementById('title').value = board.title;
	document.getElementById('content').value = board.content;
	document.getElementById('writer').value = board.writer;
	document.getElementById('password').value = board.password;
	//console.log(board);
	//alert(this.getAttribute('bno') + '를 눌렀음'); //this:이벤트가 발생한 객체
}


