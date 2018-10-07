<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="微信支付成功">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,viewport-fit=cover">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/weui.min.css"/>
    <link rel="stylesheet" href="example/example.css"/>
    <style>
        header p{
            text-align: center;
            font-family: 微软雅黑;
        }
    </style>
</head>
<body>
<header style="width: 100%;height: 50px;background: #333030;color: white"><p style="font-size:18px;">交易详情</p><p style="font-size:12px">微信安全支付</p></header>
<div class="weui-msg">
    <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
    <div class="weui-msg__text-area">
        <div class="weui-msg__title" style="color: #0BB20C">支付成功</div>
        <p class="weui-msg__desc" style="font-size: 30px;color: #000000">￥<%=request.getParameter("money")%></p>
    </div>
</div>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd weui-cell_primary"><p style="color: #999;font-size: 17px">收款人</p></div>
            <div class="weui-cell__ft">e众智慧社区</div>
        </div>
    </div>
    <div class="weui-msg__opr-area">
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:;">完成</a>
        </div>
    </div>
</body>
</html>
