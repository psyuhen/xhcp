/**
 * function:
 * MerchInfoAdd.js
 * @author sam.pan
 * @createTime 2017-03-29 15:20:01
 */
var MerchInfo = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _merch_id = options.merch_id;
	var _controller_name = "com.huateng.xhcp.web.product.MerchInfoController";
	var _param_type = "com.huateng.xhcp.model.product.MerchInfo";
	var _count = 0;

	/*查询按钮事件初始化*/
	this.initBtn = function(){
		Form.dateTime("published_date", null, 'YYYYMMDD');
		/*清空*/
		$("#btn_reset").on("click", function(){
			Form.reset("conditionForm");
		});

		/*选择事件*/
		$("#classify_root").change(function () {
			var classifyRoot = $(this).val();
			$("#classify_id option:gt(0)").remove();
			if(classifyRoot == ""){
				$("#classify_id").val("");
				return;
			}

			queryAndSet(classifyRoot, null);
		});

        $("#addPhotoImg img").on("click", function () {
            addClick("photo");
        });
        $("#addDetailImg img").on("click", function () {
            addClick("detail");
        });

        $("#imgphotofilesDiv").on("change", "div :file", function (event) {
            //获取文件列表
            var fileList = event.target.files;
            showImg($(this), fileList);
        });
        $("#imgdetailfilesDiv").on("change", "div :file", function (event) {
            //获取文件列表
            var fileList = event.target.files;
            showImg($(this), fileList);
        });

        if(_oper != "view"){
            $("#imgphotofilesDiv").on("click", "div img", function (event) {
                $(this).nextAll(":file").click();
            });
            $("#imgdetailfilesDiv").on("click", "div img", function (event) {
                $(this).nextAll(":file").click();
            });
        }
	};

	function showImg(obj, fileList){
        if(fileList != null && fileList.length > 0){
            var filename = obj.val();
            if(checkImg(filename)){
                addImgFile(obj,fileList[0]);
            }else{
                obj.val("");
            }
        }else{
            obj.prevAll("img").attr("src", "");
        }
    }

	function addClick(fileType){
        var imgDiv = appendDiv(fileType);
        $("#img"+fileType+"filesDiv").append(imgDiv);
        imgDiv.find(":file").click();
    }

    function addImgFile(obj,file){
        var fileReaderSupport = typeof FileReader != "undefined";
        if(!fileReaderSupport){
            alert("浏览器不支持FileReader API");
            return;
        }
        var reader = new FileReader();
        reader.onload = function(event){
            var fileContents = event.target.result;
            obj.prevAll("img").attr("src", fileContents);
        };
        reader.readAsDataURL(file);
    }

    function appendDiv(fileType) {
        var oneImg = $("<div class='imgDiv'>&nbsp;&nbsp;&nbsp;");
        var img = $("<img style='cursor:pointer;'>");
        img.attr({
            "width":"108px",
            "height":"108px"
        });
        img.addClass("detailphoto");
        var a = $("<a href='javascript:void(0)' class='delete' ><i class='glyphicon glyphicon-remove red'></i></a>");

        if(_oper != "view"){
            a.click(function () {
                var $athis = $(this);
                if(_oper == "update"){
                    var imgId = $athis.prev("img").attr("id");
                    if(imgId != ""){
                        tableSupport.post(mgr_path + "/product/gallery/deleteMerchGallery",{}, function (resp) {
                            Noty.response(resp);
                            if(resp.status == "0"){
                                $athis.parent().remove();
                            }
                        });
                    }
                }else{
                    $athis.parent().remove();
                }
            });
        }

        var input = $("<input type='file' name='"+fileType+"_"+ (_count ++)+"' style='display:none;' />");
        oneImg.append(img);
        oneImg.append(a);
        oneImg.append(input);

        return oneImg;
    }

	function queryAndSet(pclsId, classifyId){
		tableSupport.post(mgr_path + "/classify/queryBy",{"pcls_id":pclsId},function (list) {
			var $classifyId = $("#classify_id");
			for(var i=0,len=list.length;i<len;i++){
				$classifyId.append(SOption(list[i].classify_id, list[i].name, list[i].desc));
			}
			if($.trim(classifyId) != ""){
				$classifyId.val(classifyId);
			}
		});
	}
	
	/* 字段校验*/
	function __fieldValidator(){
		var _field = {};

		_field.merch_id = Validator.validate(false, {max:10});
		_field.name = Validator.validate(false, {max:300});
		_field.desc = Validator.validate(false, {max:1000});
		_field.classify_id = Validator.validate(false, {max:10});
		_field.price = Validator.validate(false, {max:11}, null, Validator.d(11,4));
		_field.in_stock = Validator.validate(false, {max:10}, {r:/\d{1,10}/,m:"请输入整数"});
		_field.published_date = Validator.validate(false, {max:14});
		_field.out_published = Validator.validate(false, {max:1});
		_field.update_time = Validator.validate(false, {max:14});
		_field.create_time = Validator.validate(false, {max:14});
		_field.sm_recommend = Validator.validate(false, {max:1});
		_field.free_shipping = Validator.validate(false, {max:1});
		_field.unit = Validator.validate(false, {max:10});
		_field.weight = Validator.validate(false, {max:11}, null, Validator.d(11,4));
		_field.standard = Validator.validate(false, {max:10});
		// _field.hits = Validator.validate(false, {max:10});
	
		
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
		/*ParamCheck.commonSubmit({
			service_name 	: _controller_name,
			method_name 	: ("add" === type ? "addMerchInfo" : "updateMerchInfo"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "产品信息",
			switch_name 	: "merchInfoSwitch",
			module_id 		: "productmgr",
			check_fields 	: ["merch_id"],
			form_id			: "merchInfoForm",
			oparams			: photos
		});*/
		_upload(type);
	};

	function checkImg(img){
        if (img != "") {
            var isRightFmt = false;
            var photofmts = [".gif",".jpg",".jpeg",".png",".bmp"];
            $.each(photofmts, function (index, item) {
                if (StringUtil.endWith(img.toLowerCase(), item)) {
                    isRightFmt = true;
                    return false;
                }
            });
            if(!isRightFmt){
                Dialog.create("","文件格式不正确：请选择gif,jpg,jpeg,png,bmp格式的图片！",null);
                return false;
            }
        }
        return true;
    }

    function _upload(_oper){
        if(_oper == "add"){
            $("#merchInfoForm").attr("action", ctx +"/mgr/product/addMerchInfo");
        }else if(_oper == "update"){
            $("#merchInfoForm").attr("action", ctx +"/mgr/product/updateMerchInfo");
        }

        if (Html5Util.supports_formdata()) {
            html5Upload();
        } else {
            $("#merchInfoForm").submit();
        }
    }

    function html5Upload() {
        new FileUpload({
            "url" : $("#merchInfoForm").attr("action"),
            "progress" : $("#progress"),
            "showDefaultSuccess" : false,
            "success" : function (evt) {
                var text = evt.target.responseText;
                if(text != ""){
                    var resp = eval("(" + text + ")");
                    if(resp.status == "0"){
                        Noty.information("保存成功!");
                        $("#select_file").val("");
                        $("#select_video").val("");
                        //handler(resp.dataObj);
                    }else{
                        Noty.error(resp.desc);
                    }
                }
            }
        }).uploadByFormId("merchInfoForm");
    }

	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "merchInfoForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询产品信息*/
	this.queryByKey = function(){
		tableSupport.get(mgr_path + "/product/queryByKey", {"merch_id" : _merch_id}, function(merchInfo){
			Form.setValue(merchInfo);

			if(merchInfo != null){
				var classifyId = merchInfo.classify_id;
				var pclsId = merchInfo.pcls_id;
				if($.trim(pclsId) != ""){
					$("#classify_root").val(pclsId);
					queryAndSet(pclsId, classifyId);
				}
			}
		});

		//查询产品图片信息
        tableSupport.get(mgr_path + "/product/gallery/" + _merch_id, {}, function(list){
            if(list != null){
                //图片展示
                $.each(list, function (index, item) {
                    var fileType = $.trim(item.file_type);
                    if(fileType == "0"){
                        var imgDiv = appendDiv("photo");
                        imgDiv.find("img:eq(0)").attr("src", ctx +"/"+ item.path + item.name);
                        imgDiv.attr("id", "photos_" + item.gallery_id);
                        $("#imgphotofilesDiv").append(imgDiv);
                    }else if(fileType == "1"){
                        var imgDiv = appendDiv("detail");
                        imgDiv.find("img:eq(0)").attr("src", ctx +"/"+ item.path + item.name);
                        imgDiv.attr("id", "details_" + item.gallery_id);
                        $("#imgdetailfilesDiv").append(imgDiv);
                    }
                });
                //图片详情
            }
        });
	};
	
	function init(){
		//Form.rptBank("rpt_bank");
		$this.initBtn();
		$this.formvalidator();
		
		if("add" !== _oper){
			$this.queryByKey();
		}
		
		if("update" === _oper){
			$("#merch_id").attr("readonly", true);
		}else if("view" === _oper){
		    $("#addPhotoImg,#addDetailImg").hide();
			Form.setDisabled("merchInfoForm");
		}
	}
	
	init();
};

