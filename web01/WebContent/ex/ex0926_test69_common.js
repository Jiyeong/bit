"use strict";

function bit(value){
	var element = null;
	if (value instanceof Element) {
		element = value;
	} else if(value.charAt(0) == '#') {
		element = document.getElementById(value.substring(1));
	} else if (vlaue.charAt(0) == '<') {
		element = document.createElement(value.replace(/<|>/g,''));		
	}


element.text = function(vlaue) {
	this.textContent = value;
	return this;
};

element.html = function(vlaue) {
	this.innerHTML = value;
	return this;
};

element.append = function(child) {
	this.appendcChild = value;
	return this;
};

element.appendTo = function(parent) {
	parent.appendChild(this);
	return this;
};

element.attr = function(name, vlaue) {
	this.setAttribute = (name, value);
	return this;
};

	element.click = function(listner) {
		if(listener){
			this.onclick = listener;
		} else {
			var event = new MouseEvent('click',{
				'view':window,
				'bubbles':true,
				'cancelable':true
			});
		
			this.dispatchEvent(event);
		}
		return this;
	}

	element.val = function(value) {
		this.value = value;
		return this;
	};

	element.css = function(name, value) {
		this.style[name] = value;
		return this;
	};
return element;
}

var $ = bit;

bit.toYYYMMDD = functon(date) {
	return date.getFullYeae() + '-' +
	(date.getMonth() +1) + '-' +
	date.getDate();
};

