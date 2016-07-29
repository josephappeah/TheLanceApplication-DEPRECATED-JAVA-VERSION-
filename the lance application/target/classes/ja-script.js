/*
	author: joseph appeah
	date: 07/08/2016
*/

/*
This Script Requires jquery.
*/

/*Attempt to append all required libraries to head*/
window.onload = function(){
	try{
		//get jquery
		var jquery = document.createElement('script');
		jquery.src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js';
		document.getElementsByTagName('head')[0].appendChild(jquery);
		
		//get animate.css
		$('head').append('<link href="https://github.com/daneden/animate.css/blob/master/animate.css" />');
	}catch(e){
		console.log('Error: Failed to append required libraries.');
	}
} 
 
 /*Font Resize on Window Resize*/
 function resizeFontById(div_id){
	 try{
		$(document).ready(function(){
			var initialFontSize = getFontSize(div_id);
			resizeFont(initialFontSize ,div_id);
			$(window).resize(function(){
				resizeFont(initialFontSize ,div_id);
			});
			function getFontSize(id){        
				var initialFontSize = parseInt($("#" + id).css("font-size".replace("px","")));
				return initialFontSize;
			}
			function resizeFont(fontSize,id){
				var currentWindowSize = parseInt(Math.sqrt(Math.pow($(window).height(),2) + Math.pow($(window).width(),2)));
				var newFontSize = (fontSize*(currentWindowSize/1000));
				if(newFontSize >fontSize){newFontSize = newFontSize - (newFontSize-fontSize);}
				$("#" + id).css("font-size",newFontSize);
			}
		});
	 }catch(e){
		 console.log('Error: resizeFontById requires jquery.');
	 }
}

/*Perform action when div is reached on scrolling*/
function actionWhenReached(div_id, redo, undo){
	try{
		$(document).ready(function(){
		$(window).scroll(function(){
			var divPosition = $('#' + div_id).offset().top,
				divHeight = $('#' + div_id).outerHeight(),
				windowHeight = $(window).height(),
				currentHeight = $(this).scrollTop();
			if (currentHeight > (  (windowHeight)) -( divPosition + divHeight )){
				redo();
			}

			if(currentHeight < (  (windowHeight))-( divPosition + divHeight ) ){
				undo();
			}
		});
	});
	}catch(e){
		console.log('Error: actionWhenReached requires jquery');
	}
}


/*Perform action when div is reached on scrolling*/
function actionOnOffset(offset, redo, undo){
	try{
		$(document).ready(function(){
		$(window).scroll(function(){
				var currentHeight = $(this).scrollTop();
			if (currentHeight > (offset)){
				redo();
			}

			if(currentHeight < ( offset) ){
				undo();
			}
		});
	});
	}catch(e){
		console.log('Error: actionWhenReached requires jquery');
	}
}

/*Animate by Id. Using animations from animate.css*/
function animateById(id, animation){
	try{
		$("#" + id).addClass('animated ' + animation);
			window.setTimeout(function(){	
		$("#" + id).removeClass('animated '+ animation);
		},2000);
	}catch(e){
		console.log('Error: animateById requires animate.css');
	}
}
