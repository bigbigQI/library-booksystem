(function($) {
	$.alerts = {
		verticalOffset : 0,
		horizontalOffset : 0,
		repositionOnResize : true,
		overlayOpacity : .30,
		overlayColor : '#FFF',
		draggable : true,
		okButton : '确定',
		cancelButton : '取消',
		dialogClass : null,
		alert : function(message, title, callback) {
			if (title == null)
				title = '警告';
			$.alerts._show(title, message, null, null, null, 'alert', function(
					result) {
				if (callback)
					callback(result);
			});
		},
		confirm : function(message, title, callback) {
			if (title == null)
				title = '确认';
			$.alerts._show(title, message, null, null, null, 'confirm',
					function(result) {
						if (callback)
							callback(result);
					});
		},
		prompt : function(message, title, value, callback) {
			if (title == null)
				title = '请输入';
			$.alerts._show(title, message, value, null, null, 'prompt',
					function(result) {
						if (callback)
							callback(result);
					});
		},
		iframe : function(url, title, width, height) {
			if (title == null)
				title = '提示';
			$.alerts._show(title, url, null, width, height, 'iframe', null);
		},
		choose : function(message, title, width, height, callback) {
			if (title == null)
				title = '提示';
			$.alerts._show(title, message, null, width, height, 'choose',
					function(result) {
						if (callback)
							callback(result);
					});
		},
		html : function(message, title, width, height) {
			if (title == null)
				title = '提示';
			$.alerts._show(title, message, null, width, height, 'html', null);
		},
		_show : function(title, msg, value, width, height, type, callback) {
			$.alerts._hide();
			$.alerts._overlay('show');
			$("BODY")
					.append(
							'<div id="popup_container" class="gSys_msg_box">'
									+ '<div id="popup_title" class="ptitle"><span class="fn-bg rc-l"></span><h4></h4><span class="fn-bg rc-r"></span><a href="javascript:;" class="fn-bg aclose" title="关闭">关闭</a></div>'
									+ '<div id="popup_message_box" class="pbox"><div class="pmsg"><b title="提示:" class="ico"></b><div class="ct" id="popup_message"></div></div></div>'
									+ '<div id="popup_opt_box" class="popt"><div class="opt" id="opt"></div></div>'
									+ '</div>');
			if ($.alerts.dialogClass)
				$("#popup_container").addClass($.alerts.dialogClass);
			if (width == null) {
				width = 460;
			}
			$("#popup_container").css({
				position : 'absolute',
				zIndex : 99999,
				padding : 0,
				margin : 0,
				width : width + 'px'
			});
			if (height) {
				$("#popup_container").css("height", height + 'px');
			}
			$("#popup_title h4").text(title);
			$("#popup_message_box b").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html(
					$("#popup_message").text().replace(/\n/g, '<br />'));
			$.alerts._maintainPosition(true);
			$.alerts._reposition();
			$("#popup_title a").click(function() {
				$.alerts._hide();
			});
			switch (type) {
			case 'alert':
				$("#opt").html(
						'<div id="popup_ok" class="pbtn" tabindex="0"><span>'
								+ $.alerts.okButton + '</span></div>');
				$("#popup_ok").click(function() {
					$.alerts._hide();
					callback(true);
				});
				$("#popup_ok").mouseover(function() {
					$(this).attr("class", "pbtn btn_hover");
				});
				$("#popup_ok").mouseout(function() {
					$(this).attr("class", "pbtn");
				});
				$("#popup_ok").mousedown(function() {
					$(this).attr("class", "pbtn btn_active");
				});
				$("#popup_ok").mouseup(function() {
					$(this).attr("class", "pbtn");
				});
				$("#popup_ok").focus().keypress(function(e) {
					if (e.keyCode == 13 || e.keyCode == 27)
						$("#popup_ok").trigger('click');
				});
				break;
			case 'confirm':
				$("#opt")
						.html(
								'<div id="popup_cancel" class="pbtn" tabindex="0"><span>'
										+ $.alerts.cancelButton
										+ '</span></div><div id="popup_ok" class="pbtn" tabindex="0"><span>'
										+ $.alerts.okButton + '</span></div>');
				$("#popup_ok").click(function() {
					$.alerts._hide();
					if (callback)
						callback(true);
				});
				$("#popup_cancel").click(function() {
					$.alerts._hide();
					if (callback)
						callback(false);
				});
				$("#popup_ok, #popup_cancel").mouseover(function() {
					$(this).attr("class", "pbtn btn_hover");
				});
				$("#popup_ok, #popup_cancel").mouseout(function() {
					$(this).attr("class", "pbtn");
				});
				$("#popup_ok, #popup_cancel").mousedown(function() {
					$(this).attr("class", "pbtn btn_active");
				});
				$("#popup_ok, #popup_cancel").mouseup(function() {
					$(this).attr("class", "pbtn");
				});
				$("#popup_ok").focus();
				$("#popup_ok, #popup_cancel").keypress(function(e) {
					if (e.keyCode == 13)
						$("#popup_ok").trigger('click');
					if (e.keyCode == 27)
						$("#popup_cancel").trigger('click');
				});
				break;
			case 'iframe':
				$("#popup_message_box").remove();
				$("#popup_opt_box").remove();
				$("#popup_container")
						.append(
								"<div id='popup_iframe_box' class='piframe'><div id='loading' class='loading'><img src='./loading.gif'></div></div>");
				$("#loading").css({
					width : width + "px",
					height : height + "px",
					padding : 0,
					margin : 0
				});
				if ($.browser.msie
						&& ($.browser.version == "6.0" || $.browser.version == "7.0")) {
					$("#loading").css("fontSize", height * 0.9 + 'px');
				}
				;
				if ($.browser.msie && $.browser.version == "6.0") {
					$("#popup_title").width((parseInt(width) + 2) + 'px');
				}
				;
				var iframe = document.createElement("iframe");
				iframe.id = "iframe";
				iframe.width = width + "px";
				iframe.height = height + "px";
				iframe.scrolling = "auto";
				iframe.setAttribute("frameborder", "0", 0);
				iframe.src = msg;
				iframe.style.display = "none";
				$("#popup_iframe_box").append(iframe);
				$(iframe).load(function() {
					iframe.style.display = "block";
					$("#loading").hide();
				});
				break;
			case 'prompt':
				$("#popup_message_box b").remove();
				$("#popup_message").attr("class", "cp");
				$("#popup_message")
						.append(
								'<br /><input type="text" class="pinput" size="48" id="popup_prompt" />');
				$("#opt")
						.html(
								'<div id="popup_cancel" class="pbtn" tabindex="0"><span>'
										+ $.alerts.cancelButton
										+ '</span></div><div id="popup_ok" class="pbtn" tabindex="0"><span>'
										+ $.alerts.okButton + '</span></div>');
				$("#popup_ok, #popup_cancel").mouseover(function() {
					$(this).attr("class", "pbtn btn_hover");
				});
				$("#popup_ok, #popup_cancel").mouseout(function() {
					$(this).attr("class", "pbtn");
				});
				$("#popup_ok, #popup_cancel").mousedown(function() {
					$(this).attr("class", "pbtn btn_active");
				});
				$("#popup_ok, #popup_cancel").mouseup(function() {
					$(this).attr("class", "pbtn");
				});
				$("#popup_ok").click(function() {
					var val = $("#popup_prompt").val();
					$.alerts._hide();
					if (callback)
						callback(val);
				});
				$("#popup_cancel").click(function() {
					$.alerts._hide();
					if (callback)
						callback(null);
				});
				$("#popup_prompt, #popup_ok, #popup_cancel").keypress(
						function(e) {
							if (e.keyCode == 13)
								$("#popup_ok").trigger('click');
							if (e.keyCode == 27)
								$("#popup_cancel").trigger('click');
						});
				if (value)
					$("#popup_prompt").val(value);
				$("#popup_prompt").focus().select();
				break;
			case 'choose':
				$("#popup_message_box b").remove();
				$("#popup_message").attr("class", "cp");
				$("#opt")
						.html(
								'<div id="popup_cancel" class="pbtn" tabindex="0"><span>'
										+ $.alerts.cancelButton
										+ '</span></div><div id="popup_ok" class="pbtn" tabindex="0"><span>'
										+ $.alerts.okButton + '</span></div>');
				$("#popup_ok, #popup_cancel").mouseover(function() {
					$(this).attr("class", "pbtn btn_hover");
				});
				$("#popup_ok, #popup_cancel").mouseout(function() {
					$(this).attr("class", "pbtn");
				});
				$("#popup_ok, #popup_cancel").mousedown(function() {
					$(this).attr("class", "pbtn btn_active");
				});
				$("#popup_ok, #popup_cancel").mouseup(function() {
					$(this).attr("class", "pbtn");
				});
				$("#popup_ok").click(function() {
					var val = $("#popup_message");
					$.alerts._hide();
					if (callback)
						callback(val);
				});
				$("#popup_cancel").click(function() {
					$.alerts._hide();
					if (callback)
						callback(null);
				});
				$("#popup_ok, #popup_cancel").keypress(function(e) {
					if (e.keyCode == 13)
						$("#popup_ok").trigger('click');
					if (e.keyCode == 27)
						$("#popup_cancel").trigger('click');
				});
				break;
			case 'html':
				$("#popup_message_box b").remove();
				$("#popup_opt_box").remove();
				break;
			}
			if ($.alerts.draggable) {
				try {
					$("#popup_container").easydrag();
					$("#popup_container").setHandler('popup_title');
					$("#popup_title").css({
						cursor : 'move'
					});
				} catch (e) {
				}
			}
		},
		_hide : function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		_overlay : function(status) {
			switch (status) {
			case 'show':
				$.alerts._overlay('hide');
				$("BODY").append('<div id="popup_overlay"></div>');
				$("#popup_overlay").css({
					position : 'absolute',
					zIndex : 99998,
					top : '0px',
					left : '0px',
					width : '100%',
					height : $(document).height(),
					background : $.alerts.overlayColor,
					opacity : $.alerts.overlayOpacity
				});
				if ($.browser.msie && $.browser.version == "6.0") {
					$("#popup_overlay").width($(window).width());
				}
				;
				break;
			case 'hide':
				$("#popup_overlay").remove();
				break;
			}
		},
		_reposition : function() {
			var top = (($(window).height() / 2) - ($("#popup_container")
					.outerHeight() / 2))
					+ $.alerts.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container")
					.outerWidth() / 2))
					+ $.alerts.horizontalOffset;
			if (top < 0)
				top = 0;
			if (left < 0)
				left = 0;
			top = top + $(window).scrollTop();
			$("#popup_container").css({
				top : top + 'px',
				left : left + 'px'
			});
			$("#popup_overlay").height($(document).height());
		},
		_maintainPosition : function(status) {
			if ($.alerts.repositionOnResize) {
				switch (status) {
				case true:
					$(window).bind('resize', $.alerts._reposition);
					break;
				case false:
					$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
	}
	jAlert = function(message, title, callback) {
		$.alerts.alert(message, title, callback);
	}
	jConfirm = function(message, title, callback) {
		$.alerts.confirm(message, title, callback);
	};
	jPrompt = function(message, title, value, callback) {
		$.alerts.prompt(message, title, value, callback);
	};
	jIframe = function(url, title, width, height) {
		$.alerts.iframe(url, title, width, height);
	};
	jChoose = function(html, title, width, height, callback) {
		$.alerts.choose(html, title, width, height, callback);
	};
	jHtml = function(html, title, width, height) {
		$.alerts.html(html, title, width, height);
	};
})(jQuery);