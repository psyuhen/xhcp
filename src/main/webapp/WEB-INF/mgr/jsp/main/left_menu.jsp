<!-- left menu starts -->
<div id="left_menu" class="col-sm-2 col-lg-2" >
	<div class="row">
		<div class="input-group col-lg-12">
			<input placeholder="<spring:message code='text_search' />" class="form-control" name="query" id="searchMenuTree" type="text">
			<span class="input-group-addon" style="cursor: pointer;" title="<spring:message code='text_search' />"><i class="glyphicon glyphicon-search red" id="searching"></i></span>
		</div>
	</div>
	<div class="row">
		<div id="menu_list" class="col-lg-12" style="padding: 0px;overflow: auto;font-size: 10px;">
			<div class="sidebar-nav">
				<div class="nav-canvas">
					<div class="nav-sm nav nav-stacked"></div>
					<ul class="nav nav-pills nav-stacked main-menu" id="Module">
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!--/span-->
<!-- left menu ends -->

<script src="${ctx}/js/html5/Html5Util.${__min}js"></script>
<script src="${ctx}/js/common/json.${__min}js"></script>
<script src="${ctx}/js/common/DateUtil.${__min}js"></script>
<script src="${ctx}/js/common/MenuTree.${__min}js"></script>
<script>
	$(document).ready(function(){
		var menuTree = new MenuTree({
			"module_id": "${param.module_id}",
			"account_id": "${sessionScope.user.account_id}",
			"leaf_menu": "${sessionScope.LeafMenu}"
		});
		menuTree.findTree();
	});
</script>