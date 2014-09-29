"use strict"; 

//= window.bit = function(value){ 아래와 같은 말.
function bit(value) {
	var elements = null; //원래 생닭
	if (value instanceof Element){ //엘리먼트에 대해 초기화된 것이냐?
		elements = [value]; //엘리먼트면?
	/*} else if (value.charAt(0) == '#') { // 아이디일 경우,
		element = document.getElementById(value.substring(1));*/
	} else if (value.charAt(0) == '<') { // 태그일 경우,
	  elements =  [document.createElement(value.replace(/<|>/g, ''))];	
	} else {
		elements = document.querySelectorAll(value);// 한개의 엘리먼트 아님 /어차피배열
	}

	elements.text = function(value) {
		for(var i = 0;i < this.length; i++){
		this[i].textContent = value;
		}
		return this;
	};
	
	elements.html = function(value) {
		for(var i = 0;i < this.length; i++){
		this[i].innerHTML = value;
		}
		return this;
	};
	
	elements.append = function(child) {
		for(var i = 0;i < this.length; i++){
		if(child instanceof Element){
		this[i].appendChild(child);
		} else {
			for(var x = 0; x < child.length; x++){
				this[i].appendChild(child[x]);
				}
			}
		}
		return this;
	};
	
	elements.appendTo = function(parent) {
		for(var i = 0;i < this.length; i++){
		if (parent instanceof Element){
			parent.appendChild(this[i]);
		}else {
			parent[0].appendChild(this[i]);
			}
		}
		return this;
	};
	
	elements.attr = function(name, value) {
		if(arguments.length == 2){
			for(var i = 0;i < this.length; i++){
				this[i].setAttribute(name, value);
				}
			return this;
		} else {
			return this[0].getAttribute(name);
		}
	};
	
	elements.click = function(listener) {
		for(var i = 0;i < this.length; i++){
		if(listener){
		this[i].onclick = listener;
		} else {
			var event = new MouseEvent('click', {
			    'view': window,
			    'bubbles': true,
			    'cancelable': true
				});
				this[i].dispatchEvent(event);
			}
		}
		return this;
	}
	
	elements.val = function(value){
		if(arguments.length == 1){
			for(var i = 0;i < this.length; i++){
			this[i].value = value;
			}
			return this;
		} else {
			return this[0].value; //무조건 0번쨰.
		}
	};//jquery value=val 유사하게;
	
	elements.css = function(name, value) {
		if(arguments.length == 2){ //vlaue면 빈문자를 false취급하므로 2개 추가해서 하지 않도록 
			for(var i = 0;i < this.length; i++){
			this[i].style[name] = value;
			}
			return this;
		} else {
			return this[0].style[name];
		}
	};
	
	elements.remove = function(){
		for(var i = 0; i < this.length ; i++){
			this[i].parentElement.removeChild(this[i]);
		}
		return this;
	};
	
	return elements; //가공된 닭
}


var $ = bit;

// 함수도 객체다! 저장소로 사용될 수 있다.
bit.toYYYYMMDD = function(date) { //라이브러리화
	return date.getFullYear() + '-' +
	  (date.getMonth() + 1) + '-' +
	  date.getDate();
};


