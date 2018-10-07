<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<html>
	<head>
		<title>e众生活物业后台管理系统</title>
	</head>
	<body class="easyui-layout">
	    <!-- 头部标题 -->
		<div data-options="region:'north',border:false" style="height:60px; padding:5px; background:#ACD6FF"> 
			<span class="northTitle" style="margin-left: 80px;color:#333;font-size:25px">e众生活物业后台管理系统</span>
		    <span class="loginInfo" style="margin-left: 500px;">登录用户：</span>
		    <span style="color:red;">admin&nbsp;&nbsp;</span>
		   	姓名：<span style="color:red;">管理员&nbsp;&nbsp;</span>
		          角色：<span style="color:red;">系统管理员  </span>
		    <a id="btn" href="javascript:void(0)" onclick="logout()">退出</a>   
		</div>
		
	    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
			<div class="easyui-accordion"  data-options="iconCls:'icon-reload',fit:true">
				<div title="房产管理" data-options="iconCls:'fa fa-home fa-fw fa-lg'">
					<ul style="padding: 0px;margin:0px;" >
					<li style="padding: 10px;"><a href="${proPath }/base/goURL/management/list.action" title="管理处"
						style="text-decoration: none;display: block;">管理处</a>
					</li>
					<li style="padding: 10px;"><a href="${proPath }/base/goURL/building/list" title="楼宇管理"
						style="text-decoration: none;display: block;">楼宇管理</a>
					</li>
					<li style="padding: 10px;"><a href="${proPath }/base/goURL/room/list" title="房间档案"
						style="text-decoration: none;display: block;">房间档案</a>
					</li>
					</ul>
				</div>		
					
				<div title="停车管理" data-options="iconCls:'fa fa-car fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 10px;"><a href="${proPath }/base/goURL/berth/berthmanage" title="车位区域档案管理"
							style="text-decoration: none;display: block;">车位区域档案管理</a>
						</li>
						<li style="padding: 10px;"><a href="${proPath }/base/goURL/platemanage/platecost" title="车位收费设置"
							style="text-decoration: none;display: block;">车位收费设置</a>
						</li>
						<li style="padding: 10px;"><a href="${proPath }/base/goURL/platemanage/parkrecord" title="停车记录"
							style="text-decoration: none;display: block;">停车记录</a>
						</li>
					</ul>
				</div>
				
				<div title="抄表管理" data-options="iconCls:'fa fa-calendar-check-o fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
					<%--	<li style="padding: 6px;"><a href="${proPath}/base/goURL/grid/list" title="表计类别设置"
							style="text-decoration: none;display: block;">表计类别设置</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath}/base/goURLT/grid/room/list" title="房间表计设置"
							style="text-decoration: none;display: block;">房间表计设置</a>
						</li>--%>
						<li style="padding: 6px;"><a href="${proPath}/base/goURL/grid/record_list" title="抄表录入管理"
							style="text-decoration: none;display: block;">抄表录入管理</a>
						</li>
					</ul>
				</div>		
				
				<div title="收费管理" data-options="iconCls:'fa fa-calculator fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/charge/item_list" title="收费项目设置"
							style="text-decoration: none;display: block;">收费项目设置</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/charge/room_charge_list" title="房间收费设置"
							style="text-decoration: none;display: block;">房间收费设置</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/charge/recordList" title="应收费记录"
							style="text-decoration: none;display: block;">应收费记录</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/charge/Review" title="已审核页面"
							style="text-decoration: none;display: block;">已审核记录</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/charge/recordList2" title="缴费管理"
							style="text-decoration: none;display: block;">缴费管理</a>
						</li>
					</ul>
				</div>		
					
				<div title="客户管理" data-options="iconCls:'fa fa-address-book fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURL/customer/archives_list" title="房价客户档案"
							style="text-decoration: none;display: block;">房间客户档案</a>
						</li>
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURL/customer/emigrate" title="迁入迁出管理"
							style="text-decoration: none;display: block;">迁入迁出管理</a>
						</li>
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURLT/customer/mobile/list" title="App用户管理"
							style="text-decoration: none;display: block;">APP用户管理</a>
						</li>
					</ul>
				</div>	
				
				<div title="维修管理" data-options="iconCls:'fa fa-wrench fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/repair/repair_record" title="报单登记管理"
							style="text-decoration: none;display: block;">报单登记管理</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/repair/repair_count" title="维修数量统计"
							style="text-decoration: none;display: block;">维修数量统计</a>
						</li>
					</ul>
				</div>	
					
				<div title="家政服务" data-options="iconCls:'fa fa-server fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/housekeeping/register" title="家政服务公司列表"
							style="text-decoration: none;display: block;">家政服务公司列表</a>
						</li>
					</ul>
				</div>
					
				<div title="家教管理" data-options="iconCls:'fa fa-drivers-license-o fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/teacher/list" title="家教审核"
							style="text-decoration: none;display: block;">家教审核</a>
						</li>			
					</ul>
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${proPath }/base/goURLT/teacher/fee/list" title="家教费交易记录"
							style="text-decoration: none;display: block;">家教费交易记录</a>
						</li>			
					</ul>
				</div>
						
				<div title="公益基金" data-options="iconCls:'fa fa-btc fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURL/benefit/list" title="公益基金交易明细"
							style="text-decoration: none;display: block;">公益基金交易明细</a>
						</li>
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURLT/benefit/apply/list" title="公益基金申请记录"
							style="text-decoration: none;display: block;">公益基金申请记录</a>
						</li>				
					</ul>
				</div>
						
				<div title="租赁管理" data-options="iconCls:'fa fa-university fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURL/rent/list" title="出租登记"
							style="text-decoration: none;display: block;">出租登记</a>
						</li>		
					</ul>
				</div>	
				
				<div title="商城管理" data-options="iconCls:'fa fa-shopping-cart fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${proPath }/base/goURL/shopping-mall/list" title="商品管理"
							style="text-decoration: none;display: block;">商品管理</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath }/base/goURLT/shopping-mall/order/list" title="订单管理"
							style="text-decoration: none;display: block;">订单管理</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath }/base/goURLT/shopping-mall/merchant/list" title="商家管理"
							style="text-decoration: none;display: block;">商家管理</a>
						</li>
						<li style="padding: 6px;"><a href="${proPath }/base/goURLT/shopping-mall/slide-show/list" title="商城首页轮播图"
							style="text-decoration: none;display: block;">商城首页轮播图</a>
						</li>
					</ul>
				</div>	
					
				<div title="消息中心" data-options="iconCls:'fa fa-commenting-o fa-fw fa-lg'">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURL/news/list" title="个人消息"
							style="text-decoration: none;display: block;">个人消息</a>
						</li>
					</ul>
				</div>
				
				<div title="公告管理" data-options="iconCls:'fa fa-comments-o fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURL/notice/list" title="公告管理"
							style="text-decoration: none;display: block;">公告管理</a>
						</li>
					</ul>
				</div>	
				
				<div title="管理员设置" data-options="iconCls:'fa fa-user fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/ad/list" title="管理员设置"
							style="text-decoration: none;display: block;">管理员设置</a>
						</li>
					</ul>
					
					<shiro:hasPermission name="p112:p113">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURLT/admin-set/role/list" title="角色设置"
							style="text-decoration: none;display: block;">角色设置</a>
						</li>
					</ul>
					</shiro:hasPermission>
				</div>
				
				<div title="门锁管理" data-options="iconCls:'fa fa-lock fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURL/deviceLock/list" title="门锁列表"
							style="text-decoration: none;display: block;">门锁列表</a>
						</li>
					</ul>
				</div>	
				
				<%-- <div title="访客管理" data-options="iconCls:'fa fa-comments-o fa-fw fa-lg',">
					<ul style="padding: 0px;margin:0px;" >
						<li style="padding: 6px;"><a href="${pageContext.request.contextPath}/base/goURL/visitor/list" title="访客记录"
							style="text-decoration: none;display: block;">访客记录</a>
						</li>
					</ul>
				</div> --%>					
			</div>
	    </div>
	    <div data-options="region:'center',title:''">
	    	<div id="tabs" class="easyui-tabs">
			    <div title="首页" >
			     	
			    </div>
			</div>
	    </div>
	    <div id="win">
	    
	    </div>
	    <!-- 页脚信息 -->
		<div data-options="region:'south',border:false" style="height:20px; background:#F3F3F3; padding:2px; vertical-align:middle;">
			<span id="sysVersion">系统版本：V1.0</span>
		    <span id="nowTime"></span>
		</div>
	
		<script type="text/javascript" src="/ezsh/js/main/main.js"></script>
	</body>
</html>