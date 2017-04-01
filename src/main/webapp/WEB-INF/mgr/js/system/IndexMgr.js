/**
 * Created by sam.pan on 2017/3/20.
 */

var IndexMgr = function(){

    var _logoTimer = null;
    var _indexPhotoTimer = null;
    var _videoTimer = null;
    var _newsPhotoTimer = null;

    function addOper(obj) {
        //$('img', this).fadeToggle(1000);
        obj.find('.gallery-controls').remove();
        obj.prepend('<div class="well gallery-controls">' +
            '<p><a href="#" title="点击更换" class="gallery-edit btn"><i class="glyphicon glyphicon-edit"></i></a> ' +
            '<a href="#" title="点击删除" class="gallery-delete btn"><i class="glyphicon glyphicon-remove"></i></a></p>' +
            '</div>');
        obj.find('.gallery-controls').stop().animate({'margin-top': '-30'}, 400);
    }
    function removeOper(obj) {
        //$('img', this).fadeToggle(1000);
        obj.find('.gallery-controls').stop().animate({'margin-top': '-1'}, 200, function () {
            $(this).remove();
        });
    }

    function logoinit(){
        //gallery controls container animation
        $("#logophoto").hover(function(){
            addOper($(this));
        },function () {
            removeOper($(this));
        });

        //gallery delete
        $('#logoul').on('click', '.gallery-delete', function (e) {
            e.preventDefault();
            var li = $(this).parents('.thumbnail');
            var _a = li.children("a:eq(0)");
            var fileId = _a.attr("id");
            if(fileId == "" || fileId == undefined){
                Dialog.create("", "请先添加图片", null);
                return;
            }
            tableSupport.remove(mgr_path + "/index/deletefile", {
                file_id : fileId
            }, function (obj) {
                Noty.response(obj);
                if(obj.status == "0"){//删除成功
                    var _defaultImg = ctx + "/css/images/content_add_image_icon.png";
                    _a.attr({
                        id : "",
                        href: _defaultImg
                    });
                    _a.css("background","url(" + _defaultImg + ")");
                    _a.children("img:eq(0)").attr("src", _defaultImg);
                }
            });
            
        });
        //gallery edit
        $('#logoul').on('click', '.gallery-edit', function (e) {
            e.preventDefault();
            var li = $(this).parents('.thumbnail');
            var _a = li.children("a:eq(0)");
            var fileId = _a.attr("id");
            if($.trim(fileId) != ""){
                Dialog.create("", "请先删除图片,再上传", null);
                return;
            }
            $("#uploadFile").val("");
            $("#uploadFile").click();
            if(_logoTimer == null){
                _logoTimer = setInterval(function () {
                    var val = $("#uploadFile").val();
                    if(val != ""){
                        clearInterval(_logoTimer);
                        _logoTimer = null;
                        upload("importForm","uploadFile", "upload", "progress", li);
                    }
                },500);
            }
        });

        //gallery colorbox
        $('#logophoto a').colorbox({
            // rel: 'thumbnail a',
            transition: "elastic",
            maxWidth: "95%",
            maxHeight: "95%",
            slideshow: true
        });

    }

    function indexphotoinit(){
        $("#indexphotoul li").hover(function(){
            addOper($(this));
        },function () {
            removeOper($(this));
        });
        //gallery delete
        $('#indexphotoul').on('click', '.gallery-delete', function (e) {
            e.preventDefault();
            var li = $(this).parents('.thumbnail');
            var _a = li.children("a:eq(0)");
            var fileId = _a.attr("id");
            if(fileId == "" || fileId == undefined){
                Dialog.create("", "请先添加图片", null);
                return;
            }
            tableSupport.remove(mgr_path + "/index/deletefile", {
                file_id : fileId
            }, function (obj) {
                Noty.response(obj);
                if(obj.status == "0"){//删除成功
                    var _defaultImg = ctx + "/css/images/content_add_image_icon.png";
                    _a.attr({
                        id : "",
                        href: _defaultImg
                    });
                    _a.css("background","url(" + _defaultImg + ")");
                    _a.children("img:eq(0)").attr("src", _defaultImg);
                }
            });

        });
        //gallery edit
        $('#indexphotoul').on('click', '.gallery-edit', function (e) {
            e.preventDefault();
            var li = $(this).parents('.thumbnail');
            var _a = li.children("a:eq(0)");
            var fileId = _a.attr("id");
            if($.trim(fileId) != ""){
                Dialog.create("", "请先删除图片,再上传", null);
                return;
            }
            $("#indexphotofile").val("");

            $("#indexphotofile").click();
            if(_indexPhotoTimer == null){
                _indexPhotoTimer = setInterval(function () {
                    var val = $("#indexphotofile").val();
                    if(val != ""){
                        clearInterval(_indexPhotoTimer);
                        _indexPhotoTimer = null;
                        upload("indexphotoForm","indexphotofile", "indexphotobtn", "progress1", li);
                    }
                },500);
            }
        });

        $('#indexphotoul .thumbnail a').colorbox({
            rel: 'thumbnail a',
            transition: "elastic",
            maxWidth: "95%",
            maxHeight: "95%",
            slideshow: true
        });
    }


    function videoinit() {
        $("#videobtn").on("click",function () {
            $("#videofile").click();
            if(_videoTimer == null){
                _videoTimer = setInterval(function () {
                    var val = $("#videofile").val();
                    if(val != ""){
                        clearInterval(_videoTimer);
                        _videoTimer = null;
                        videoupload();
                    }
                },500);
            }
        });
    }

    function newsphotoinit() {
        //gallery controls container animation
        $("#newsphoto").hover(function(){
            addOper($(this));
        },function () {
            removeOper($(this));
        });

        //gallery delete
        $('#newsul').on('click', '.gallery-delete', function (e) {
            e.preventDefault();
            var li = $(this).parents('.thumbnail');
            var _a = li.children("a:eq(0)");
            var fileId = _a.attr("id");
            if(fileId == "" || fileId == undefined){
                Dialog.create("", "请先添加图片", null);
                return;
            }
            tableSupport.remove(mgr_path + "/index/deletefile", {
                file_id : fileId
            }, function (obj) {
                Noty.response(obj);
                if(obj.status == "0"){//删除成功
                    var _defaultImg = ctx + "/css/images/content_add_image_icon.png";
                    _a.attr({
                        id : "",
                        href: _defaultImg
                    });
                    _a.css("background","url(" + _defaultImg + ")");
                    _a.children("img:eq(0)").attr("src", _defaultImg);
                }
            });

        });
        //gallery edit
        $('#newsul').on('click', '.gallery-edit', function (e) {
            e.preventDefault();
            var li = $(this).parents('.thumbnail');
            var _a = li.children("a:eq(0)");
            var fileId = _a.attr("id");
            if($.trim(fileId) != ""){
                Dialog.create("", "请先删除图片,再上传", null);
                return;
            }
            $("#newsFile").val("");
            $("#newsFile").click();
            if(_newsPhotoTimer == null){
                _newsPhotoTimer = setInterval(function () {
                    var val = $("#newsFile").val();
                    if(val != ""){
                        clearInterval(_newsPhotoTimer);
                        _newsPhotoTimer = null;
                        upload("newsForm","newsFile", "newsupload", "progress3", li);
                    }
                },500);
            }
        });

        //gallery colorbox
        $('#newsphoto a').colorbox({
            // rel: 'thumbnail a',
            transition: "elastic",
            maxWidth: "95%",
            maxHeight: "95%",
            scrolling: false,
            slideshow: true
        });
    }

    logoinit();
    indexphotoinit();
    videoinit();
    newsphotoinit();
    
    function upload(formid, uploadfile, uploadbtn, progress, $li){
        var photofmts = [".gif",".jpg",".jpeg",".png",".bmp"];
        var $formid = $("#" + formid);
        var $uploadbtn = $("#" + uploadbtn);
        var file = $("#" +formid+ " #"+uploadfile).val();
        if (file == "") {
            Dialog.create("","未选择文件：请选择gif,jpg,jpeg,png,bmp格式的图片！",null);
            return false;
        }

        var isRightFmt = false;
        $.each(photofmts, function (index, item) {
            if (StringUtil.endWith(file.toLowerCase(), item)) {
                isRightFmt = true;
                return false;
            }
        });

        if(!isRightFmt){
            Dialog.create("","文件格式不正确：请选择gif,jpg,jpeg,png,bmp格式的图片！",null);
            return false;
        }

        Dialog.create("","确定要上传吗？",function () {
            submit();
        });

        function submit() {
            if (Html5Util.supports_formdata()) {
                html5Upload();
            } else {
                $formid.submit();
            }
        }

        function html5Upload() {
            $uploadbtn.attr("disabled", true);
            var url = $formid.attr("action");
            var success = function(evt) {
                var text = evt.target.responseText;
                if(text != ""){
                    var kemsg = eval("(" + text + ")");
                    if(kemsg.error == "0"){
                        var li = $li;
                        var _a = li.children("a:eq(0)");
                        var _data = eval("(" +kemsg.data+")");
                        var _fileInfo = _data[0];
                        _a.attr({
                            id : _fileInfo.file_id,
                            href: _fileInfo.url
                        });
                        _a.css("background","url(" + _fileInfo.url + ")");
                        _a.children("img:eq(0)").attr("src", _fileInfo.url);
                        Noty.information("上传成功");
                    }else{
                        Noty.error(kemsg.message);
                    }
                }
                $uploadbtn.attr("disabled", false);
            };
            new FileUpload({
                "url" : url,
                "progress" : $("#" + progress),
                "showDefaultSuccess" : false,
                "success" : success
            }).uploadByFormId(formid);
        }
    }
    
    function videoupload() {
        var file = $("#videofile").val();
        if (file == "") {
            Dialog.create("","未选择文件：请选择mp4格式的视频！",null);
            $("#videofile").val("");
            return false;
        }

        if(!StringUtil.endWith(file.toLowerCase(), ".mp4")){
            Dialog.create("","文件格式不正确：请选择mp4格式的视频！",null);
            $("#videofile").val("");
            return false;
        }

        Dialog.create("","确定要上传吗？",function () {
            if (Html5Util.supports_formdata()) {
                new FileUpload({
                    "url" : $("#videoForm").attr("action"),
                    "progress" : $("#progress2"),
                    "showDefaultSuccess" : false,
                    "success" : function(evt){
                        var text = evt.target.responseText;
                        $("#videofile").val("");
                        if(text != "") {
                            var kemsg = eval("(" + text + ")");
                            if (kemsg.error == "0") {//上传成功后删除
                                var $videoinfo = $("#videoinfo");
                                var fileId = $videoinfo.attr("file_id");
                                if(fileId != ""){
                                    tableSupport.remove(mgr_path + "/index/deletefile", {
                                        file_id : fileId
                                    }, function (obj) {
                                        if(obj.status == "0"){//删除成功
                                            Noty.information("更换成功");
                                        }
                                    });
                                }

                                var _data = eval("(" +kemsg.data+")");
                                var _fileInfo = _data[0];
                                $videoinfo.attr("file_id", _fileInfo.file_id);
                                $videoinfo.attr("src", _fileInfo.url);
                                // Noty.information("上传成功");
                            } else {
                                Noty.error(kemsg.message);
                            }
                        }
                    }
                }).uploadByFormId("videoForm");
            } else {
                $("#videoForm").submit();
            }
        });
    }
};