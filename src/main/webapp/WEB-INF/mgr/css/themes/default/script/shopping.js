function add_cart(proid){
if(!isNaN(proid)){
 	var pro_number = $("#count").val();
 	var specificationsid = $("#specificationsid").val();
  
	$.post(ctx +'/product/car/add', {"merch_id": proid,"buy_num": pro_number}, function(resp){
			if(resp.status == "0"){
				window.location.href=ctx +"/shopping.html";
			}
		});
	}	
}


function change_price(specificationsid){
if(!isNaN(specificationsid)){ 
	$.post('ajax_shopping.php', {act:'change_price', specificationsid: specificationsid}, function(data, textStatus){
					     $("#price").html(data);
						 $("#price_value").val(data);
						 $("#specificationsid").val(specificationsid);
            });
	}	
}






function add_collection(proid){
if(!isNaN(proid)){ 
	  $.ajax({ 
	 	  timeout:5000,
		  type: 'POST', 
		  url: 'ajax_shopping.php',
		  data:{act:'add_collection',proid:proid},
		  dataType: 'json', 
		  cache: false,
		  async:false, 
		  success:function(json){ 
		  $("#cartcontent").html(json.response_str);
						 $("#cartcontent").removeClass().addClass("add-shopping-collect");
						 if(json.islogin==1){$("#collection_img").attr("src","themes/default/images/b_red.png");}
						 $('#cartcontent').fadeIn();
						  $.getScript("/themes/default/jquery/shopping.js");
		  },
	  });
	}	
}


$('.addcart_close').bind('click',function(){
   $('#cartcontent').hide(500);
})

$('.keep-shop').bind('click',function(){
   $('#cartcontent').hide(500);
})




function select_checkbox() {
	var strid='';
var chk_value =[];    
  $('input[name="favorite"]:checked').each(function(){    
   strid += $(this).val()+","    
  });
   if(strid == ''){alert('请选择');}
   else{
			$.post('ajax_shopping.php', {act:'delete_favorite',favorite_id:strid}, function(data, textStatus){
				if(data==1){location.reload();}
				else{alert('删除失败，您是否已登录');}
            });  
   } 
}



function delete_cart(cart_id,merch_id){
if(!isNaN(cart_id)){ 
	$.post(ctx + '/product/car/delete', {"car_id":cart_id, "merch_id":merch_id}, function(resp){
			if(resp.status == "0"){
				location.reload();
			}
		});
	}	
}


function delete_shoppingcart() {
var strid='';
var chk_value =[];    
  $('input[name="shopcart"]:checked').each(function(){    
   strid += $(this).val()+","    
  });
   if(strid == ''){alert('请选择');}
   else{
			$.post('ajax_shopping.php', {act:'delete_shoppingcart',cartid:strid}, function(data, textStatus){
				if(data==1){location.reload();}
				else{alert('删除失败，您是否已登录');}
            });  
   } 
}


function cart_to_favorite(pid){
if(!isNaN(pid)){ 
	$.post('ajax_shopping.php', {act:'cart_to_favorite', pid: pid}, function(data, textStatus){
							 if(data==1){
							  alert('成功添加到收藏夹');
							 }
							 else{alert('您已经收藏过此宝贝');}
            });
	}	
}


function count_minus(cid){
    var $cid = $('#count'+cid);
    var after_no = parseInt($cid.val())-1;
	 var kprice = parseFloat($('#kprice'+cid).html());
	 if(isNaN(after_no) || after_no == 0){
		 	after_no = 1;
	 }
	 $.post(ctx+'/product/car/addbuynum', {"merch_id":cid,"buy_num":after_no}, function(resp){
         if(resp.status == "0"){
             $cid.val(after_no);
             $('#subtotal'+cid).html(after_no * kprice);
             $('#total').html('￥'+(resp.dataObj).toFixed(2));
         }
     });
}

function count_add(cid){
	 var $cid = $('#count'+cid);
	 var after_no = parseInt($cid.val())+1;
	 var kprice = parseFloat($('#kprice'+cid).html());
	 if(isNaN(after_no) || after_no == 0){
			after_no = 1;
	 }
	 $.post(ctx+'/product/car/addbuynum', {"merch_id":cid,"buy_num":after_no}, function(resp){
	 	if(resp.status == "0"){
	 		$cid.val(after_no);
            $('#subtotal'+cid).html(after_no * kprice);
            $('#total').html('￥'+(resp.dataObj).toFixed(2));
        }
     });
}

function count_input(cid){
  var after_no = parseInt($('#count'+cid).val());
  var kprice = parseFloat($('#kprice'+cid).html());
  if(isNaN(after_no) || after_no == 0){
	  		after_no = 1;
			$('#count'+cid).val(1)
	 }
	  else{
	 	$('#count'+cid).val(after_no);
	 }
    $.post('ajax_shopping.php', {act:'change_countno',cid:cid,prono:after_no}, function(data, textStatus){
		$('#subtotal'+cid).html(after_no * kprice);
		 $('#total').html('￥'+data); 
     });
}








