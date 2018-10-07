<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
	<head>
		<title>首页</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" href="/ezsh/css/wechat/menu-icon/iconfont.css">    
		<link rel="stylesheet" href="/ezsh/assets/css/amazeui.min.css"/>
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
			/* float: left;  */
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
		/* .ezsh_circle_nav_list a:hover { 
			-webkit-box-shadow: inset 0 0 40px rgba(0,0,0,.3); 
			box-shadow: inset 0 0 40px rgba(0,0,0,.3);
			-webkit-transition: all 0.2s ease;
			transition: all 0.2s ease; 
		} */
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
		/* .ezsh_show_more_list .ezsh_show_more_list_block {
			text-align:center;
		} */
		.ezsh_show_more_list .ezsh_show_more_list_block ul {
			color:#fff;
			/* text-align:center; */
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
		  font-size:46px;
		  font-style:normal;
		  -webkit-font-smoothing: antialiased;
		  -moz-osx-font-smoothing: grayscale;
		}
		</style>
	</head>
	<body>
	<div class="ezsh_mian">
		<!-- slider start -->
		<div data-am-widget="slider" class="am-slider am-slider-default" data-am-slider='{&quot;animation&quot;:&quot;slide&quot;,&quot;slideshow&quot;:false}' >
			<ul class="am-slides">
			    <li>
			      	<img src="http://s.amazeui.org/media/i/demos/bing-1.jpg">
			        <div class="am-slider-desc">欢迎来到e众生活智慧社区家教中心！</div> 
			    </li>
			    <li>
			      	<img src="http://s.amazeui.org/media/i/demos/bing-2.jpg">
			        <div class="am-slider-desc">在这里你可以找到喜欢的家教！</div>
			    </li>
			    <li>
			      	<img src="http://s.amazeui.org/media/i/demos/bing-3.jpg">
			        <div class="am-slider-desc">在这里你将拥有更多锻炼的机会！</div>
			       
			    </li>
			    <li>
			      	<img src="http://s.amazeui.org/media/i/demos/bing-4.jpg">
			        <div class="am-slider-desc">在这里你可以展现你的才华！</div>
			       
			    </li>
			</ul>
		</div>
		<!-- slider end -->
		
		<!-- menu nav start -->
		<div class="ezsh_circle_nav">
			<ul class="ezsh_circle_nav_list am-avg-sm-4">
		        <li>
		        	<a href="/ezsh/routeW/jobList?subject=语文" ><i style="color:#F4A460;" class="iconfont icon-yuwen"></i></a>
		        	<span>语文</span>
		        </li>
		        <li><a href="/ezsh/routeW/jobList?subject=数学" ><i style="color:#9ACD32;" class="iconfont icon-shuxue"></i></a>
		        	<span>数学</span>
		        </li>
		        <li><a href="/ezsh/routeW/jobList?subject=英语"><i style="color:#7CCD7C;" class="iconfont icon-yingyu1"></i></a>
		        	<span>英语</span>
		        </li>
		        <li><a href="/ezsh/routeW/jobList?subject=生物"><i style="color:green;" class="iconfont icon-icon26-copy"></i></a>
		        	<span>生物</span>
		        </li>
		        <li><a href="/ezsh/routeW/jobList?subject=地理"><i style="color:#00BFFF;" class="iconfont icon-dili"></i></a>
		        	<span>地理</span>
		        </li>
		        <li><a href="/ezsh/routeW/jobList?subject=物理"><i style="color:#6C7B8B;" class="iconfont icon-wuli"></i></a>
		        	<span>物理</span>
		        </li>
		        <li><a href="/ezsh/routeW/jobList?subject=化学"><i style="color:#00BFFF;" class="iconfont icon-huaxue1"></i></a>
		        	<span>化学</span>
		        </li>
		        <li><a href="javascript:show();" class=""><i style="color:#ADFF2F;" class="iconfont icon-gengduo"></i></a>
		        	<span>更多</span>
		        </li>
	    	</ul>
		</div>
		<!-- menu nav end -->
		
		<!-- ezsh_news_content_main start -->
		<div class="ezsh_news_content_main">
			<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
				<!--列表标题-->
				<div class="am-list-news-hd am-cf">
				   <!--带更多链接-->
				   <a href="/ezsh/routeW/jobList" class="">
				 	   <span class="am-list-news-title am-text-default">最新家教信息</span>
				       <span class="am-list-news-more am-fr">更多 &raquo;</span>
				   </a>
				</div>
				
				<div  class="am-list-news-bd">
					<ul id="am-list-news-bd" class="am-list">
					    <!-- <li class="am-g am-list-item-desced">
					    	<div>
					            <a href="###">
					            <i style="color:#6C7B8B;font-size:16px;" class="iconfont icon-wuli"></i>
							    <span style="color:#757575;">物理</span>
							    <span class="am-list-news-grade am-fr">《一年级》</span>
							    </a>
					        </div>
					        <a href="http://www.douban.com/online/11614662/" class="am-list-item-hd ">我很囧，你保重....晒晒旅行中的那些囧！</a>
					        <div class="am-list-item-text">囧人囧事囧照，人在囧途，越囧越萌。标记《带你出发，陪我回家》http://book.douban.com/subject/25711202/为“想读”“在读”或“读过”，有机会获得此书本活动进行3个月，每月送出三本书。会有不定期bonus！</div>
					    </li>
					    
					    <li class="am-g am-list-item-desced">
					        <div class="pet_list_one_info">
					            <a href="###" class="fa-cubes">
					            
							    <span style="color:#757575;">物理</span>
							    <span class="am-list-news-grade am-fr">《一年级》</span>
							    </a>
					        </div>
					        <a href="http://www.douban.com/online/11614662/" class="am-list-item-hd ">我很囧，你保重....晒晒旅行中的那些囧！</a>
					        <div class="am-list-item-text">囧人囧事囧照，人在囧途，越囧越萌。标记《带你出发，陪我回家》http://book.douban.com/subject/25711202/为“想读”“在读”或“读过”，有机会获得此书本活动进行3个月，每月送出三本书。会有不定期bonus！</div>
					    </li> -->
				    </ul>
				</div>
			</div>
		</div>
		<!-- ezsh_news_content_main end -->
		
		<!-- show more start -->
		<div class="ezsh_show_more_list" >
			<div class="pet_more_close"  style="text-align:right;">
				<a style="font-size:25px;margin-right:10px;" href="javascript:close();">x</a>
			</div>
			<div class="ezsh_show_more_list_block">
				<div class="ezsh_more_list_block_name">普通类</div>
				<ul class="am-avg-sm-4">
			        <li>
			        	<a href="/ezsh/routeW/jobList?subject=政治" class="fa-cubes"><span style="color:blue;">政治</span></a>
			        </li>
			        <li>
			        	<a href="/ezsh/routeW/jobList?subject=历史"><span style="color:blue;">历史</span></a>
			        </li>
		    	</ul>
		    	
		    	<!-- <div class="ezsh_more_list_block_name">艺术类</div>
				<ul class="am-avg-sm-4">
			        <li><a href="" class="fa-cubes"></a><span>钢琴</span></li>
			        <li><a href="" class=""></a><span>吉他</span></li>
		    	</ul>
		    	
		    	<div class="ezsh_more_list_block_name">体育类</div>
				<ul class="am-avg-sm-4">
			        <li><a href="" class="fa-cubes"></a><span>乒乓球</span></li>
			        <li><a href="" class=""></a><span>游泳</span></li>
		    	</ul> -->
			</div>
		</div>
		<!-- show more end -->
		
		<!-- foot navbar start -->
		<div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default">
			<ul class="am-navbar-nav am-cf am-avg-sm-4">
			    <li>
			      <a href="#"  style="margin-top:10px;">
			          <i style="color:#FFFFFF;font-size:20px;" class="iconfont icon-6" ></i>
			          <span class="am-navbar-label">返回</span>
			      </a>
			    </li>

			    <!-- <li data-am-navbar-share>
			      <a href="###" style="margin-top:10px;">
			          <i style="color:#FFFFFF;font-size:20px;" class="iconfont icon-fenxiang"></i>
			          <span class="am-navbar-label">分享</span>
			      </a>
			    </li> -->
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
  	</body>
  	
	<script src="/ezsh/js/jquery-3.2.1.min.js"></script>
	<script src="/ezsh/assets/js/amazeui.min.js"></script>
	<script src="/ezsh/assets/js/app.js"></script>
	<script type="text/javascript">
		function show() {
			$(".ezsh_show_more_list").addClass('ezsh_show_more_list_show');
		}
		function close(){
			$(".ezsh_show_more_list").removeClass('ezsh_show_more_list_show');
		}
	</script>
	<script type="text/javascript">  
	var applicationRecord = document.getElementById('am-list-news-bd')

	//绘制单个div
	function setLi(item){
		var Li = '<li class="am-g am-list-item-desced">'
	    	+'<div>'
	            +'<a href="###">'
	            +'<i style="color:#6C7B8B;font-size:16px;" class="iconfont icon-kecheng"></i>'
			    +'<span style="color:#757575;">'+item.teacherLesson+'</span>'
			    +'<span class="am-list-news-grade am-fr">《一年级》</span>'
			    +'</a>'
	        +'</div>'
	        +'<div class="am-list-item-hd">'
	        	+'<span>家长：</span>'+item.parentName+'</span>'
	        	+'<span class="am-fr">联系电话：'+item.linkPhone+'</span>'
	        +'</div>'
	        +'<div class="am-list-item-text">'
		        +'<span>期望老师：</span>'+item.expectSex+'</span>'
	        	+'<span style="display:inline-block;margin-left:10%;">课时费：'+item.lessonCharge+'元/h</span>'
	        	+'<span class="am-fr">每周次数：'+item.timesWeek+'</span>'
	        +'</div>'
	        +'<div class="am-list-item-text">家教地址：'+item.placeProvince+item.placeCity+item.placeDistrict+item.managerName+'</div>'
	        +'<div class="am-list-item-text">要求说明：'+item.otherExplain+'</div>'
	        +'<div class="am-list-item-text">发布时间：'+item.addTime+'</div>'
	        +'</li>';
	    return Li;
	}
	
	//循环加载到页面
	function getApplicationData(){
		var dataList;
		$.ajax({  
            type:'post',  
            url:"/ezsh/educateInfoW/select",  
            dataType:'json',  
            /* data:{content:content}, */  
            beforeSend:function(){  
                /* obj.html('正在处理...'); */  
            },success:function(data){
                if(data.status == 1){
                	dataList = data.data.familyEducateInfoList;
                	var html = '';
            	    for(var i = 0;i<dataList.length;i++){
            	        html += setLi(dataList[i]);
            	    }
            	    applicationRecord.innerHTML = html;
                    //成功  
                }else{  
                    //出错   
                }  
            }  
    	});
	}

	 window.onload = getApplicationData();
	</script>
</html>
