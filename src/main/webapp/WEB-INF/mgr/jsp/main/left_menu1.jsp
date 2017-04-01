<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- left menu starts -->
<div id="left_menu" class="col-sm-2 col-lg-2" >
	<div class="row">
		<div class="well col-md-12 left login-box">
			<form class="form-horizontal" action="index.html" method="post">
				<fieldset>
					<div class="input-group input-group-lg">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
						<input type="text" class="form-control" placeholder="请输入用户名">
					</div>
					<div class="clearfix"></div><br>

					<div class="input-group input-group-lg">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
						<input type="password" class="form-control" placeholder="请输入密码">
					</div>
					<div class="clearfix"></div>

					<p class="col-md-5">
						<button type="submit" class="btn btn-primary btn-sm">
							<i class="glyphicon glyphicon-arrow-right"></i>登录
						</button>
					</p>
					<p class="col-md-5">
						<a href="${ctx}/mgr/user/add?module_id=UserMgr" target="_blank" class="btn btn-primary btn-sm">
							<i class="glyphicon glyphicon-plus"></i>
							注册
						</a>
					</p>
				</fieldset>
			</form>
		</div>
		<!--/span-->
	</div>
	<div class="row">
		<table class="table" border="1" cellspacing="0" style="border-collapse:collapse; color:#82662C; font-family:'宋体'">
			<thead>
				<tr>
					<td style="white-space: nowrap">会员级别</td>
					<td style="white-space: nowrap">折扣优惠</td>
					<td>配送方式</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>贵宾会员</td>
					<td>9折</td>
					<td rowspan="3">凡一次性购物300元以上，免运费。</td>
				</tr>
				<tr>
					<td>黄金会员</td>
					<td>8.5折</td>
				</tr>
				<tr>
					<td>钻石会员</td>
					<td>8折</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="row">
		<div class="col-md-12">
			<a href="#" title="点我注册" target="_blank">
				<img src="${ctx}/css/images/img_user2.jpg" border="0" />
			</a>
		</div>
	</div>
	<div class="row">
		<ul class="dashboard-list">
			<li>
				<a href="#" title='新会区人大常委会工作组莅临新宝堂调研新会陈皮产业发展'>
					新会区人大常委会工作组莅临
				</a>
			</li>
			<li>
				<a href="#" title='《陈皮世家》连载：广东省人大立法保护新会陈皮产业'>
					《陈皮世家》连载：广东省人
				</a>
			</li>
			<li>
				<a href="#" title='柑花盛放，许您一园的春暖花开···'>
					柑花盛放，许您一园的春暖花
				</a>
			</li>
			<li>
				<a href="#" title='广东省文化厅厅长方健宏调研新会陈皮非物质文化遗产保护工作'>
					广东省文化厅厅长方健宏调研
				</a>
			</li>
			<li>
				<a href="#" title='新会区副区长张斌为新宝堂新会柑陈皮酵素项目发展加油鼓劲'>
					新会区副区长张斌为新宝堂新
				</a>
			</li>
			<li>
				<a href="#" title='《陈皮世家》连载：新宝堂获首届“新会陈皮皇”称号【61集】(红包、语音都有哦！)'>
					《陈皮世家》连载：新宝堂获
				</a>
			</li>
		</ul>
	</div>
	<div class="row">
	</div>
</div>
<!--/span-->
<!-- left menu ends -->