<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String orgId = session.getAttribute("loginTreeId").toString();
//Object orgId = session.getAttribute("loginTreeId");
    %>
<html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="支付宝支付成功">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,viewport-fit=cover">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/weui.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/example/example.css"/>
    <style>
        header p{
            text-align: center;
            font-family: 微软雅黑;
        }
    </style>
</head>
<body>
<header style="width: 100%;height: 50px;background: #333030;color: white">
    <div>
    <p style="font-size:18px;">交易详情</p>
    <p style="font-size:12px">支付宝</p>
    </div>
    <a href="javascript:;" style="float: right; bottom: 32px;right: 12px;position: relative;color:#1394d6;font-size: 17px">完成</a>

</header>
<div class="weui-msg">
    <div class="weui-msg__icon-area"><img src="${pageContext.request.contextPath}/example/images/zfcg.png" style="width: 100px;height: 90px"></div>
    <div class="weui-msg__text-area">
        <div class="weui-msg__title" style="color: #2e9dea">付款成功</div>
        <p class="weui-msg__desc" style="font-size: 30px;color: #000000">￥<%=request.getParameter("alipay_trade_wap_pay_response")%></p>
    </div>
</div>
<div class="weui-cells">
    <div class="weui-cell">
        <div class="weui-cell__bd weui-cell_primary"><p style="color: #999;font-size: 17px">收款人</p></div>
        <div class="weui-cell__ft">e众智慧社区</div>
    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>
     var alipay_trade_wap_pay_response="<%=request.getParameter("alipay_trade_wap_pay_response")%>";
     var sign="<%=request.getParameter("sign")%>";
     var synchronize={};
     synchronize.alipay_trade_wap_pay_response=alipay_trade_wap_pay_response;
     synchronize.sign=sign;
     
     alert(synchronize.toString());
    $.ajax({
        type:'POST',
        url:"https://szykcz.com/aliPay/verifyParkingPay",
        data:{
            synchronize:synchronize
        },
        success:function () {
            alert("支付成功");
        }
    })
</script>
</html>
