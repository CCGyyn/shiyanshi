<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%

    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String orgId = session.getAttribute("loginTreeId").toString();
//Object orgId = session.getAttribute("loginTreeId");
%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="微信端个人支付">
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0,viewport-fit=cover">
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/weui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/example/example.css" />
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<style>
#pay {
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 45px;
	background-color: #00c800;
	transition: background 1s;
}
</style>
</head>
<body>

	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">车牌号码:</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" placeholder="请输入车牌号" id="carnumber" />
			</div>
			<div class="weui-cell__ft">
				<button id="sure" class="weui-btn weui-btn_mini weui-btn_primary"
					style="height: 30px; background: #09bb07">确定</button>
			</div>
		</div>
	</div>
	<div id="paywindow">
		<div style="display: inline-block">
			<div class="weui-cells__title" style="display: inline-block">
				车辆详细信息<a href="#" style="margin-left: 200px"><img
					src="${pageContext.request.contextPath}/example/images/wh.jpg"
					style="width: 15px;height: 15px"></a>
			</div>
		</div>
		<table style="margin-left: 14px;">

			<tr>
				<td><span style="font-family: 微软雅黑;font-weight: bold">车牌号码:</span><span
					id="t1"
					style="margin-left: 5px;color: #0e280f;font-weight: 600;font-size: 18px;">Test1</span></td>
			</tr>
			<tr>
				<td><span style="font-family: 微软雅黑;font-weight: bold">进场时间:</span><span
					id="t2"
					style="margin-left: 5px;color: #0e280f;font-weight: 600;font-size: 18px;">2018-04-08
						21:21:06</span></td>
			</tr>
			<tr>
				<td><span style="font-family: 微软雅黑;font-weight:bold">停车金额:</span><span
					id="t3"
					style="margin-left: 5px;color: #0e280f;font-weight: 600;font-size: 18px;">1</span></td>
			</tr>
		</table>

		<div class="weui-cells__title">选择支付方式</div>
		<div class="weui-cells weui-cells_radio">
			<label class="weui-cell weui-check__label" for="x1">
				<div class="weui-cell__bd" style="display: inline-block">
					<img
						src="${pageContext.request.contextPath}/example/images/weuxin.jpg "
						height="42px" width="42px"
						style="display: inline-block;margin-bottom: -14px; padding-right: 6px;">
					<span style="font-family: 微软雅黑"> 微信支付</span>
				</div>
				<div class="weui-cell__ft">
					<input type="radio" class="weui-check" name="radio1" id="x1"
						checked="checked" /> <i class="weui-icon-checked"></i>
				</div>
			</label> <label class="weui-cell weui-check__label" for="x2">
				<div class="weui-cell__bd"
					style="display: inline-block; margin-left: 7px;">
					<img
						src="${pageContext.request.contextPath}/example/images/zfb.jpg "
						height="28px" width="28px"
						style="display: inline-block;margin-bottom: -10px;padding-right: 12px;">
					<span style="font-family: 微软雅黑">支付宝</span>
				</div>
				<div class="weui-cell__ft">
					<input type="radio" class="weui-check" name="radio1" id="x2" /> <i
						class="weui-icon-checked"></i>
				</div>
			</label>
		</div>

		<footer id="pay">
			<div
				style="color: white;padding-top: 9px;text-align: center;font-family: 微软雅黑">确定支付</div>
		</footer>
	</div>
	<script type="text/javascript">
    $('#sure').click(function () {
        var carnumber=$('#carnumber').val();
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/ezsh/park/getSupposeParkCost",
            data:{"plateNumber":carnumber},
            success: function(data){
                console.log(data);
                if(data["status"] == 1){
                    alert("操作成功");
                    $('#t1').text(carnumber);
                    $('#t2').text(data.data.inTime);
                    $('#t3').text(data.data.money);
                    $('#paywindow').show();

                }else{
                    alert(data["message"]);
                }
            }

        });
    });

    $('#pay').click(function () {
        /*     var carnumber=$('#carnumber').val();*/

        var money=$('#t3').text();
        this.style.background="#8d8a8e";
        $('#pay').find('div').text("正在支付....");
        if($("#x1").is(':checked')) {
            console.log(1);
        }

        else if($("#x2").is(':checked')){
        	alert("com on!");
          $.ajax({
              type:'POST',
              url:"https://szykcz.com/park/parkPrepayment",
             /* url:"http://localhost:8080/ezsh/park/parkPrepayment", */
           dataType:"json",
           asnyc:false,
           cache: false,
           timeout : 50000,
              data:{
                plateNum:"Test1",
                money:money,
                payWay:"alipay"
              },
              success:function (data) {
                  var datas=decodeURI(data.data.orderInfo);
                  
                  var url="https://openapi.alipay.com/gateway.do?"+datas;
                   /* alert(url); */
                   window.location.href=url;
              },
              error:function(XMLHttpRequest, textStatus, errorThrown){
            	  alert("失败！error"); 
            	  alert(XMLHttpRequest.status);
             	  alert(XMLHttpRequest.readyState);
             	  alert(textStatus);
              } 


          })
        }
    })

</script>

</body>
</html>
