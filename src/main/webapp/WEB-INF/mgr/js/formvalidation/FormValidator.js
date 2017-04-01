/**
 * 抽象出formvalidation
 * FormValidator.js
 * @author sam.pan
 * @createTime 2016-02-22 15:17:12
 */
var FormValidator = function(options){
	/* FormId*/
	var _formId = options.formId;
	/* 初始化事件*/
	var _initFieldFv = options.initFieldFv;
	/* 检验字段*/
	var _fields = options.fields || {};
	/* 成功验证后回调的事件*/
	var _successValidatorFv = options.successValidatorFv;
	/* 所有form成功验证后的事件，一般用于提交*/
	var _successFormFv = options.successFormFv;
	/* Triggered when any field is invalid*/
	var _errFieldFv = options.errFieldFv;
	/* Triggered when any field is valid*/
	var _successFieldFv = options.successFieldFv;
	/* */
	var _excluded = options.excluded;
	
	/* 初始化事件*/
	function __initFieldFv(e, data) {
        var $parent = data.element.parents('.form-group'),
            $icon   = $parent.find('.form-control-feedback[data-fv-icon-for="' + data.field + '"]');

        $icon.on('click.clearing', function() {
            /* 检查字段是否可用 只能校验不通过时才清除哈 */ 
            if ($icon.hasClass('glyphicon-remove')) {
                /*清除字段值*/
                data.fv.resetField(data.element);
            }
        });
    }
	
	function init(){
		$('#'+_formId).on('init.field.fv', function(e, data) {
			/* 默认点击X的时候清除信息*/
			__initFieldFv(e, data);
			if(typeof _initFieldFv == "function"){
				/* 用户自定义方法*/
				_initFieldFv(e, data);
			}
		})
		/*校验事件*/
		.formValidation({
	        framework: 'bootstrap',
	        excluded: _excluded,
	        icon: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        locale: lang,
	        fields: _fields
		})
		/* 成功验证的事件*/
		.on('success.validator.fv', function(e, data) {
			if($.isFunction(_successValidatorFv)){
				_successValidatorFv(e, data);
			}
		})
		.on('err.field.fv', function(e, data) {
			if($.isFunction(_errFieldFv)){
				_errFieldFv(e, data);
			}
		})
		.on('success.field.fv', function(e, data) {
			if($.isFunction(_successFieldFv)){
				_successFieldFv(e, data);
			}
		})
		 /*使用ajax提交数据*/
	    .on('success.form.fv', function(e) {
	    	if($.isFunction(_successFormFv)){
	    		_successFormFv(e);
			}
	    });
	}
	
	/* 默认初始化*/
	init();
};