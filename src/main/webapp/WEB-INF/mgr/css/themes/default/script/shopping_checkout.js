/**
 * Created by pansen on 2017/4/8.
 */

function check_address(islist){
    var consignee = $("#consignee").val();
    var address = $("#address").val();

    var zipcode = $("#zipcode").val();
    var mobile = $("#mobile").val();
    var province = $("#province").val();
    var city = $("#city").val();
    var district = $("#district").val();
    //var district = $("#district").val();

    var errflag = 0;

    if(consignee.length < 2){$("#consignee").removeClass("yes").addClass("no");errflag += 1;}
    else{$("#consignee").removeClass("no").addClass("yes");}

    if(province==''){$("#province").removeClass("yes").addClass("no");errflag += 1;}
    else{$("#province").removeClass("no").addClass("yes");}

    if(district==''){$("#district").removeClass("yes").addClass("no");errflag += 1;}
    else{$("#district").removeClass("no").addClass("yes");}

    if(address.length < 2){$("#address").removeClass("yes").addClass("no");errflag += 1;}
    else{$("#address").removeClass("no").addClass("yes");}


    var isMobile=/^(?:13\d|15\d|18\d|17\d|14\d)\d{5}(\d{3}|\*{3})$/;
    if(!isMobile.test(mobile)){
        $("#mobile").removeClass("yes").addClass("no");errflag += 1;
    }
    else{$("#mobile").removeClass("no").addClass("yes");}

    if(errflag > 0){
        return false;
    }

    var obj = {};
    obj.province_code = province;
    obj.city_code = city;
    obj.town_code = district;
    obj.address = address;
    obj.mobile = mobile;
    obj.user_name = consignee;

    var checked = $(":checkbox").is(":checked");
    obj.default_addr = checked ? "0" : "1";

    $.post(ctx + "/freqaddr/addFreqAddr", obj, function(resp){
        if(resp.status == "0"){
            window.location.reload();
        }
    });

    return false;
}


function ChangeProvince(provinceId){
    if(provinceId != ''){
        $.post(ctx + "/city/queryBy", {"province_code":provinceId}, function(data){
            var $city = $("#city");
            $city.val("");
            $city.html('<option value="">请选择市</opton>');
            if(data == null || data == undefined){
                return;
            }

            for(var i=0,len=data.length;i<len;i++){
                $city.append('<option value="'+data[i].city_code+'">' + data[i].city_name +'</opton>');
            }
            $("#district").html('<option value="">区/县</opton>');
        });
    }else{
        $("#city").html('<option value="">请选择市</opton>');
        $("#district").html('<option value="">请选择县</opton>');
    }
}

function ChangeCity(cityId){
    if(cityId != ''){
        $.post(ctx + "/citytown/queryBy", {"city_code":cityId}, function(data){
            var $district = $("#district");
            $district.val("");
            $district.html('<option value="">请选择县</opton>');

            if(data == null || data == undefined){
                return;
            }
            for(var i=0,len=data.length;i<len;i++){
                $district.append('<option value="'+data[i].town_code+'">' + data[i].town_name +'</opton>');
            }
        });
    }
}

function checkorder(){
    if($('input:radio[name="address_id"]').is(":checked")==false){
        $("#alertinfo").html('请选择收货地址');
        $("#alertinfo").show(300);
        return false;
    }
    return true;
}