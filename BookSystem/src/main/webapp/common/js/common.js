

function isIE(){
	if ((navigator.userAgent.indexOf('MSIE') >= 0) 
	    && (navigator.userAgent.indexOf('Opera') < 0)){
	    return true;
	}else {
		return false;
	}
}
jQuery.fn.placeholder = function(){

	var i = document.createElement('input'),
		placeholdersupport = 'placeholder' in i;
	if(!placeholdersupport){
		var inputs = jQuery(this);
		inputs.each(function(){
			var input = jQuery(this),
				text = input.attr('placeholder'),
				pdl = 0,
				height = input.outerHeight(),
				width = input.outerWidth(),
				placeholder = jQuery('<span class="phTips">'+text+'</span>');
			try{
				pdl = input.css('padding-left').match(/\d*/i)[0] * 1;
			}catch(e){
				pdl = 5;
			}
			placeholder.css({'margin-left': -(width-pdl),'height':height,'line-height':height+"px",'position':'absolute', 'color': "#cecfc9", 'font-size' : "12px"});
			placeholder.click(function(){
				input.focus();
			});
			if(input.val() != ""){
				placeholder.css({display:'none'});
			}else{
				placeholder.css({display:'inline'});
			}
			placeholder.insertAfter(input);
			input.keyup(function(e){
				if(jQuery(this).val() != ""){
					placeholder.css({display:'none'});
				}else{
					placeholder.css({display:'inline'});
				}
			});
		});
	}
	return this;
};
$(function(){
	try {
		if (false == noAutoDealPlaceHolder)
			setTimeout("replaceplaceHolder()", 40);
	} catch (e) {
		setTimeout("replaceplaceHolder()", 40);
	}
});
function replaceplaceHolder(){
$("input[type='text'],input[type='password']").each(function(){
		
		var defaultShow = $(this).attr("placeholder");
		if(defaultShow==null || defaultShow == ""){
			return true;//continue;
		}
		dealPlaceHolder(this,true);
	});
}
function placeholderSupport() {
    return 'placeholder' in document.createElement('input');
}
/**
 * @name placeHolder
 * @class 跨浏览器placeHolder,对于不支持原生placeHolder的浏览器，通过value或插入span元素两种方案模拟
 * @param {Object} obj 要应用placeHolder的表单元素对象
 * @param {Boolean} span 是否采用悬浮的span元素方式来模拟placeHolder，默认值false,默认使用value方式模拟
 */
function dealPlaceHolder(obj, span) {
    if (!obj.getAttribute('placeholder')) return;
    var imitateMode = span === true ? true: false;
    var supportPlaceholder = 'placeholder' in document.createElement('input');
    if (!supportPlaceholder) { //
        var defaultValue = obj.getAttribute('placeholder');
        if (!imitateMode) {
            obj.onfocus = function() { (obj.value == defaultValue) && (obj.value = '');
                obj.style.color = '';
            }
            obj.onblur = function() {
                if (obj.value == defaultValue) {
                    obj.style.color = '';
                } else if (obj.value == '') {
                    obj.value = defaultValue;
                    obj.style.color = '#ACA899';
                }
            }
            obj.onblur();
        } else {
            var placeHolderCont = document.createTextNode(defaultValue);
            var oWrapper = document.createElement('span');
            oWrapper.style.cssText = 'position:absolute;text-align:left; color:#ACA899; display:block; overflow:hidden;';
          //  oWrapper.className = 'wrap-placeholder';
            //position: absolute; text-align:left;
            oWrapper.style.fontFamily = getStyle(obj, 'fontFamily');
       //     oWrapper.style.textAlign = "left";
            oWrapper.style.fontSize = getStyle(obj, 'fontSize');
            oWrapper.style.marginLeft = parseInt(getStyle(obj, 'marginLeft')) ? parseInt(getStyle(obj, 'marginLeft')) + 3 + 'px': 3 + 'px';
            oWrapper.style.marginTop = parseInt(getStyle(obj, 'marginTop')) ? getStyle(obj, 'marginTop') : 1 + 'px';
            oWrapper.style.paddingLeft = getStyle(obj, 'paddingLeft');
            
            oWrapper.style.width = obj.offsetWidth - parseInt(getStyle(obj, 'marginLeft')) + 'px';
          
            oWrapper.style.height = obj.offsetHeight + 'px';
            oWrapper.style.lineHeight = obj.nodeName.toLowerCase() == 'textarea' ? '': obj.offsetHeight + 'px';
            oWrapper.appendChild(placeHolderCont);
            obj.parentNode.insertBefore(oWrapper, obj);
            oWrapper.onclick = function() {
                obj.focus();
            }
            //绑定input或onpropertychange事件
            if (typeof(obj.oninput) == 'object') {
                obj.addEventListener("input", changeHandler, false);
            } else {
                obj.onpropertychange = changeHandler;
            }
            function changeHandler() {
                oWrapper.style.display = obj.value != '' ? 'none': 'inline-block';
            }
            /**
                 * @name getStyle
                 * @class 获取样式
                 * @param {Object} obj 要获取样式的对象
                 * @param {String} styleName 要获取的样式名
                 */
            function getStyle(obj, styleName) {
                var oStyle = null;
                if (obj.currentStyle) oStyle = obj.currentStyle[styleName];
                else if (window.getComputedStyle) oStyle = window.getComputedStyle(obj, null)[styleName];
                return oStyle;
            }
        }
    }
}

/**
 * 动态构造密码框，浏览器不会自动保存密码
 * @param inputParams
 */
function buildPasswordInput(inputParams,name,val){

	if(isIE()){
		inputParams += " type=\"password\"";
	}else{
		inputParams  += " type=\"text\"  onfocus=\"this.type='password'\"";
	}
	if(name){
		inputParams += " name='"+name+"'";
	}
	if(val){
		inputParams += " value='"+val+"'";
	}
	var inputHtml = "<input "+inputParams+"  autocomplete=\"off\" />";
	document.write(inputHtml);
	//如果有默认值，让type变为password
	if(name == undefined){
		return;
	}
	if($("input[name='"+name+"']").val() != "" && $("input[name='"+name+"']").attr("type") != 'password'){
		
	//	window.setTimeout("$(\"input[name='"+name+"']\").focus();",30);
		try{
			$("input[name='"+name+"']")[0].type = 'password';
		}catch(e){}
		
	}
}

/**
 * 自定义alert框
 * @param msg
 */
function alertc(msg,callBack){
	button = "确定";
	title = "提示";
	$.alerts.okButton=button;
	jAlert(msg,title,callBack);
}
/**
 * 自定义confirm框
 * @param msg
 * @param callBackYes
 * @param callBackNo
 */
function confirmc(msg,callBackYes,callBackNo){
	jConfirm(msg, '请确定',function(ret){
					if(ret){ //点击确定
						if(callBackYes)
							callBackYes();
					}else{  //点击取消
						if(callBackNo)
							callBackNo();
					}
					
	});
}
/**
 * 当前页面转换为top页面
 */
function breakout(){   
	if (window.top!=window.self){   
 		window.top.location=window.location;
  	}   
}
/**
 * contains
 * @param elem
 * @returns {Boolean}
 */
Array.prototype.contains = function (elem) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == elem) {
			return true;
		}
	}
	return false;	
}
$.ajaxSetup({
	contentType:"application/x-www-form-urlencoded;charset=utf-8",   
    dataType: "json",
    complete:function(XMLHttpRequest,textStatus){
            var sessionstatus=XMLHttpRequest.getResponseHeader("statusCode");//通过XMLHttpRequest取得响应头，sessionstatus，  
            if(sessionstatus != null && sessionstatus=="301"){ 
                        //如果超时就处理 ，指定要跳转的页面  
                                window.location.reload();   
                        }   
             }   
    }); 