"use strict"; 

// 목표 : 라이브러리화5_0929
// val() 함수에 읽기 기능 추가
// 삭제, 변경 버튼의 리스너 추가
// bit() 함수 변경 => querySeletorAll()을 사용하여 처리.

// 목표 : 라이브러리화4
// val() 조미료 추가
// css() 조미료 추가
// 생닭 가공 => bit() if 문 참조


// 목표 : 라이브러리화3
// return this의 활용

// 목표 : 라이브러리화2
// 기존의 엘리먼트에 나만의 조미료를 뿌리자!

// 목표 : 라이르버리화1
//

// 목표: 리팩토링 => 반복되는 코드를 함수로 추출하여 정리한다.
// extract method => changeState() 생성 
// 엘리먼트 찾는 코드 => 간단한 함수로 정의 => $()

changeState('create');

var toYYYYMMDD = new Date();

function changeState(state) {
	var stateMap = {
			create: 'none',
			detail: 'none'
	};

	stateMap[state] = '';

		$('.detail').css('display', stateMap.detail);
		$('.create').css('display', stateMap.create);
}

var $ = bit;


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
	$('#btnCancel').click();
}

var boardList = [];

$('#btnCancel').click(function(event) {
  changeState('create');
});

$('#btnAdd').click(function(event) {
	var board = new Board(
			$('#title').val(),
			$('#content').val(),
			$('#writer').val(),
			$('#password').val());
	
	boardList.push(board);
	
	resetForm();
	
	refreshBoardList();
});

$('#btnDelete').click(function(event){
	var no = $('#no').val();
	boardList.splice(no,1);//지우고
	refreshBoardList();//리프레시
	resetForm();
});

$('#btnChange').click(function(event){
	var no = $('#no').val();
	var board = boardList[no];
	board.title = $('#title').val();
	board.content = $('#content').val();
	//boardList[no] = board; // 이거 아님. 주소 저장이므로 이렇게 할 필요 없음.
	refreshBoardList();
});


function refreshBoardList() {
	var boardTable = $('#boardTable');
	
	$('.dataRow').remove();
	  
	for (var i in boardList) {
		$('<tr>')
			.appendTo(boardTable) //부모
			.attr('class', 'dataRow') //
			.append($('<td>').html(i))
			.append($('<td>')
					.append($('<a>') //td는 a태그를 자식으로 가짐
							.attr('bno', new String(i))
							.attr('href', '#')
							.click(loadBoardDetail)//리스너등록
							.html(boardList[i].title)))
			.append($('<td>').html(boardList[i].writer))				
			.append($('<td>').html($.toYYYYMMDD(boardList[i].date)))
			.append($('<td>').html(boardList[i].count));
			
	}
}

function loadBoardDetail(event) {
	event.preventDefault();
	
	changeState('detail');
	
	var board = boardList[$(this).attr('bno')];
	$('#no').val($(this).attr('bno'));
	$('#title').val(board.title);
	$('#content').val(board.content);
	$('#writer').val(board.writer);
	$('#date').val($.toYYYYMMDD(board.date));
}

function toYYYYMMDD(date) {
	return date.getFullYear() + '-' +
	  (date.getMonth() + 1) + '-' +
	  date.getDate();
}