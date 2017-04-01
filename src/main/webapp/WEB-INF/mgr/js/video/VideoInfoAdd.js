/**
 * function:
 * VideoInfoAdd.js
 * @author sam.pan
 * @createTime 2017-03-27 10:43:38
 */
var VideoInfo = function (options){
	var $this = this;
	var _oper = options.oper || "add";
	var _video_id = options.video_id;
	var _controller_name = "com.huateng.xhcp.web.video.VideoInfoController";
	var _param_type = "com.huateng.xhcp.model.video.VideoInfo";
	
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

		_field.video_id = Validator.validate(false, {max:10});
		_field.title = Validator.validate(false, {max:50});
		_field.img_file_id = Validator.validate(false, {max:10});
		_field.video_file_id = Validator.validate(false, {max:10});
	
		
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
			method_name 	: ("add" === type ? "addVideoInfo" : "updateVideoInfo"),
			parameter_type  : _param_type,
			oper_type 		: type,
			oper_desc 		: ("add" === type ? "新增" : "编辑") + "视频信息信息",
			switch_name 	: "videoInfoSwitch",
			module_id 		: "videomgr",
			check_fields 	: ["video_id"],
			form_id			: "videoInfoForm",
			oparams			: {}
		});*/

		_upload(type);
	};

	function _upload(type){
		var img = $("#select_file").val();
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

		var video = $("#select_video").val();
		if (video != "") {
			if(!StringUtil.endWith(video.toLowerCase(), ".mp4")){
				Dialog.create("","文件格式不正确：请选择mp4格式的视频！",null);
				$("#videofile").val("");
				return false;
			}
		}

		if(_oper == "add"){
			$("#videoInfoForm").attr("action", ctx +"/mgr/video/addVideoInfo");
		}else if(_oper == "update"){
			$("#videoInfoForm").attr("action", ctx +"/mgr/video/updateVideoInfo");
		}

		if (Html5Util.supports_formdata()) {
			html5Upload();
		} else {
			$("#videoInfoForm").submit();
		}
	}

	function html5Upload() {
		new FileUpload({
			"url" : $("#videoInfoForm").attr("action"),
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
                        handler(resp.dataObj);
					}else{
						Noty.error(resp.desc);
					}
				}
			}
		}).uploadByFormId("videoInfoForm");
	}

	/* form 校验 */
	this.formvalidator = function(){
		var options={
			formId				: "videoInfoForm",
	        fields				: __fieldValidator(),
	        successFormFv		: __formCommit
		};
		
		new FormValidator(options);
	};
	/* 查询视频信息信息*/
	this.queryByKey = function(){
		tableSupport.get(mgr_path + "/video/queryByKey", {"video_id" : _video_id}, function(videoInfo){
			Form.setValue(videoInfo);
            handler(videoInfo);
		});
	};

	function handler(videoInfo){
        if(videoInfo != null){
            var img_url = videoInfo.img_file_url;
            var video_url = videoInfo.video_file_url;

            if($.trim(img_url) != ""){
                $("#img_file_id").val(videoInfo.img_file_id);
                $("#select_file").after("<img src='" +ctx+ "/"+ img_url +"' style='width:320px;height:240px;' />");

                if(_oper == "update"){
                    var $input = $("<input type='button' class='btn btn-primary btn-sm'  value='删除图片'/>");
                    $input.click(function(){
                        $this.deleteFile("img", videoInfo.img_file_id);
                    });

                    $("#select_file").next("img").after($input);
                }
            }

            if($.trim(video_url) != ""){
                $("#video_file_id").val(videoInfo.video_file_id);
                $("#select_video").after('<video src="'+ctx+'/'+video_url+'"  width="320" height="240" controls="controls">	Your browser does not support the video tag.</video> ');
                if(_oper == "update"){
                    var $input = $("<input type='button' class='btn btn-primary btn-sm'  value='删除视频'/>");
                    $input.click(function(){
                        $this.deleteFile("video", videoInfo.video_file_id);
                    });

                    $("#select_video").next("video").after($input);
                }
            }

        }
    }

	this.deleteFile = function(type,file_id) {
		tableSupport.post(mgr_path + "/video/deleteAndUpdateVideoInfo", {"type":type, "file_id": file_id, "video_id": _video_id}, function (resp) {
			Noty.response(resp);
			if(resp.status == "0"){
				if(type == "img"){
					$("#select_file").next("img").remove().end().next("input:eq(0)").remove();
					$("#img_file_id").val("");
				}else if(type == "video"){
					$("#select_video").next("video").remove().end().next("input:eq(0)").remove();
					$("#video_file_id").val("");
				}
			}
		});
	}
	
	function init(){
		//Form.rptBank("rpt_bank");
		$this.initBtn();
		$this.formvalidator();
		
		if("add" !== _oper){
			$this.queryByKey();
		}
		
		if("update" === _oper){
			$("#video_id").attr("readonly", true);
		}else if("view" === _oper){
			$("#select_file").hide();
			$("#select_video").hide();
			Form.setDisabled("videoInfoForm");
		}
	}
	
	init();
};

