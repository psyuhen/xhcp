/**
 * Created by sam.pan on 2017/3/23.
 */
function getVal(inputid, tipid, iconid, tipinfo) {
    var $input = $("#" + inputid);
    var $inputval = $input.val();

    if($inputval == ''){
        $input.removeClass("yes").addClass("no");
        $("#" + tipid).html(tipinfo);
        $("#" + iconid).show(500);

        return "";
    }else{
        $input.removeClass("no").addClass("yes");
        $("#" + tipid).html("");
        $("#" + iconid).hide(500);

        return $inputval;
    }
}

function check_login(){
    var errflag_login = 0;
    var username_login = getVal("username_login", "username_login_info", "username_login_icon", "请输入手机号");
    if(username_login==''){
        errflag_login += 1;
    }

    var password_login = getVal("password_login", "password_login_info", "password_login_icon", "请输入密码");
    if(password_login==''){
        errflag_login += 1;
    }

    var validate_code = getVal("validate_code", "validate_code_info", "validate_code_icon", "请输入验证码");;
    if(validate_code ==''){
        errflag_login += 1;
    }

    if(errflag_login > 0){
        return false;
    }

    var obj = {};
    obj.mobile = username_login;
    obj.account_id = username_login;
    obj.account_password = password_login;
    obj.validate_code = validate_code;
    obj.blackUrl = $("#blackUrl").val();

    $.post(ctx+"/login.do",obj, function (resp) {
        if(resp.status == "0"){
            if(StringUtil.isBlank(resp.dataObj)){
                window.location.href = ctx + "/usercenter.html";
            }else{
                window.location.href = resp.dataObj;
            }
        }else{
            $("#validateCode").click();
            $("#username_login").removeClass("yes").addClass("no");
            $("#password_login").removeClass("yes").addClass("no");
            $("#username_login_info").html("");
            $("#password_login_icon").show(500);
            alert(resp.desc);
            $("#password_login_info").html(resp.desc);
            return false;
        }

    });
    return false;
}


function check_mobile(){
    var mobile_phone = $("#mobile").val();
    var isMobile=/^(?:13\d|15\d|18\d|17\d|14\d)\d{5}(\d{3}|\*{3})$/;
    if(!isMobile.test(mobile_phone)){
        $("#mobile").removeClass("yes").addClass("no");
        $("#mobile_phone_info").html("请输入正确的手机号！").show(500);
        //alert("请输入正确的手机号！");
    }
    else{
        $("#mobile").removeClass("no").addClass("yes");
        $("#mobile_phone_info").html("").hide(500);
        $.post(ctx + "/checkmobile.do", {"mobile" : mobile_phone}, function (resp) {
            if(resp.status != "0"){
                alert(resp.desc);
            }
        });
    }
}

function check_password(){
    var $password = $("#account_password");
    var $password_info = $("#password_info");

    var password = $password.val();
    if(password == ""){
        $password.removeClass("yes").addClass("no");
        $password_info.html("请输入密码").show(500);
    }else{
        if(password.length < 6 || password.length > 20){
            $password.removeClass("yes").addClass("no");
            $password_info.html("密码不少于6位").show(500);;
        }else{
            password = password.replace(/[^a-zA-Z0-9_]/g, '-');
            if(password.indexOf('-') != -1){
                $password.removeClass("yes").addClass("no");
                $password_info.html("密码中含非法字符").show(500);;
            }else{
                $password.removeClass("no").addClass("yes");
                $password_info.html("输入密码有效").show(500);;
            }
        }
    }

    var $confirmPassword = $("#password_confirm");
    var confirm_password = $confirmPassword.val();

    if(password != confirm_password && confirm_password!=''){
        $confirmPassword.removeClass("yes").addClass("no");
        $("#password_confirm_info").html("输入密码不同").show(500);;
    }else{
        if(confirm_password!=''){
            $confirmPassword.removeClass("no").addClass("yes");
            $("#password_confirm_info").html("输入密码有效").show(500);
        }
    }
}

function check_reginfo(){
    var mobile_phone = $("#mobile").val();
    var validate_code = $("#validate_code").val();
    var password = $("#account_password").val();
    var password_confirm = $("#password_confirm").val();

    var obj = {};
    obj.mobile = mobile_phone;
    obj.account_password = password;
    obj.validate_code = validate_code;

    $.post(ctx + "/register.do",obj,function (resp) {
        alert(resp.desc);
    });
    return false;
}

function change_password(){
    var errflag_info = 0;
    var old_password = $("#old_password").val();
    var new_password = $("#new_password").val();
    var comfirm_password = $("#comfirm_password").val();


    if(old_password.length < 6){
        $("#old_password").addClass("no");
        errflag_info += 1;
    }
    else{ $("#old_password").removeClass("no");}


    if(new_password.length < 6){
        $("#new_password").addClass("no");
        errflag_info += 1;
    }
    else{$("#new_password").removeClass("no");}

    if(comfirm_password.length < 6){
        $("#comfirm_password").addClass("no");
        errflag_info += 1;
    }
    else{
        $("#comfirm_password").removeClass("no");
    }

    if(new_password != comfirm_password){
        errflag_info += 1;
        $("#comfirm_password").addClass("no");
        alert("两次输入的密码不一致！");
    }

    if(errflag_info > 0){
        return false;
    }

    var obj = {"old_password": old_password,"account_password": new_password}
    $.post(ctx + "/changepwd.do", obj, function(resp){

        if(resp.status == "0"){
            $(':input').not(':hidden,:button').val('');
        }
        $("#response_info").html(resp.desc);

    });

    return false;
}