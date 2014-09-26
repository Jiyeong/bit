"use strict"; 

//= window.bit = function(value){ 아래와 같은 말.
function bit(value) {
	var element = null; //원래 생닭
	if (value instanceof Element){ //엘리먼트에 대해 초기화된 것이냐?
		element = value; //엘리먼트면?
	}else if (value.charAt(0) == '#') { // 아이디일 경우,
		element = document.getElementById(value.substring(1));
	} else if (value.charAt(0) == '<') { // 태그일 경우,
	  element =  document.createElement(value.replace(/<|>/g, ''));	
	}

	element.text = function(value) {
		this.textContent = value;
		return this;
	};
	
	element.html = function(value) {
		this.innerHTML = value;
		return this;
	};
	
	element.append = function(child) {
		this.appendChild(child);
		return this;
	};
	
	element.appendTo = function(parent) {
		parent.appendChild(this);
		return this;
	};
	
	element.attr = function(name, value) {
		this.setAttribute(name, value);
		return this;
	};
	
	element.click = function(listener) {
		if(listener){
		this.onclick = listener;
		} else {
			var event = new MouseEvent('click', {
			    'view': window,
			    'bubbles': true,
			    'cancelable': true
			});
		
			this.dispatchEvent(event);
		}
		return this;
	}
	
	element.val = function(value){
		this.value =value;
		return this;
	};//jquery value=val 유사하게;
	
	element.css = function(name, value) {
		this.style[name] = value;
		return this;
	}
	
	return element; //가공된 닭
}


var $ = bit;

// 함수도 객체다! 저장소로 사용될 수 있다.
bit.toYYYYMMDD = function(date) { //라이브러리화
	return date.getFullYear() + '-' +
	  (date.getMonth() + 1) + '-' +
	  date.getDate();
};


