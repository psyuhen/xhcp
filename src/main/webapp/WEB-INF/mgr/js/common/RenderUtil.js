/**
 * function:
 * RenderUtil.js
 * @author sam.pan
 * @createTime 2016-04-11 16:35:49
 */
var RenderUtil = function(){};

/**
 * 格式化表格的time[yyyy年MM月dd日HH时mm分ss秒]
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.time = function (data, type, row){
	return DateUtil.dateFormat(data, "yyyy年MM月dd日HH时mm分ss秒");
};

/**
 * 格式化表格的date[yyyy年MM月dd日]
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.date = function (data, type, row){
	return DateUtil.dateFormat(data, "yyyy年MM月dd日");
};
/**
 * 格式化表格的hour[HH时mm分ss秒]
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.hour = function (data, type, row){
	return DateUtil.dateFormat(data, "HH时mm分ss秒");
};
/**
 * 格式化datetime
 * @param format 格式
 * @returns {Function}
 */
RenderUtil.datetime = function (format){
	format = format || "yyyy年MM月dd日HH时mm分ss秒";
	
	return function (data, type, row){
		return DateUtil.dateFormat(data, format);
	};
};
/**
 * 格式化金额
 * @author shijunkai
 */
RenderUtil.formatMoney = function(data, type, row) {
	data = data + "";
	if (!StringUtil.isBlank(data)) {
		if (data == "null") {
			return "0.00";
		}
		if (StringUtil.startWith(data, "\.")) {
			data = "0" + data;
		}
		var m = data.split("\.");
		if (m.length == 1) {
			return data + ".00";
		} else {
			if (m[1].length == 0) {
				return data + "00";
			} else if (m[1].length == 1) {
				return data + "0";
			}
			return data;
		}
	}
	return "0.00";
};
/**
 * 0-否，1-是
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.yesOrNo = function (data, type, row){
	if(data == "0"){
		return "否";
	}else if(data == "1"){
		return "是";
	}
	return "";
};
/**
 * 失效控制，1-永久，2-根据失效日
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.expireFlag = function (data, type, row){
	if(data == "1"){
		return "永久";
	}else if(data == "2"){
		return "根据失效日";
	}
	return "";
};
/**
 * 国内外标志，0-国外，1-国内，2-境外
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.abroad = function (data, type, row){
	if(data == "0"){
		return "国外";
	}else if(data == "1"){
		return "国内";
	}else if(data == "2"){
		return "境外";
	}
	return "";
};
/**
 * 国内外标志，0-国外，1-国内，2-境外
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.trad_sts = function (data, type, row){
	if(data == ""){
		return "";
	}
	
	if(data == '00'){
		return '成功';
	}else{
		return '失败';
	}
};
/**
 * 卡种，04-贷记卡,其他为借记卡
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.card_type = function (data, type, row){
	if(data == null){
		return "";
	}else if(data == "04"){
		return "贷记卡";
	}else{
		return "借记卡";
	}
};
/**
 * 上报分行
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.rpt_bank = function (data, type, row){
	var _key = "__RPT_BANK";
	var _list = [];
	//先从本地缓存中获取，能获取到就取本地缓存的数据
	var tmp = Storage.getItem(_key);
	tmp = $.trim(tmp);
	if(tmp !== ""){
		var list = JSON.parse(tmp);
		_list = list;
	}else{
		//本地没有先查询数据库
		tableSupport.synchGet(cfrm_path + "/brana/rptbank", {}, function(list){
			_list = list;
			Storage.addItem(_key, JSON.stringify(list));
		});
	}
	
	var index = StringUtil.binarySearch(_list, data, "bran_no");
	if(index == -1){
		return data;
	}
	
	return _list[index].bran_name;
};

/* 暂存dictnames*/
RenderUtil.dictnames = {};
/**
 * 会先从LocalStorage获取，获取不到再从本地变量中获取。
 * 如果两个都没有，再从数据库获取
 * 增加数字字典的render
 * @param dictname 字典名称
 * @returns {Function}
 */
RenderUtil.dictname = function(dictname){
	//先从LocalStorage中获取
	var _dictname = $.trim(Storage.getItem(dictname));
	var _isEmpty = $.isEmptyObject(RenderUtil.dictnames[dictname]);
	var _dictextObj = null;
	if(_dictname === "" && _isEmpty){
		/*判断是否为空，为空再去查询*/
		var dict = new Dictionary({});
		dict.synchQueryDictByName(dictname, function(list){
			var dicttext = {};
			for (var i = 0, len = list.length; i < len; i++) {
				dicttext[list[i].dd_id] = list[i].dd_text;
			}
			Storage.addItem(dictname, JSON.stringify(dicttext));
			RenderUtil.dictnames[dictname] = dicttext;
			
			_dictextObj = dicttext;
		});
	}
	
	if(_dictname !== ""){//从LocalStorage能获取到
		_dictextObj = JSON.parse(_dictname);
	}else if(!_isEmpty){
		_dictextObj = RenderUtil.dictnames[dictname];
	}
	
	/* 返回datatable对应的格式*/
	return function(data, type, row){
		var dictext = _dictextObj;
		if(dictext != null && dictext[data] != undefined){
    		return dictext[data];
    	}
    	return data;
	};
};

/**
 * 字典对象Render
 * @param dictObj 字典对象 
 * 			{"key" : "value" , ""(空字符串) : "默认值"}
 * @returns {Function}
 */
RenderUtil.dictObj = function(dictObj){
	return function(data , type , row){
		return dictObj[data] || dictObj[""] || data || "";
	};
};

/**
 * 列表中字段显示
 * @param da
 * @param st
 * @param dt
 * @returns {Function}
 */
RenderUtil.label = function (da, st, dt){
	return function ( data, type, row ) {
		if(data == da){
			return "<span class='label-success label label-default'>" + st + "</span>";
		}
		return "<span class='label-default label label-danger'>" + dt + "</span>";
	};
};
/**
 * 生效标志，1/生效，0/失效
 * @param data
 * @param type
 * @param row
 * @returns {String}
 */
RenderUtil.valid_flag = RenderUtil.label("1", "生效", "失效");
/**
 *  商户类型：00-其他类, 01-第三方支付, 02-投资理财类
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.commertype = function ( data, type, row ) {
	if(data == "00"){
		return "其他类";
	}else if(data == "01") {
		return "第三方支付";
	}else if(data == "02") {
		return "投资理财类";
	}else if(data == "03") {
		return "电子客票类";
	}else if(data == "04") {
		return "生活百货类";
	}else if(data == "05") {
		return "大宗商品类";
	}else if(data == "06") {
		return "教育培训类";
	}else if(data == "07") {
		return "旅游宾馆类";
	}else if(data == "08") {
		return "餐饮娱乐类";
	}else if(data == "09") {
		return "公共事业缴费类";
	}else if(data == "10") {
		return "医疗保健类";
	}else if(data == "11") {
		return "游戏通讯类";
	}else if(data == "12") {
		return "公共事业类";
	}
	return data;
}

/**
 *  黑名单清单转换操作：1-不转换黑名单, 0或2-转为黑名单
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.trans = function ( data, type, row ) {
	if(data == "1"){
		return "黑名单清单";
	}else if(data == "0" || data == "2") {
		return "<a class='btn btn-success btn-xs' name='trans_a'>"+"<i class='glyphicon glyphicon-edit'></i>转为黑名单</a>";
	}
	return data;
}

/**
 *  渠道：01-网银, 02-手机
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.chanlNo = function ( data, type, row ) {
	if(data == "01"){
		return "网银";
	}else if(data == "02"){
		return "手机银行";
	}else if(data == "03"){
		return "Call Center";
	}
	return data;
}

/**
 *  渠道：0-解除, 1-新增
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.status = function ( data, type, row ) {
	if(data == "0"){
		return "解除";
	}else if(data == "1"){
		return "新增";
	}
	return data;
}

/**
 *  同步标识，0/未同步，1/已同步
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.synFlag = function ( data, type, row ) {
	if(data == "0"){
		return "未同步";
	}else if(data == "1"){
		return "已同步";
	}
	return data;
}

/**
 *  生效标志，0/生效，1/失效
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.activeFlag = function ( data, type, row ) { 
	if(data == "0"){
		return "生效";
	}else if(data == "1") {
		return "失效";
	}else if(data == "2") {
		return "其它";
	}
	return  data;
}

/**
 *  生效标志，A/增加，U/修改，D/删除
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.msgType = function ( data, type, row ) { 
	if(data == "A"){
		return "增加";
	}else if(data == "D") {
		return "删除";
	}else if(data == "U") {
		return "修改";
	}
	return  data;
}

/**
 *  生效标志，1/C，2/JAVA，3/PRC
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.logCata = function ( data, type, row ) { 
	if(data == "1"){
		return "C";
	}else if(data == "2") {
		return "JAVA";
	}else if(data == "3") {
		return "PRC";
	}
	return  data;
}

/**
 *  生效标志，1/FATAL，2/ERROR，3/WARN，4/INFO，5/DEBUG
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.msgLevel = function ( data, type, row ) { 
	if(data == "1"){
		return "FATAL";
	}else if(data == "2") {
		return "ERROR";
	}else if(data == "3") {
		return "WARN";
	}else if(data == "4") {
		return "INFO";
	}else if(data == "5") {
		return "DEBUG";
	}
	return  data;
}

/**
 *  商户类型，1/网银-普通PC。4/手机银行-iOS平台
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.equipType = function ( data, type, row ) {
	if(data == "1"){
		return "网银-普通PC";
	}else if(data == "4"){
		return "手机银行-iOS平台";
	}else if(data == "5"){
		return "手机银行-Android平台";
	}else if(data == "7"){
		return "PAD银行-IOS平台";
	}else if(data == "8"){
		return "PAD银行-Android平台";
	}
	return data;
}

/**
 *  平台，01/高风险商户。02/二级商户
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.tenantType = function ( data, type, row ) {
	if(data == "01"){
		return "高风险商户";
	}else if(data == "02"){
		return "二级商户";
	}
	return data;
}

/**
 *  操作
 * @param data
 * @param type
 * @param row
 * @author liangcy
 * @returns
 */
RenderUtil.operType = function ( data, type, row ) {
	
	return "<a class='btn btn-success btn-xs' name='trans_a'>解除</a>";
}

/**
 * 风险等级商户类型：0-B2C商户, 1-B2B商户, 2-同为B2C和B2B商户
 * @param data
 * @param type
 * @param row
 * @author bolin
 * @returns
 */
RenderUtil.shoptype = function (data, type, row){
	if(data == null){
		return "";
	}if(data == "0"){
		return "B2C商户";
	}if(data == "1"){
		return "B2B商户";
	}if(data == "2"){
		return "同为B2C和B2B商户";
	}
};
/**
 * 风险等级商户种类：0-网上支付商户，1-收银台商户，2-所有，3-实体商户
 * @param data
 * @param type
 * @param row
 * @author bolin
 * @returns
 */
RenderUtil.billflag = function (data, type, row){
	if(data == null){
		return "";
	}if(data == "0"){
		return "网上支付商户";
	}if(data == "1"){
		return "收银台商户";
	}if(data == "2"){
		return "所有";
	}if(data == "3"){
		return "实体商户";
	}
};
/**
 * 风险等级商户级别：A-普通商户，B-高级商户，C-VIP商户
 * @param data
 * @param type
 * @param row
 * @author bolin
 * @returns
 */
RenderUtil.SHOPLEVEL = function (data, type, row){
	if(data == null){
		return "";
	}if(data == "A"){
		return "普通商户";
	}if(data == "B"){
		return "高级商户";
	}if(data == "C"){
		return "VIP商户";
	}
};
/**
 * 风险等级用户状态：00-正常，01-挂起，02-销户
 * @param data
 * @param type
 * @param row
 * @author bolin
 * @returns
 */
RenderUtil.statuscode = function (data, type, row){
	if(data == null){
		return "";
	}if(data == "00"){
		return "正常";
	}if(data == "01"){
		return "挂起";
	}if(data == "02"){
		return "销户";
	}
};
/**
 * 风险等级开通渠道：000000000000-未开通，100000000000-网上银行，010000000000-手机银行，001000000000-Call Center
 * @param data
 * @param type
 * @param row
 * @author bolin
 * @returns
 */
RenderUtil.channel = function (data, type, row){
	if(data == null){
		return "";
	}if(data == "000000000000"){
		return "未开通";
	}if(data == "100000000000"){
		return "网上银行";
	}if(data == "010000000000"){
		return "手机银行";
	}if(data == "001000000000"){
		return "Call Center";
	}
};
/**
 * 风险等级签约状态：c-已经签约，N-未签约
 * @param data
 * @param type
 * @param row
 * @author bolin
 * @returns
 */
RenderUtil.agreestatus = function (data, type, row){
	if(data == null){
		return "";
	}if(data == "C"){
		return "已经签约";
	}if(data == "N"){
		return "未签约";
	}
};


/**
 * 交易阻断类型
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.trad_type = function (data, type, row){
	if(!RenderUtil.tranTypeList){
		tableSupport.synchGet(cfrm_path + "/queryShamAggTradno/tranType", {}, function(list){
			RenderUtil.tranTypeList = list;
		});
	}
	
	var index = StringUtil.binarySearch(RenderUtil.tranTypeList, data, "tran_type");
	if(index == -1){
		return data;
	}
	
	return RenderUtil.tranTypeList[index].trad_type;
};

/**
 * 根据规则号查询desc值(描述)，再title展示
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.rule_no = function (data, type, row){
	if(data == ""){
		return data;
	}else{
		var ruleNoA = "";
		var ruleDesc = "";
		var ruleNos = data.split(",");
		for(var i = 0; i < ruleNos.length; i++){
			ruleDesc = RenderUtil.ruleNoRender(ruleNos[i]);
			ruleNoA += "<a title='" + ruleDesc + "' >"+ ruleNos[i] +"</a>,"
		}
		return ruleNoA.substring(0,ruleNoA.length - 1);
	}
};

/**
 * 根据规则号查询desc值(描述)
 * @param rule_no
 * @returns
 */
RenderUtil.ruleNoRender = function(rule_no){
	if(!RenderUtil.ruleList){
		tableSupport.synchPost(cfrm_path + "/sham/virtualtrans/queryShamAggConfigAll", {}, function(list){
			RenderUtil.ruleList = list;
		});
	}
	
	var index = StringUtil.binarySearch(RenderUtil.ruleList, rule_no, "rule_no");
	if(index == -1){
		return rule_no;
	}
	
	return RenderUtil.ruleList[index].rule_desc;
}

/**
 * 是否通过复核：0-已推数，1-未复核，2-已复核
 * @param data
 * @param type
 * @param row
 * @author fengsq
 * @returns
 */
RenderUtil.chek_flag = function (data, type, row){
	if(data == "0"){
		return "已推数";
	}if(data == "1"){
		return "未复核";
	}if(data == "2"){
		return "已复核";
	}
	return "";
};

/**
 * 复核状态：0-未复核，1-复核通过，2-复核未通过，3-加入白名单
 * @param data
 * @param type
 * @param row
 * @author fengsq
 * @returns
 */
RenderUtil.check_status = function (data, type, row){
	if(data == "0"){
		return "未复核";
	}if(data == "1"){
		return "复核通过";
	}if(data == "2"){
		return "复核未通过";
	}if(data == "3"){
		return "加入白名单";
	}
	return "";
};
/**
 * 反钓鱼可疑状态：01-待送检；02-待确认；03-确认为钓鱼；其它-确认非钓鱼
 * @author shijunkai
 * @param data
 * @param type
 * @param row
 * @returns {String}
 */
RenderUtil.apm_status = function(data, type, row) {
	if (data == "01") {
		return "待送检";
	} else if (data == "02") {
		return "待确认";
	} else if (data == "03") {
		return "确认为钓鱼";
	} else {
		return "确认非钓鱼";
	}
};
/**
 * 案例库复核状态：2-未复核，0-复核通过，1-复核拒绝，3-编辑复核，4-删除复核
 * @param data
 * @param type
 * @param row
 * @author shijunkai
 * @returns
 */
RenderUtil.bi_checked = function(data, type, row) {
	if (data == 2) {
		return "未复核";
	} else if (data == 0) {
		return "复核通过";
	} else if (data == 1) {
		return "复核拒绝";
	} else if (data == 3) {
		return "编辑复核";
	} else if (data == 4) {
		return "删除复核";
	}
	return data;
};
/**
 * 黑名单终端拦截反馈查询 数据类型
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.bb_data_type = function (data, type, row){
	if(data === "01"){
		return "支付";
	}if(data === "02"){
		return "登录";
	}
	return data;
};
/**
 * 事后签约,验证类型
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.auth_type = function ( data, type, row ) {
	if(data == "0"){
		return "无";
	}else if(data == "1"){
		return "刮刮卡";
	}else if(data == "2"){
          return "动态口令卡";
      }else if(data == "3"){
          return "电子证书";
      }else{
      	return data;
      }
};
/**
 * 生成一个连接
 * @param name 连接名称
 * @returns {Function}
 */
RenderUtil.link = function (name) {
	return function(data, type, row){
		if(StringUtil.isBlank(data)){
			return "";
		}
		return "<a href='javascript:void(0)' name='" + name + "'>" + data + "</a>";
	};
};
/**
 * 规则标志-机控与非机控
 * @param data
 * @param type
 * @param row
 * @returns {String}
 */
RenderUtil.rule_flag = function(data, type, row) {
	if (data == '1') {
		return "机控";
	} else {
		return "非机控";
	}
};
/**
 * 事中事后的渠道交易码
 * @param data
 * @param type
 * @param row
 * @returns {String}
 */
RenderUtil.chanl_trad_no = function ( data, type, row ) {
	if(data != null){
		return data.substring(0, 6);
	};
	return "";
};

/**
 * 将数字的毫秒数转换为文字描述(如：1天11小时24分36秒2毫秒)
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.getDateDesc = function ( data, type, row ) {
	return DateUtil.getDateDesc(data, true);
}
/**
 * DW 风险记录/流水 交易类别
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.trade_type = function ( data, type, row ) {
	  if(data == "1"){
        return "转账";
      }else if(data == "2"){
        return "代发";
      }else{
        return "其它";
      }	
};
/**
 * 电子银行风控/报表/商户退款类
 * @author bolin
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.trad_sts = function (data, type, row){
	if(data == "00"){
		return "成功";
	}else if(data == "01"){
		return "错误";
	}else if(data == "02"){
		return "请求发送前失败";
	}else if(data == "03"){
		return "请求发送后失败";
	}else if(data == "04"){
		return "未知状态";
	}else if(data == "05"){
		return "发送状态";
	}else{
		return data;
	}
};
/**
 * 电子银行风控/报表/商户退款类
 * @author bolin
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.payRefFlg = function (data, type, row){
	if(data == "0"){
		return "支付";
	}else if(data == "01"){
		return "退款";
	}else{
		return data;
	}
}
/**
 * 电子银行风控/风险监控/客户流水查询/日终流水渠道
 * @author bolin
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.ysChanlNo = function (data, type, row){
	if(data == "01"){
		return "网银渠道";
	}else if(data == "02"){
		return "手机渠道";
	}else if(data == "03"){
		return "电话渠道";
	}else if(data == "05"){
		return "短信渠道";
	}else if(data == "91"){
		return "快捷支付";
	}else{
		return data;
	}
}
/**
 * 卡种(card_kind)
 * @author bolin
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.cardKind = function (data, type, row){
	if(data == "1"){
		return "借记卡";
	}else if(data == "2"){
		return "贷记卡";
	}else if(data == "3"){
		return "准贷记卡";
	}else if(data == "4"){
		return "预付费卡";
	}
}
/**
 * 内外卡标识(card_flag)
 * @author bolin
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.cardFlag = function (data, type, row){
	if(data == "1"){
		return "行内卡";
	}else{
		return "行外卡";
	}
}
/**
 * 位置风控客户所属运营商：0、电信；1、移动；2、联通
 * @author shijunkai
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.cust_mrch = function(data, type, row) {
	if (data == "0") {
		return "电信";
	} else if (data == "1") {
		return "移动";
	} else if (data == "2") {
		return "联通";
	} else {
		return data;
	}
};
/**
 * 位置风控定位结果：0-未定位；1-定位成功；2-定位失败；3-定位超时
 * @author shijunkai
 * @param data
 * @param type
 * @param row
 * @returns
 */
RenderUtil.loactionResult = function(data, type, row) {
	if (data == "0") {
		return "未定位";
	} else if (data == "1") {
		return "定位成功";
	} else if (data == "2") {
		return "定位失败";
	} else if (data == "3") {
		return "定位超时";
	} else {
		return data;
	}
};
/**
 * 获取终端描述符
 * @param platform 平台号
 * @returns {___anonymous17591_17592}
 */
RenderUtil.getEquipDesc = function(platform){
	var field = {};
	field.eq2 = "设备2";
	field.eq3 = "设备3";
	field.eq4 = "设备4";
	field.eq5 = "设备5";
	if(platform === "1"){
		field.eq2 = "BIOS序列号";
		field.eq3 = "MAC地址";
		field.eq4 = "磁盘分区的卷轴序列号";
		field.eq5 = "IDE控制器序列号";
	}else if(platform === "4" || platform === "7"){
		field.eq2 = "绑定标识";
		field.eq3 = "MAC地址";
		field.eq4 = "IP";
		field.eq5 = "GPS";
	}else if(platform === "5" || platform === "8"){
		field.eq2 = "绑定标识";
		field.eq3 = "IMEI";
		field.eq4 = "IP";
		field.eq5 = "GPS";
	}else if(platform === "10" || platform === "11"){
		field.eq3 = "设备标识";
	}
	return field;
};