<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
	<head>
		<title>首页</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	    <meta content="yes" name="apple-mobile-web-app-capable"/>
	    <meta content="black" name="apple-mobile-web-app-status-bar-style"/>
	    <meta content="telephone=no" name="format-detection"/>
		<link rel="stylesheet" href="/ezsh/wechat/menu-icon/iconfont.css">    
		<link rel="stylesheet" href="/ezsh/assets/css/amazeui.min.css"/>
		<link rel="stylesheet" type="text/css" href="/ezsh/address-master/dist/amazeui.address.css" />
		<style type="text/css">
		/* 清除浏览器差异，保持样式统一 */
		* {
		    margin: 0;
		    padding: 0;
		    border:0;
		    -webkit-font-smoothing: antialiased;
		}
		
		.ezsh_mian { 
			max-width: 640px; 
			margin: 0 auto; 
			background: #ececec; 
			position: relative;
		}
		
		/* 菜单栏导航 */
		.ezsh_circle_nav {
		  	padding:25px 10px 5px;
		  	max-width: 100%;  
		 }
		.ezsh_circle_nav_list { 
			max-width: 100%; 
			overflow: hidden;
		}
		.ezsh_circle_nav_list li { 
			width: 25%; 
			margin-bottom: 15px;
		}
		.ezsh_circle_nav_list a {
			border-radius: 50%; 
			width: 55px; 
			height: 55px; 
			display: block; 
			margin: 0 auto; 
			/* color: #fff; */ 
			text-align: center; 
			line-height: 55px; 
			font-size: 35px;
			-webkit-transition: all 0.2s ease;
			transition: all 0.2s ease;
		}
		
		.ezsh_circle_nav_list span { 
			display: block; 
			width: 100%; 
			text-align: center; 
			padding-top: 5px; 
			font-size: 14px;
		}
		
		.ezsh_news_content_main {
			max-width: 100%;
		    background: #fff;
		    padding: 10px 20px;
		    -webkit-box-shadow: 0 0 3px rgba(100,100,100,.1);
		    box-shadow: 0 0 3px rgba(100,100,100,.1);
		}
		.am-list-news-title {
		    border-left: 3px solid #fe9700;
		    color: #fe9700;
		}
		
		.am-list-news-grade {
		    border-right: 3px solid #fe9700;
		    color: #fe9700;
		}
		
		.ezsh_show_more_list { 
			width: 100%; 
			left: 0; 
			height: 60%;
			background:rgba(48,52,53,.9);   
			position: fixed; 
			bottom: -9999px; 
			z-index: 9999;
			-webkit-transition: all .5s;
	        transition: all 0.5s;
        }
        
        .ezsh_show_more_list_show {
			bottom: 0;
			left: 0;
			animation: more_list 0.5s;
			-webkit-animation: more_list 0.5s;
		}
	
		.ezsh_show_more_list .ezsh_show_more_list_block ul {
			color:#fff;
		}
		.ezsh_show_more_list .ezsh_show_more_list_block ul>li>a {
			margin-left:15px;
		}
		.ezsh_show_more_list .ezsh_show_more_list_block .ezsh_more_list_block_name {
			color:#fff;
			font-size:20px;
			text-align:center;
		}
		
		/* 样式重新覆盖 */
		.iconfont {
		  font-family:"iconfont" !important;
		  font-size:36px;
		  font-style:normal;
		  -webkit-font-smoothing: antialiased;
		  -moz-osx-font-smoothing: grayscale;
		}
		#search_condition button{
			border-color:#fff;
		}
		#search_condition button>span{
			text-align:center;
		}
		</style>
	</head>
	<body>
		<div class="ezsh_mian" >
			<header data-am-widget="header" class="am-header am-header-default am-header-fixed">
				<div class="am-form-group" style="position:absolute;margin:10px 0px 0px 50px;">
			    	<input class="am-form-field am-round" id="keyWord" placeholder="关键字搜索" style="height:28px;width:80%;"/>
			    </div>
			
				<div class="am-header-right am-header-nav" style="position:absolute;margin-right:20px;">
				    <a href="javaScript:search()" >
				    	<i style="font-size:26px;"class="iconfont icon-rencaijiaohuantiaojiansousuo"></i>
				    </a>
				</div>
			</header>
		
			<div id="search_condition" style="position:fixed;top: 49px;left: 0;right: 0; width: 100%;z-index: 1010;    background:#fff;">
				<div id="address1" style="display:inline-block;width:32%;">
				<select id="address1-form" data-am-selected="{btnWidth: '100%',maxHeight:0}">
					<option id="district" value="东城区">东城区</option>
					<option id="city" value="北京市">北京市</option>
					<option id="prov" value="北京">北京</option>
				</select>
				</div>
				<div style="display:inline-block;width:32%;">
					<select id="tea_subject" data-am-selected="{maxHeight:0,btnWidth: '100%'}">
						
					</select>
				</div>
				<div style="display:inline-block;width:32%;">
					<select id="tea_grade" data-am-selected="{maxHeight:0,btnWidth: '100%'}">
						
					</select>
				</div>
			</div>
			
			<!-- ezsh_news_content_main start -->
			<div class="ezsh_news_content_main">
			
				<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
					<div class="am-list-news-bd" style="margin-top:50px;">
						<ul id="listContent" class="am-list" >
						  
					    </ul>
					</div>
					<div style="margin:0px 0px 0px 35%;">
						<a href="javaScript:getMore()" class="fa-cubes">
						    <span id="getMore" style="color:#757575;">点击加载更多</span>
						</a>
					</div>
				</div>
				
			</div>
			<!-- ezsh_news_content_main end -->
			
			
			<div data-am-widget="gotop" class="am-gotop am-gotop-fixed" >
			    <a href="#top" title="">
	          	<i class="iconfont icon-dingbu"></i>
	    		</a>
			</div>
			
			<!-- foot navbar start -->
			<div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default">
				<ul class="am-navbar-nav am-cf am-avg-sm-4">
				    <li>
				      <a href="javascript:back()" style="margin-top:10px;">
				          <i style="color:#FFFFFF;font-size:20px;" class="iconfont icon-6" ></i>
				          <span class="am-navbar-label">返回</span>
				      </a>
				    </li>
				    
				    <li>
				      <a href="/ezsh/routeW/center?identify=wechat" style="margin-top:10px;">
				          <i style="color:#FFFFFF;font-size:20px;" class="iconfont icon-gerenzhongxin"></i>
				          <span class="am-navbar-label">个人中心</span>
				      </a>
				    </li>
				</ul>
			</div>
			<!-- foot navbar end-->	
		</div>
		<input type="hidden" id="hidden-subject" value="${subject}">
  	</body>
  	<script type="text/javascript">
 	function setLi(item){
		var Li = '<li class="am-g am-list-item-desced">'
	    	+'<div>'
	            +'<a href="###">'
	            +'<i style="color:#6C7B8B;font-size:16px;" class="iconfont icon-lesson1"></i>'
			    +'<span style="color:#757575;">'+item.teacherLesson+'</span>'
			    +'<span class="am-list-news-grade am-fr">《'+item.teacherGrade+'》</span>'
			    +'</a>'
	        +'</div>'
	        +'<div class="am-list-item-hd">'
	        	+'<span>家长：</span>'+item.parentName+'</span>'
	        	+'<span class="am-fr">联系电话：'+item.linkPhone+'</span>'
	        +'</div>'
	        +'<div class="am-list-item-text">'
		        +'<span>期望老师：</span>'+item.expectSex+'</span>'
	        	+'<span style="display:inline-block;margin-left:5%;">课时费：'+item.lessonCharge+'元/h</span>'
	        	+'<span class="am-fr">每周次数：'+item.timesWeek+'</span>'
	        +'</div>'
	        +'<div class="am-list-item-text">家教地址：'+item.placeProvince+item.placeCity+item.placeDistrict+item.managerName+'</div>'
	        +'<div class="am-list-item-text">要求说明：'+item.otherExplain+'</div>'
	        +'<div class="am-list-item-text">发布时间：'+item.addTime+'</div>'
	        +'</li>';
	    return Li;
	}
  	</script>
	<script src="/ezsh/js/jquery-3.2.1.min.js"></script>
	<script src="/ezsh/assets/js/amazeui.min.js"></script>
	<script src="/ezsh/wechat/teacher_subject/subject.js"></script>
	<script src="/ezsh/js/dist/amazeui.dialog.min.js" charset="utf-8"></script>
	<script type="text/javascript">
	var pageSize=10,startPage=1,totalPage=0;
	var applicationRecord = document.getElementById('listContent');

	function getMore() {
		//getMoreData(1,10);
		if(startPage==totalPage || totalPage==0){
			return;
		} else {
			startPage+=1;
		}
		var dataList;
		$.ajax({  
            type:'post',  
            url:"/ezsh/educateInfoW/select",  
            dataType:'json',  
            data:{startPage:startPage,pageSize:pageSize,
            	teacherLesson:$("#tea_subject").val(),teacherGrade:$("#tea_grade").val(),
            	placeProvince:$("#prov").val(),placeCity:$("#city").val(),
            	placeDistrict:$("#district").val(),otherExplain:$("#keyWord").val()},  
            beforeSend:function(){  
  
            },success:function(data){
                if(data.status == 1){
                	dataList = data.data.familyEducateInfoList;
                	var html = '';
            	    for(var i = 0;i<dataList.length;i++){
            	        html += setLi(dataList[i]);
            	    }
            	    $("#listContent").append(html); 
            	    if(startPage==totalPage){
                		document.getElementById('getMore').innerHTML="暂无更多！";
                	}
                    //成功  
                }else{  
                    //出错   
                }  
            }  
    	});
	}
	
	function search(){
		startPage=1;
		$.ajax({  
            type:'post',  
            url:"/ezsh/educateInfoW/select",  
            dataType:'json',  
            data:{startPage:startPage,pageSize:pageSize,
            	teacherLesson:$("#tea_subject").val(),teacherGrade:$("#tea_grade").val(),
            	placeProvince:$("#prov").val(),placeCity:$("#city").val(),
            	placeDistrict:$("#district").val(),otherExplain:$("#keyWord").val()},  
            beforeSend:function(){  
  
            },success:function(data){
            	if(data.status == 1){
                	dataList = data.data.familyEducateInfoList;
                	var html = '';
                	if(data.data.totalPage>0){
                		for(var i = 0;i<dataList.length;i++){
                	        html += setLi(dataList[i]);
                	    }
                	    applicationRecord.innerHTML = html;
                	    totalPage=data.data.totalPage;
                	    if(startPage==totalPage){
                    		document.getElementById('getMore').innerHTML="暂无更多！";
                    	} else {
                    		document.getElementById('getMore').innerHTML="点击加载更多！";
                    	}
                	} else if(data.data.totalPage==0) {
                		applicationRecord.innerHTML="";
                		document.getElementById('getMore').innerHTML="暂无更多！";
                		AMUI.dialog.alert({
							title: '提示',
							content: "暂无数据",
							onConfirm: function() {
							console.log('close');
   					    	}
   						});
                	} else {
                		document.getElementById('getMore').innerHTML="暂无更多！";
                	}
                    //成功  
                }else{  
                    //出错   
                }   
            }  
    	});
	}
	
	function back(){
		window.history.back();  
	}
	</script>
	<script src="/ezsh/address-master/dist/address.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	$(function() {
		/* document.addEventListener('touchmove', function (e) {
			e.preventDefault();
		}, false); */
		//	自定义输出
		$("#address1").address({
			customOutput: true,
			selectEnd: function(json,address) {
				for(var key in json) {
					$("#address1-form").find("option[id='" + key + "']").val(json[key]);
					document.getElementById(key).innerText=json[key];
				}
			}
		}).on("provTap",function(event,activeli){
			console.log(activeli.text());
		}).on("cityTap",function(event,activeli){
			console.log(activeli.text());
		})
	});
	</script>
</html>
