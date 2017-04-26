// "use strict";
// !+[1,] ? alert('ie') : alert('other');
// alert(window.navigator.userAgent);
// e.stopPropagation();
_setting = {
	toggleImageSize: false,
	toggleLazyload: false,
	imgUrl : 'images/'
}
$(window).scroll(function() {
	scrollx =  $(document).scrollTop();
	basic.lazyload();
});

$(window).resize(function() {
	basic.lockscale();
});

$(document).ready(function() {
	basic.lockscale();
	basic.animated();
	// _setting.toggleLazyload ? basic.lazyload() : null;
	// _setting.toggleImageSize ? basic.imgsize() : null;
	// basic.grids();
	// basic.deviceRotate();
	// nicescroll();
	// mousewheel();
	if(version()!='Chrome'&&version()!='FireFox'&&version()!='FireFox'&&version()!='Opera'&&!mobile()) {
				$('.notice').slideDown();
			}
	index.banner();
	product.detial();

	uikit.amount();
	uikit.inputs();
	basic.table();
	// $(window).on('load',function(){
	// 	index.videoPlay();
	// })
	index.videoPlay();


	videocenter.slider();
	if($(window).width()<=1024) {
		$('.header .nav .sub').removeClass('animated fadeInDown');
		$('.menubtn').click(function() {
			$('.header .menu').slideToggle();
		});
		$('body').click(function() {
			$('.header .menu').slideUp();
		});
		$('.header').click(function(e) {
			e.stopPropagation();
		});
	}
	$('.product .list .sidebar dt').click(function() {
		$(this).parent('dl').siblings('dl').find('dd').slideUp();
		$(this).siblings('dd').stop().slideDown();
	});
	$('[name="proclass"]').change(function() {
		$value = $(this).val();
		window.location.href=$value;
	});
	if($(window).width()<=640){
		$('.mendian .item').width($(window).width()-30);
	}


	$("#nb_nodeboard_close").on("click", function () {

		if($(this).hasClass("nb-nodeboard-max")){
			$(this).removeClass("nb-nodeboard-max");
            $("#nb_nodeboard_form > ins").show();
            $("#nb_node_contain").show();
		}else{
            $(this).addClass("nb-nodeboard-max");
            $("#nb_nodeboard_form > ins").hide();
            $("#nb_node_contain").hide();
		}
    });

	$("#nb-nodeboard-set-content-js,#nb_nodeboard_set_phone").on("focus", function () {
		$("#nb-nodeboard-tips-js").remove();
    });

	$("#nb_node_contain").on("click", function () {
		var msg = $("#nb-nodeboard-set-content-js").val();
		var name = $("#nb_nodeboard_set_name").val();
		var phone = $("#nb_nodeboard_set_phone").val();
		var email = $("#nb_nodeboard_set_email").val();
		var address = $("#nb_nodeboard_set_address").val();

		if(msg == ""){
			$("#nb-nodeboard-set-content-js").after('<ins class="nb-nodeboard-tips" id="nb-nodeboard-tips-js">不允许为空!</ins>');
			return;
		}

        if(phone == ""){
            $("#nb_nodeboard_set_phone").after('<ins class="nb-nodeboard-tips" id="nb-nodeboard-tips-js">不允许为空!</ins>');
            return;
        }

        ///guest/addGuestBook
		var obj = {};
        obj.msg_info = msg;
        obj.name = name;
        obj.phone = phone;
        obj.email = email;
        obj.address = address;
        $.post(ctx + '/guest/addGuestBook',obj,function(resp) {
        	alert(resp.desc);
        });
    });
});

// 首页新闻
$(function() {
	currentPage = 1;//默认第一页
	$('.index .news .arrows').on('click','.arrow',function() {
		$(this).hasClass('prev') ? currentPage-=1: currentPage+=1;
		$.post(ctx + '/article/news',{'page': currentPage},function($news) {
			// $news = eval('('+result+')');
			if($news!=null) {
				$('.index .news .list li').removeClass().addClass('animated fadeOutUp');
				setTimeout(function() {
					$('.index .news .list li').remove();
					$.each($news,function(k,v) { 
						var html = '<li><a href='+ ctx +'"/article/news-'+v.article_id+'.html"><h2>'+v.title+'</h2><span>'+$.trim(v.article_date)+'</span></a><p>'+v.contents+'</p></li>';
						$('.index .news .list').append(html);
					});
					basic.animated();
				},0.5e3);
			}
				
		});
	});
});

var videocenter = {
	slider : function() {
		if($(window).width()>1280) {
			$step = 5
		}else if($(window).width()>1024){
			$step = 4
		}else if($(window).width()>860) {
			$step = 3
		}else if($(window).width()>480) {
			$step = 2
		}
		if($(window).width()>480) {
			$('.videocenter .content .slider').forslide({
				item: '.item',
				step : $step,
				tagName: 'li',
				clear: true,
				tagClass: 'clear'
			});
			$('.videocenter .content .slider').bxSlider({
				nextSelector: $('.videocenter .arrows .next'),
				prevSelector: $('.videocenter .arrows .prev'),
				prevText: '<i class="iconfont">&#xe6b0;</i>',
				nextText: '<i class="iconfont">&#xe60a;</i>',
				pager: false
			});
		}
	}

}

var uikit = {
	inputs: function() {
		$('input').each(function() {
			if($(this).attr('max')) {
				$(this).keyup(function() {
					$max = parseInt($(this).attr('max'));
					if(parseInt($(this).val())>$max) {
						$(this).val($max);
					}
				});
			}
			if($(this).attr('min')) {
				$(this).keyup(function() {
					$min = parseInt($(this).attr('min'));
					if(parseInt($(this).val())<$min) {
						$(this).val($min);
					}
				});
			}
		});
	},
	amount : function() {
		$('.uikit_amount a').click(function() {
			$this = $(this);
			$max = parseInt($this.parent('.uikit_amount').attr('max'));
			$min = parseInt($this.parent('.uikit_amount').attr('min'));
			$val = parseInt($(this).siblings('input').val());
			if($this.hasClass('add')) {
				$val+=1;
				$val>$max ? $val = $max : null;
			}else{
				$val-=1;
				$val<$min ? $val = $min : null;
			}
			$(this).siblings('input').val($val);
		});
	}
}
var product = {
	detial: function() {
		$productMainSlider = $('.product .detial .slider').bxSlider({
			auto:false,
			controls: false,
			pager: false
		});
		$('.product .detial .main .preview').on('click','li',function() {
			$eq = $(this).index();
			$productMainSlider.goToSlide($eq);
		});
	}
}

var index = {
	banner: function() {
		$('.index .banner .slider').find('li').each(function(i){
			i+=1
			$(this).attr('class','style'+i);
		});
		$slider = $('.index .banner .slider');
		$sliderf = $slider.bxSlider({
			auto: true,
			pause: 5e3,
			mode: 'fade',
			nextSelector: $('.index .banner .arrows .next'),
			prevSelector: $('.index .banner .arrows .prev'),
			prevText: '<i class="iconfont">&#xe61c;</i>',
			nextText: '<i class="iconfont">&#xe61d;</i>',
			onSliderLoad: function(c) {
				$('.index .banner .bx-pager').find('.bx-pager-item').each(function() {
					$(this).find('a').addClass('bold');
					parseInt($(this).find('a').text())<10?$(this).find('a').text('0'+$(this).find('a').text()) : null;
				});
				$slider.find('li:not(.bx-clone)').eq(c).addClass('active');
				$slider.find('li:not(.bx-clone)').eq(c).find('.animated').show();
			},
			onSlideBefore: function(e,o,n) {
				$slider.find('li:not(.bx-clone)').removeClass('active').eq(n).addClass('active');
				$slider.find('li:not(.bx-clone)').eq(n).find('.animated').show();
				$slider.find('li:not(.bx-clone)').eq(o).find('.animated').hide();
				// if($(window).width() > 768) {
				// 	$('.index .banner').append('<canvas id="temp" style="position: absolute; top: 0; left: 0; z-index: 99;"></canvas>');
				// 	canvas  = document.getElementById("temp");
				// 	ctx = canvas.getContext('2d');
				// 	cw = ctx.canvas.width = $('.index .banner').width();  ch =ctx.canvas.height = $('.index .banner').height();
				// 	// e10718  b59835
				// 	color = ["#6ec05a","#b2e7f7","#ffa844","#ca161f","#c94251"];
				// 	ctx.beginPath();
				// 	// 随机一个位置   
				// 	rx = parseInt(Math.random()*cw); ry = parseInt(Math.random()*ch);
				// 	n = 0;
				// 	$r = cw - rx > rx ? cw - rx : rx ;
				// 	color = color[o];
				// 	$timer  = setInterval(function(){
				// 		if(n>=1) {
				// 			clearInterval($timer);
				// 			$('.index .banner canvas').fadeOut(500,function() {
				// 				$('.index .banner canvas').remove();
				// 			});
				// 		}else{
				// 			n+= 0.02;
				// 			_r = $r * n * 1.5;
				// 			ctx.arc( rx , ry , _r , 0 , 2 * Math.PI );
				// 			ctx.fillStyle = color;
				// 			ctx.fill();
				// 		}
				// 	},10);
				// }
			},
			onSlideAfter: function(e,o,n) {
				$sliderf.startAuto();
				// setTimeout(function() {
				// 	$('.index .banner canvas').remove();
				// },500);
			}
		});
		
	},
	videoPlay:function(){
		_w = $(window).width();
		if( _w > 768 ) {
			_h =  _w * 840 / 1920;
			if(_h > $(window).height() ) {
				_h = $(window).height();
				$('.indexvideo').css({ 'background-size': '100% auto' });
			}
			_videoScale = 1080 / 1920;
			_videoMargin = (_h - _w * _videoScale)/2;
			$('.indexvideo').css({
				height: _h
			});
			$('.indexvideo video').css({
				marginTop: _videoMargin
			});
		}
		if( $('.indexvideo').size()>0 ) {

			$('.indexvideo video')[0].volume = 0;
			// $('.indexvideo video')[0].playbackRate = 0.5;
			$('.indexvideo video')[0].play();
			_refresh = true;
			// videointeract();
			$(window).scroll(function() {
				_refresh = false;
				// videointeract();
			});

			$('.indexvideo').addClass('pause');

			$('.indexvideo').click(function() {
				if(typeof(_timer)!='undefined') {
					clearInterval(_timer);
				}
				console.log('play');
				if($(this).hasClass('pause')) {
					$(this).find('.playbtn').addClass('out').fadeOut();
					
					$(this).removeClass('pause').addClass('play');
					i = 0;
					_timer = setInterval(function() {
						i+=0.1;
						i > 1 ? clearInterval(_timer) : $('.indexvideo video')[0].volume = i;
					},200);
				}else{
					$(this).find('.playbtn').removeClass('out').fadeIn();
					$(this).removeClass('play').addClass('pause');
					i = 1;
					_timer = setInterval(function() {
						i-=0.1;
						i < 0 ? clearInterval(_timer) : $('.indexvideo video')[0].volume = i;
					},150);
				}
			});

		}
	}
}
function hint($msg,$time,$type) {
	$time = $time*1e3;
	$('.hintmsg.temp').remove();
	$('body').append('<div class="hintmsg"><div class="msg fadeInUp animated"><i class="iconfont">&#xe605;</i><span>'+$msg+'</span></div></div>')
	setTimeout(function() {
		$('.hintmsg').addClass('fadeOutUp animated temp');
	},$time);
}
$.fn.flow = function($option) {
	var $setting = {
		cloum: 4,
		item: '.item'
	}
	$.extend($setting,$option);
	$this = this;
	_arr = [];
	$this.find($setting.item).each(function(){
		_arr.push($(this).html());
	});
	$this.empty();
	for( i = 0 ; i < $setting.cloum; i  ++ ) {
		$this.append('<div class="cloum cloum'+i+'"></div>');
	}
	$.each(_arr,function(k,item) {
		_cloumHeight = [];
		$this.find('.cloum').each(function() {
			_cloumHeight.push($(this).outerHeight());
		});
		$eq = _cloumHeight.indexOf(Math.min.apply(null,_cloumHeight));
		$this.find('.cloum').eq($eq).append(item);
	});
}
/*按比例尺寸*/
$.fn.ratio = function($option) {
	var $setting = {
		scale : 3/4,
		overflow: false
	}
	$.extend($setting,$option);
	var $obj = this;
	$width = parseInt($obj.width());
	$height = $width * $setting.scale;
	if($setting.overflow) {
		$overflow = 'visible'
	}else{
		$overflow = 'hidden'
	}
	$obj.css({
		'height': $height,
		'overflow': $overflow
	});
}
/*DOM分割*/
$.fn.forslide = function(_option) {
	_temp = ''; _html = '';
	var _setting = {
		item : '.item',
		step : 4,
		tagName: 'li',
		tagClass: '',
		clear: false,
		attr: '',
		callback: function() {}
	}
	$.extend(_setting,_option);
	o = this;
	_size = o.find(_setting.item).size();
	if(_setting.clear){ _clear='<div class="cl"></div>'; }else{ _clear=''; }
	if(_size>_setting.step){
		if(_size % _setting.step == 0){
			for(_i=0;_i<_size;_i++){
				_temp += o.find( _setting.item+':eq('+_i+')').prop('outerHTML');
				if((_i+1)% _setting.step == 0) {
					_html += '<'+_setting.tagName+' '+_setting.attr+' class="'+_setting.tagClass+'">' + _temp + _clear +  '</'+_setting.tagName+'>' ;
					_temp = '';
				}
			}
		}else{
			_page = Math.floor(_size/_setting.step);
			for(_i=0; _i<_size; _i++ ){
				_temp += o.find( _setting.item+':eq('+_i+')').prop('outerHTML');
				if((_i+1)%_setting.step==0&&_i+1<=_page*_setting.step){
					_html += '<'+_setting.tagName+' '+_setting.attr+' class="'+_setting.tagClass+'">' + _temp + _clear +  '</'+_setting.tagName+'>' ;
					_temp = '';
				}
			}
			_html += '<'+_setting.tagName+' '+_setting.attr+' class="'+_setting.tagClass+'">' + _temp  + _clear +  '</'+_setting.tagName+'>' ;
		}
	}else{
		_html += '<'+_setting.tagName+' '+_setting.attr+' class="'+_setting.tagClass+'">' + o.html() +  '</'+_setting.tagName+'>' ;
	}
	o.empty().append(_html);
	_setting.callback();
}
var basic = {
	table: function() {
		$('table').each(function() {
			if($(this).attr('responseTable')) {
				_attr = $(this).get(0).attributes;
				if($(this).attr('condition')) {
					condition = eval($(this).attr('condition')) ? true : false;
				}else{
					condition = true;
				}
				_class = $(this).attr('class');
				_class ? null : _class = 'normal';
				if($(this).find('tr').eq(0).find('th').size()>1) {
					side = 'shu';
					isTable = true;
				}else if($(this).find('tr').eq(0).find('th').size()==1) {
					side = 'heng';
					isTable = true;
				}else{
					isTable = false;
				}

				if(condition) {
					if(isTable&&side=='shu') {
						_ths = [];
						$(this).find('tr').eq(0).find('th').each(function() {
							_ths.push($(this).html());
						});
						_trs = [];
						$(this).find('tr').each(function(i) {
							_tds = [];
							if(i!=0) {
								$(this).find('td').each(function() {
									_tds.push($(this).html());
								});
								_trs.push(_tds);
							}
						});
						// 生成html
						$table = $('<div class="table shuTable '+_class+'" />');
						$tr = $('<div class="tr tr0" />');
						$.each(_ths,function(k,v) {
							$th = $('<div class="th th'+k+'">').append($('<span>'+v+'</span>'));
							$tr.append($th);
						});
						$table.append($tr);
						$.each(_trs,function(row,rowdata) {
							row++;
							$tr = $('<div class="tr tr'+row+'" />');
							$.each(rowdata,function(k,v) {
								$td = $('<div class="td td'+k+'"/>').append($('<i class="key">'+_ths[k]+'</i>')).append($('<span>'+v+'</span>'));
								$tr.append($td);
							});
							$table.append($tr);
						});
						$.each(_attr,function(k,v) {
							$table.attr(v.nodeName,v.value);
						});
						$table.addClass('table hengTable');
						$(this).replaceWith($table);
					}else if(isTable&&side=='heng') {
						_ths = [];
						_this = $(this);
						_trs = [];
						$(this).find('tr').each(function(i) {
							_trs[i] = [];
							_ths.push($(this).find('th').html());
							$(this).find('td').each(function() {
								_trs[i].push($(this).html());
							});
						});
						console.dir(_trs);
						//生成html
						$table = $('<div/>');
						$column = $('<div class="column column0"></div>');
						$.each(_ths,function(k,v) {
							columnNum = k+1;
							$column.append($('<div class="th th'+columnNum+'"><span>'+v+'</span></div>'));
						});
						$table.append($column);
						$.each(_trs[0],function(k) {
							console.log(k);
							columnNum = k+1;
							$column = $('<div class="column column'+columnNum+'"></div>');
							$.each(_trs,function(row,data) {
								$column.append($('<div class="td td'+row+'"><i class="key">'+_ths[row]+'</i><span>'+data[k]+'</span></div>'));
							});
							$table.append($column);
						});
						$.each(_attr,function(k,v) {
							$table.attr(v.nodeName,v.value);
						});
						$table.addClass('table shuTable');
						$(this).replaceWith($table);
					}
				}
			}
		});
	},
	lockscale: function() {
		$('[lockscale]').each(function() {
			scale = eval($(this).attr('lockscale'));
			$(this).css({
				'min-height': $(this).width() * scale
			});
		});
	},
	lazyload: function() {
		$('[lazyload]').each(function() {
			if(!$(this).attr('src')) {
				$(this).css({"background":"url("+_setting.imgUrl+"bx_loader.gif) no-repeat center center"});
			}
			if($(this).offset().top - $(window).height() <= $(window).scrollTop() ) {
				if(!$(this).attr('src')) {
					$(this).attr("src",$(this).attr("lazyload"));
					if(_setting.toggleImageSize) {
						var img_url = $(this).attr('src');
						obj = $(this);
						var img = new Image();
						img.src = img_url;
						var check = function(){
							if(img.width>0 || img.height>0) {
								obj.attr('data-width',img.width);
								obj.attr('data-height',img.height);
								clearInterval(set);
							}
						};
					 	var set = setInterval(check,40);
					}
				}
			}
			$(this).load(function() {
				$(this).css({"background": "none"});
			});
		});
	},
	imgsize: function() {
		imgAmount = $('img[src]').size();
		renderid = 0;
		loadnext = function(obj) {
			var img_url = obj.attr('src');
			var img = new Image();
			img.src = img_url;
			var check = function(){
				if(img.width>0 || img.height>0) {
					obj.attr('data-width',img.width);
					obj.attr('data-height',img.height);
					clearInterval(set);
					renderid++;
					renderid < imgAmount ? loadnext($('img').eq(renderid)): null;	
				}
			};
		 	var set = setInterval(check,40);
		}
		loadnext($('img').eq(renderid));
	},
	grids: function() {
		$css = [];
		$('.row').each(function() {
			mds = '.md1,.md2,.md3,.md4,.md5,.md6,.md7,.md8,.md9,.md10,.md11,.md12,.dv';
			per = 100 / $(this).find('.dv').size();
			$(this).find('.dv').css({ width: per +'%'});
			$(this).find(mds).each(function() {
				$html = $(this).html();
				$margin = $(this).css('margin');
				$(this).addClass('mp0');
				$(this).html('<div class="item">'+$html+'</div>');

				if($margin!='0px') {
					/*获取父级的所有class*/
					$thisClass = '.' + $(this).attr('class').replace(/\s+/g,".");
					$parent = $(this).parents('[class]');
					$parentClass= [];
					if($parent.attr('class')) {
						$parentClass.push($parent.attr('class').replace(/\s+/g,"."));
					}
					for( i = 0 ; i < 5 ; i ++ ) {
						$parent = $parent.parents('[class]');
						if($parent.attr('class')) {
							$parentClass.push($parent.attr('class').replace(/\s+/g,"."));
						}
					}
					$parentClasses = '';
					for( i = $parentClass.length-1 ; i > 0 ; i-- ) {
						$parentClasses += '.' + $parentClass[i] + ' ';
					}
					$thisClassPath = $tcp = $parentClasses + $thisClass ; 
					$thisCss = $thisClassPath + ' .item {margin: '+$margin+'}';
					$css.push($thisCss);
				}

			});
		});
		/*插入css到head*/
		$ncss = [];
		for(var i=0;i<$css.length;i++) {
		　　var items=$css[i];
		　　//判断元素是否存在于new_arr中，如果不存在则插入到new_arr的最后
		　　if($.inArray(items,$ncss)==-1) {
				$ncss.push(items);
		　　}
		}
		$css = '';
		for( i = 0; i < $ncss.length ; i++ ) {
			$css += $ncss[i];
		}
		$('<style>'+$css+'</style>').insertBefore('head');
	},
	deviceRotate: function() {
		if(mobile()) {
			window.addEventListener("onorientationchange" in window ? "orientationchange" : "resize", function() {
				if (window.orientation === 180 || window.orientation === 0) { 
					$('body').addClass('vertical');
				} 
				if (window.orientation === 90 || window.orientation === -90 ){ 
					$('body').addClass('horizontal');
				}  
			}, false); 
		}
	},
	animated: function() {
		$('[breakas]').each(function() {
			_temp = [];
			_text = [];
			_html = '';
			typeof($(this).attr('tagClass'))!='undefined' ? _class=' class="'+$(this).attr('tagClass')+'" ' : _class= '';
			$tagName = $(this).attr('breakas');
			$text = $(this).text();
			_temp = $text.split('#');
			$.each(_temp,function(k,v) {
				_text[k] = v.split(" ");
			});
			$.each(_text,function(k,line) {
				$.each(line,function(a,word) {
					_html += '<span '+_class+'>'+word+'</span> ';
				});
				_html+='<br />';
			});
			$(this).html(_html);
		});
		$('[firstactive]').each(function() {
			$tar = $(this).attr('firstactive');
			$(this).find($($tar)).eq(0).addClass('active');
		});
		$('[prefix]').each(function() {
			$prefix = $(this).attr('prefix');
			if($(this).attr('child')) {
				$child = $(this).attr('child');
				$(this).find($child).each(function(_i) {
					_i+=1;
					$(this).addClass($prefix).addClass($prefix+_i);
				});
			}else{
				$(this).each(function(_i) {
					_i+=1;
					$(this).addClass($prefix).addClass($prefix+_i);
				});
			}
		});
		$('[split]').each(function() {
			_temp = [];
			_html = '';
			$tar = $(this).attr('split');
			$text = $(this).text();
			for(i=0;i<$text.length;i++) {
				_temp.push($text.substring(i,i+1));
			}
			for(i=0;i<_temp.length;i++) {
				if(_temp[i]=="#") {
					_html += '<br />';
				}else {
					_html += '<'+$tar+'>'+_temp[i]+'</'+$tar+'>';
				}
			}
			$(this).html(_html);
		});
		$('[goto]').each(function() {
			$(this).on('click',function() {
				$tar = $(this).attr('goto');
				$("html,body").animate({scrollTop:$($tar).offset().top},1000);
			});
		});
		$('[vcenter]').each(function() {
			tar = $(this).attr('vcenter');
			_html = $(this).find(tar).prop('outerHTML');
			$(this).html('<table width="100%" height="100%"></table>');
			$(this).find('table').append('<td valign="middle">'+_html+'</td>');
		});
		$('[absovcenter]').each(function() {
			$target = $(this).attr('absovcenter');
			$position = $(this).css('position');
			if($position!='absolute'&&$position!='relative'&&$position!='fixed') {
				$(this).css({
					position: 'relative'
				});
			}
			$targetHeight = $(this).find($target).outerHeight();
			$(this).find($target).css({
				position: 'absolute',
				top: '50%',
				marginTop: -$targetHeight / 2
			});
		});
		$('[imgvcenter]').each(function() {
			tar = $(this).attr('imgvcenter');
			outheight =$(this).height();
			$(this).find(tar).load(function() {
				inheight = $(this).outerHeight();
				$(this).css({
					'margin-top': (outheight-inheight)/2
				});
			});
		});
		$('[eachdelay]').each(function() {
			delaytarget=$(this).attr('eachdelay');
			delaytime=$(this).attr('delaytime')/1000;
			if(typeof($(this).attr('delaystart'))!='undefined') {
				delay =  parseInt($(this).attr('delaystart')) / 1000;
			}else{
				delay = 0;
			}
			$(this).find(delaytarget).each(function() {
				$(this).css({
					'animation-delay': delay + 's',
					'-webkit-animation-delay': delay + 's',
					'-o-animation-delay': delay + 's',
					'-moz-animation-delay': delay + 's'
				});
				delay+=delaytime;
			});
		});
		$('[hover]').each(function() {
			$(this).mouseenter(function() {
				animate = $(this).attr('hover');
				if($(this).attr("effect")){
					effectchild = $(this).attr("effect");
					$(this).find(effectchild).addClass(animate+" animated");
				}else{
					$(this).addClass(animate+" animated");
				}
			});
			$(this).mouseleave(function() {
				animate = $(this).attr('hover');
				if($(this).attr("effect")){
					effectchild = $(this).attr("effect");
					$(this).find(effectchild).removeClass(animate+" animated");
				}else{
					$(this).removeClass(animate+" animated");
				}
			});
		});
		$('[animate]').each(function() {
			animate = $(this).attr('animate');
			if(animate.indexOf(',')>=0){
				arr = animate.split(',');
			}else{
				arr = ["fadeIn","zoomIn","fadeInUp","fadeInRight","fadeInDown","fadeInLeft"];
			}
			if($(this).attr('effect')){
				if($(this).attr('animateClass')) {
					if(animate=="random"||animate.indexOf(',')>=0) {
						$(this).find($(this).attr('effect')).each(function() {
							random = Math.floor(Math.random()*(arr.length));
							random = arr[random];
							$(this).addClass(random+' '+$(this).attr('animateClass'));
						});
					}else{
						$(this).find($(this).attr('effect')).addClass(animate+' '+$(this).attr('animateClass'));
					}
				}else{
					if(animate=="random"||animate.indexOf(',')>=0) {
						$(this).find($(this).attr('effect')).each(function() {
							random = Math.floor(Math.random()*(arr.length));
							random = arr[random];
							$(this).addClass(random+' animated');
						});
					}else{
						$(this).find($(this).attr('effect')).addClass(animate+' animated');
					}
				}
			}else{
				if($(this).attr('animateClass')) {
					$(this).addClass(animate+' '+$(this).attr('animateClass'));
				}else{
					$(this).addClass(animate+' animated');
				}
			}
		});
		$('[reach]').each(function() {
			if($(document).scrollTop()+$(window).height()>=$(this).offset().top){
				animate = $(this).attr('reach');
				if(animate.indexOf(',')>=0){
					arr = animate.split(',');
				}else{
					arr = ["fadeIn","zoomIn","fadeInUp","fadeInRight","fadeInDown","fadeInLeft"];
				}
				if($(this).attr("effect")){
					effectchild = $(this).attr("effect");
					$(this).find(effectchild).each(function() {
						if(animate=="random"||animate.indexOf(',')>=0) {
							random = Math.floor(Math.random()*(arr.length));
							random = arr[random];
							$(this).addClass(random+" animated");
						}else{
							$(this).addClass(animate+" animated");
						}
					});
					$(this).find(effectchild).css({
						'visibility': 'visible'
					});
				}else{
					$(this).addClass(animate+" animated");
					$(this).css({
						'visibility': 'visible'
					});
				}
				// $(this).removeAttr('reach');
			}
		});
		$(window).scroll(function() {
			topval = $(document).scrollTop();
			$('[reach]').each(function() {
				if(topval>$(this).offset().top-$(window).height()){
					animate = $(this).attr('reach');
					if($(this).attr("effect")){
						effectchild = $(this).attr("effect");
						$(this).find(effectchild).addClass(animate+" animated");
						$(this).find(effectchild).css({
							'visibility': 'visible'
						});
					}else{
						$(this).addClass(animate+" animated");
						$(this).css({
							'visibility': 'visible'
						});
					}
				}else{
					animate = $(this).attr('reach');
					if($(this).attr("effect")){
						effectchild = $(this).attr("effect");
						$(this).find(effectchild).removeClass(animate+" animated");
						$(this).find(effectchild).css({
							'visibility': 'hidden'
						});
					}else{
						$(this).removeClass(animate+" animated");
						$(this).css({
							'visibility': 'hidden'
						});
					}
				}
			});
		});
	}
}
function dir(e) { console.dir(e); }
function log(e) { console.log(e); }

function version() {
	var explorer = window.navigator.userAgent ;
	if (explorer.indexOf("MSIE") >= 0||explorer.indexOf("Trident")>0 ) {
		if(explorer.indexOf("MSIE 5")>0||explorer.indexOf("MSIE 6")>0||explorer.indexOf("MSIE 7")>0||explorer.indexOf("MSIE 8")>0) {
			return 'LowerIEVersion';
		}else{
			return 'EdgeOrTrident';
		}
	}
	else if (explorer.indexOf("Maxthon") >= 0) {return 'Maxthon';}
	else if (explorer.indexOf("Firefox") >= 0) {return 'FireFox';}
	else if(explorer.indexOf("Chrome") >= 0){ return 'Chrome';}
	else if(explorer.indexOf("Opera") >= 0){ return 'Opera';}
	else if(explorer.indexOf("Safari") >= 0){ return 'Safari';}
}
function mobile() {
	var userAgentInfo = navigator.userAgent;
	var Agents = ["Android", "iPhone","SymbianOS", "Windows Phone","iPad", "iPod"];
	var flag = false;
	for (var v = 0; v < Agents.length; v++) {
		if (userAgentInfo.indexOf(Agents[v]) > 0) {
			flag = true;
			break;
		}
	}
	// var isAndroid = userAgentInfo.indexOf('Android') > -1;
	// var isiOS = !!userAgentInfo.match(/(\(i[^;]+;( U;)? CPU.+Mac OS X)|renrenche/); 
	return flag;
}
function mousewheel() {
	$('body').append(repeat('<br />',200));
	$('body').mousewheel(function(event, delta) {
		log(delta);
	});
}
function nicescroll() {
	$('body').niceScroll({
		cursorcolor:"none",
		background: 'none',
		cursorwidth: '5',
		cursorborder:"none",
		cursorborderradius:"0",
		cursoropacitymin: '0.5',
		cursoropacitymax: '1',
		horizrailenabled: false,
		smoothscroll: false
	});
}
function banscroll() {
	if($('body').hasClass('banscroll')) {
		$('body').getNiceScroll(0).locked = true;
	}else{
		$('body').getNiceScroll(0).locked = false;
	}
}
function limit(text,length,overflow) {
	if(!overflow) { overflow = '...'; }
	if(!length) { length = 10; }
	return text.substr(0,length)+overflow;
}
function repeat(text,x) {
	var $temp=[];
	x|=1;
	for(var i=0;i<=x;$temp[i++]='');
	return $temp.join(text);
}
var $template = {
	render: function(tpl,data) {
		var temp=[];
		 for(var i=0;i<data.length;i++){
			temp.push( $template.htmlTemplate(tpl,data[i]));
		}
		return  temp.join("");
	},
	htmlTemplate: function(template, data, allowEmpty, chats) {
		var regExp;
		chats = chats || ['\\$\\{', '\\}'];
		regExp = [chats[0], '([_\\w]+[\\w\\d_]?)', chats[1]].join('');
		regExp = new RegExp(regExp, 'g');
		return template.replace(regExp,function (s, s1) {
			if (data[s1] != null && data[s1] != undefined) {
				return data[s1];
			} else {
				return allowEmpty ? '' : s;
			}
		});
	}
}
var $cookie = {
	set: function(c_name,value,expiredays) {
		var exdate=new Date()
		exdate.setDate(exdate.getDate()+expiredays)
		document.cookie=c_name+ "=" +escape(value)+
		((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
	},
	get: function(c_name) {
		if (document.cookie.length>0) {
			c_start=document.cookie.indexOf(c_name + "=")
			if (c_start!=-1) { 
				c_start=c_start + c_name.length+1 
				c_end=document.cookie.indexOf(";",c_start)
				if (c_end==-1) {c_end=document.cookie.length}
				return unescape(document.cookie.substring(c_start,c_end))
			} 
		}
		return false;
	}
}

var $format = {
	mp: function($val) {
		re = /^[1][3-8][0-9]{9}$/;
		return $val.match(re) ? true : false;
	},
	id: function($val) {
		re = /^([0-9]){7,18}(x|X)?$/;
		return $val.match(re) ? true : false;
	},
	mail: function($val) {
		re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		return $val.match(re) ? true : false;
	},
	url: function($val) {
		re = /^http[s]*:\/\/([\w-]+\.)+[\w-]+(\/[\w-.\/?%&=]*)?$/;
		return $val.match(re) ? true : false;
	}
}


function playvideo($url) {
	$video = $('#videocontainer');
	$video.attr('src',$url);
	$video.parent('.video').siblings('.cover').fadeOut();
	$video[0].play();
}

function renderlist() {
			$year = $('.select select[name=year]').val();
			$month = $('.select select[name=month]').val();
			$('.news .list .item').removeClass().addClass('item fadeOutUp animated');
			$.post(ctx + '/article/news',{"year":$year,"month":$month},function(data) {
				// data = eval('('+result+')');
				if(data.length>0) {
					setTimeout(function(){
						$('.news .list dl').empty();
						$.each(data,function(k,item) {
							_temp = ''
							+'<div class="item">'
								+'<a href="'+ctx+'/article/news-'+item.article_id+'.html">'
									+'<div class="con">'
										+'<div class="maxsize">'
											+'<div class="face" style="background-image: url('+item.article_photo+');"></div>'
											+'<div class="text">'
												+'<h1>'+item.title+'</h1>'
												+'<h2>'+item.article_date+'</h2>'
												+'<p>'+item.contents+'</p>'
											+'</div>'
										+'</div>'
									+'</div>'
								+'</a>'
							+'</div>'
							+'';
							$('.news .list dl').append(_temp);
						});
						$('.news .list dl').forslide({
							item: '.item',
							step: 5,  // 每页新闻数量
							tagName: 'dd',
							attr: 'eachdelay=".item" delaytime="150" effect=".item" animate="fadeInUp"'
						});
						basic.animated();
					},.5e3);
				}else{
					hint('该日期下面没有新闻',1.5);  // 错误提示，提示维持显示时间
				}
			});
		}


function refreshCode(obj){
	obj.src=ctx + "/validatecode?r=" + Math.random();
}


