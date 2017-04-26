/**
 * function:
 * OrderInfoAdd.js
 * @author sam.pan
 * @createTime 2017-04-11 11:04:09
 */
var OrderInfo = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _order_id = options.order_id;
	var _controller_name = "com.huateng.xhcp.web.product.OrderInfoController";
	var _param_type = "com.huateng.xhcp.model.product.OrderInfo";
	
	/*查询按钮事件初始化*/
	this.initBtn = function(){
		/*清空*/
		$("#btn_reset").on("click", function(){
			Form.reset("conditionForm");
		});
	};
	
	/* 字段校验*/
	function __fieldValidator(){
		var _field = {};

		_field.order_id = Validator.validate(false, {max:25});
		_field.amount_money = Validator.validate(false, {max:11});
		_field.buyer_account_id = Validator.validate(false, {max:10});
		_field.buyer_user_name = Validator.validate(false, {max:50});
		_field.seller_account_id = Validator.validate(false, {max:10});
		_field.seller_user_name = Validator.validate(false, {max:50});
		_field.currency_unit = Validator.validate(false, {max:15});
		_field.buyer_name = Validator.validate(false, {max:20});
		_field.buyer_phone = Validator.validate(false, {max:20});
		_field.buyer_mobile = Validator.validate(false, {max:20});
		_field.send_type = Validator.validate(false, {max:50});
		_field.send_no = Validator.validate(false, {max:50});
		_field.send_time = Validator.validate(false, {max:14});
		_field.freight = Validator.validate(false, {max:11});
		_field.invoice_need = Validator.validate(false, {max:1});
		_field.invoice_title = Validator.validate(false, {max:100});
		_field.pay_type = Validator.validate(false, {max:1});
		_field.buyer_pay_time = Validator.validate(false, {max:14});
		_field.trad_time = Validator.validate(false, {max:14});
		_field.trad_finish_time = Validator.validate(false, {max:14});
		_field.update_time = Validator.validate(false, {max:14});
		_field.seller_deliver_time = Validator.validate(false, {max:14});
		_field.buyer_confirm_time = Validator.validate(false, {max:14});
		_field.seller_del = Validator.validate(false, {max:1});
		_field.buyer_del = Validator.validate(false, {max:1});
		_field.buyer_del_time = Validator.validate(false, {max:14});
		_field.seller_del_time = Validator.validate(false, {max:14});
		_field.buyer_score = Validator.validate(false, {max:1});
		_field.seller_score = Validator.validate(false, {max:1});
		_field.status = Validator.validate(false, {max:1});
		_field.province_code = Validator.validate(false, {max:10});
		_field.city_code = Validator.validate(false, {max:10});
		_field.town_code = Validator.validate(false, {max:10});
		_field.address = Validator.validate(false, {max:1000});
		_field.buyer_advise = Validator.validate(false, {max:400});
	
		
		return _field;
	}
	
	/* 提交Form数据*/
	function __formCommit(e) {
    	/*阻止默认的form submit*/
        e.preventDefault();
        
        $this.submit(_oper);
    }
	
	//提交
	this.submit = function(type){
		ParamCheck.commonSubmit({
			service_name 	: _controller_name,
			method_name 	: ("add" === type ? "addOrderInfo" : "updateOrderInfo"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "订单信息信息",
			switch_name 	: "orderInfoSwitch",
			module_id 		: "ordermgr",
			check_fields 	: ["order_id"],
			form_id			: "orderInfoForm",
			oparams			: {}
		});
	};
	
	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "orderInfoForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};

    function ChangeProvince(provinceId, cityCode, townCode){
        if(provinceId != ''){
            tableSupport.post(ctx + "/city/queryBy", {"province_code":provinceId}, function(data){
                var $city = $("#city_code");
                $city.val("");
                $city.html('<option value="" selected="selected">请选择...</option>');
                if(data == null || data == undefined){
                    return;
                }

                for(var i=0,len=data.length;i<len;i++){
                    $city.append(SOption(data[i].city_code, data[i].city_name, data[i].city_name));
                }
                $city.val(cityCode);

                ChangeCity(cityCode, townCode);
                $("#town_code").html('<option value="" selected="selected">请选择...</option>');
            });
        }else{
            $("#city").html('<option value="" selected="selected">请选择...</option>');
            $("#town_code").html('<option value="" selected="selected">请选择...</option>');
        }
    }

    function ChangeCity(cityId, townCode){
        if(cityId != ''){
            tableSupport.post(ctx + "/citytown/queryBy", {"city_code":cityId}, function(data){
                var $district = $("#town_code");
                $district.val("");
                $district.html('<option value="">区/县</opton>');

                if(data == null || data == undefined){
                    return;
                }
                for(var i=0,len=data.length;i<len;i++){
                    $district.append(SOption(data[i].town_code, data[i].town_name, data[i].town_name));
                }
                $district.val(townCode);
            });
        }
    }

	/* 查询订单信息信息*/
	this.queryByKey = function(){
		tableSupport.get(ctx + "/mgr/product/order/queryByKey", {"order_id" : _order_id}, function(orderInfo){
			Form.setValue(orderInfo);
            ChangeProvince(orderInfo.province_code, orderInfo.city_code, orderInfo.town_code)
		});
	};
	
	function init(){
		//Form.rptBank("rpt_bank");

        Form.dateTime("send_time", null, 'YYYYMMDDhhmmss');
        Form.dateTime("buyer_pay_time", null, 'YYYYMMDDhhmmss');
        Form.dateTime("trad_time", null, 'YYYYMMDDhhmmss');
        Form.dateTime("trad_finish_time", null, 'YYYYMMDDhhmmss');
        Form.dateTime("update_time", null, 'YYYYMMDDhhmmss');
        Form.dateTime("seller_deliver_time", null, 'YYYYMMDDhhmmss');
        Form.dateTime("buyer_confirm_time", null, 'YYYYMMDDhhmmss');
        Form.dateTime("buyer_del_time", null, 'YYYYMMDDhhmmss');
        Form.dateTime("seller_del_time", null, 'YYYYMMDDhhmmss');

		$this.initBtn();
		$this.formvalidator();
		
		if("add" !== _oper){
			$this.queryByKey();
		}
		
		if("update" === _oper){
			$("#order_id").attr("readonly", true);
		}else if("view" === _oper){
			Form.setDisabled("orderInfoForm");
		}
	}

	init();
};

