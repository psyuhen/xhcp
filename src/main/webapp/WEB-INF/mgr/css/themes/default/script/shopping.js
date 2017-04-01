function add_cart(proid){
if(!isNaN(proid)){ 
 	var pro_number = $("#count").val();
 	var specificationsid = $("#specificationsid").val();
  
	$.post('ajax_shopping.php', {act:'add_cart', proid: proid,pro_number: pro_number,specificationsid: specificationsid}, function(data, textStatus){
				window.location.href="shopping.php"; 
					     /*$("#cartcontent").html(data);
						 $("#cartcontent").removeClass().addClass("add-shopping-cart");
						 $('#cartcontent').show(500);
						  $.getScript("/themes/default/jquery/shopping.js");*/
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



function delete_cart(cart_id){
if(!isNaN(cart_id)){ 
	$.post('ajax_shopping.php', {act:'delete_cart', cart_id: cart_id}, function(data, textStatus){
							 $('#cart_list'+cart_id).remove();
							 if(data=='' || data==0){
							  //window.location.href="products.php"; 
							  location.reload(); 
							 }
							 else{
								$('#total').html(data);	 
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
	 var after_no = parseInt($('#count'+cid).val())-1;
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

function count_add(cid){
	var after_no = parseInt($('#count'+cid).val())+1;
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








