changeState('container1');

function changeState(state) {
	var stateMap = {
		container1 : 'none',
		container2 : 'none'
	};

	stateMap[state] = '';

	var container1Class = document.querySelectorAll('.container1');
	var container2Class = document.querySelectorAll('.container2');

	for (var i = 0; i < container1Class.length; i++) {
		container1Class[i].style.display = stateMap.container1;
	}

	for (var i = 0; i < container2Class.length; i++) {
		container2Class[i].style.display = stateMap.container2;
	}
}

// 영화 객체
function Movie(title, subtitle, overview, county, running_time, date, director,
		actor, grade, img1) {
	this.title = title;
	this.subtitle = subtitle;
	this.overview = overview;
	this.county = county;
	this.running_time = running_time
	this.date = date;
	this.director = director;
	this.actor = actor;
	this.grade = grade;
	this.img1 = img1;
	this.count = 0;
}

var MovieList = [];

var movie1 = new Movie('비긴 어게인',
		'Begin Again, Can a Song Save Your Life?, 2013', '드라마, 멜로/로맨스, 코미디',
		'미국', '104분', '201408.13 개봉', '키이라 나이틀리(그레타), 마크 러팔로(댄), 애덤 리바인',
		'존 카니', '15세 관람가', 'mp1.jpg');
var movie2 = new Movie('인 투 더 스톰', 'Into the Storm, 2014', '액션, 스릴러', '미국',
		'89분', '2014.08.28 개봉', '리처드 아미티지(게리 모리스), 사라 웨인 콜리스(앨리슨 스톤)',
		'스티븐 쿼일', '12세 관람가', 'mp1.jpg');
var movie3 = new Movie('베리 굿 걸', 'Very Good Girls, 2013', '드라마, 멜로/로맨스', '미국',
		'91분', '2014.09.25 개봉', '다코타 패닝(릴리 버거), 엘리자베스 올슨(제리), 보이드 홀브룩',
		'나오미 포너', '15세 관람가', 'mp1.jpg');
var movie4 = new Movie('두근두근 내인생', '2014', '드라마', '한국', '117분',
		'2014.08.13 개봉', '강동원(대수), 송혜교(미라), 조성목(아름)', '이재용', '12세 관람가', 'mp1.jpg');
var movie5 = new Movie('해적: 바다로 간 산적', '2014', '모험, 액션', '한국', '104분',
		'2014.08.06 개봉', '김남길(장사정), 손예진(여월)', '이석훈', '12세 관람가', 'mp1.jpg');
var movie6 = new Movie('타짜-신의 손', '2014', '드라마', '한국', '147분', '2014.09.03 개봉',
		'최승현(함대길), 신세경(허미나), 곽도원(장동식)', '강형철', '15세 관람가', 'mp1.jpg');

MovieList.push(movie1);
MovieList.push(movie2);
MovieList.push(movie3);
MovieList.push(movie4);
MovieList.push(movie5);
MovieList.push(movie6);

var state; // 현재 위치
// printMovieDetail(state);

document.getElementById('after').onclick = function(event) {
	changeState('container2');
	state++;
	if (state >= 0 && state < MovieList.length) {
		printMovieDetail(state);
	} else {
		alert('마지막 입니다.');
		state--;
	}
};

document.getElementById('before').onclick = function(event) {
	changeState('container2');
	state--;
	if (state >= 0 && state < MovieList.length) {
		printMovieDetail(state);
	} else {
		alert('마지막 입니다.');
		state++;
	}
};

document.getElementById('selectM').onclick = function(event) {
	// 셀렉트
	var target = document.getElementById("selectM");
	state = target.options[target.selectedIndex].value; // value값을 가져오기
	if (state == 10 ) {
		console.log(target.options[target.selectedIndex].value);
		changeState('container1');
	} else { 
		changeState('container2');
		printMovieDetail(state);
	}
	
	changeState('container2');
	changeImg (state);
	printMovieDetail(state);
};

document.getElementById('mp1').onclick = function(event) {
	// 셀렉트
	changeState('container2');
	state = 0;
	changeImg (state);
	printMovieDetail(state);
};

document.getElementById('mp2').onclick = function(event) {
	// 셀렉트
	changeState('container2');
	state = 1;
	changeImg (state);
	printMovieDetail(state);
};

document.getElementById('mp3').onclick = function(event) {
	// 셀렉트
	changeState('container2');
	state = 2;
	changeImg (state);
	printMovieDetail(state);
};

document.getElementById('mp4').onclick = function(event) {
	// 셀렉트
	changeState('container2');
	state = 3;
	changeImg (state);
	printMovieDetail(state);
};

document.getElementById('mp5').onclick = function(event) {
	// 셀렉트
	changeState('container2');
	state = 4;
	changeImg (state);
	printMovieDetail(state);
};

document.getElementById('mp6').onclick = function(event) {
	// 셀렉트
	changeState('container2');
	state = 5;
	changeImg (state);
	printMovieDetail(state);
};

// 영화 정보 출력
function printMovieDetail(state) {
	/*
	 * console.log(MovieList[0].title);
	 */
	var MovieTable = document.getElementById('MovieTable');
	var tbody = MovieTable.firstElementChild; // <tbody>
	//console.log(tbody.children.length);
	for (var i = tbody.children.length - 1; i >= 0; i--) {
		// console.log(tbody.children[i]);
		tbody.removeChild(tbody.children[i]);
	}

	var tr = null;
	var td = null;
	var text = null;

	tr = document.createElement('tr');
	td = document.createElement('td');
	text = document.createTextNode(MovieList[state].title); // 제목 생성
	td.appendChild(text);
	tr.appendChild(td);
	tbody.appendChild(tr);

	tr = document.createElement('tr');
	td = document.createElement('td');
	text = document.createTextNode(MovieList[state].subtitle); // 제목 생성
	td.appendChild(text);
	tr.appendChild(td);
	tbody.appendChild(tr);

	// 개요 | 국가명 | 상영시간
	tr = document.createElement('tr');
	td = document.createElement('td');
	text = document.createTextNode(MovieList[state].overview); // 제목 생성
	td.appendChild(text);
	tr.appendChild(td);

	td = document.createElement('td');
	text = document.createTextNode(MovieList[state].county); // 제목 생성
	td.appendChild(text);
	tr.appendChild(td);

	td = document.createElement('td');
	text = document.createTextNode(MovieList[state].date); // 제목 생성
	td.appendChild(text);
	tr.appendChild(td);

	tbody.appendChild(tr);

	tr = document.createElement('tr');
	td = document.createElement('td');
	text = document.createTextNode(MovieList[state].director); // 제목 생성
	td.appendChild(text);
	tr.appendChild(td);
	tbody.appendChild(tr);

	tr = document.createElement('tr');
	td = document.createElement('td');
	text = document.createTextNode(MovieList[state].actor); // 제목 생성
	td.appendChild(text);
	tr.appendChild(td);
	tbody.appendChild(tr);

	tr = document.createElement('tr');
	td = document.createElement('td');
	text = document.createTextNode(MovieList[state].grade); // 제목 생성
	td.appendChild(text);
	tr.appendChild(td);
	tbody.appendChild(tr);
}

function changeImg (state) {
	document.getElementById('img1').src = MovieList[state].mp1;
	document.getElementById('img2').src = MovieList[state].mp2;
	document.getElementById('img3').src = MovieList[state].mp3;
	document.getElementById('img4').src = MovieList[state].mp4;
	console.log(document.getElementById('img4').src);
}

/*function slideImg () {
	var slide = document.getElementById('slide');
	var tbody = slide.firstElementChild; // <tbody>
	//console.log(tbody.children.length);
	//for (var i = tbody.children.length - 1; i >= 0; i--) {
		// console.log(tbody.children[i]);
		//tbody.removeChild(tbody.children[i]);
	//}

	var li = null;
	var img = null;

	li = document.createElement('li');
	img = document.createElement('img');
	img.src = 'mp1.jpg';	
	li.appendChild(img);	
	tbody.appendChild(li);
	
	li = document.createElement('li');
	img = document.createElement('img');
	img.src = 'mp2.jpg';	
	li.appendChild(img);	
	tbody.appendChild(li);
	
	li = document.createElement('li');
	img = document.createElement('img');
	img.src = 'mp3.jpg';	
	li.appendChild(img);	
	tbody.appendChild(li);
	
	tconsole.log(tbody.children.length);

}*/