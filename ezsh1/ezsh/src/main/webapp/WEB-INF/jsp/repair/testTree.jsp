<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>testTree</title>
</head>
  
  <body>
  	<ul id="tt"></ul>
  </body>
<script type="text/javascript">
	$(function(){
	$('#tt').tree({
    url:'http://localhost:8080/ezsh/tree/recursiveTree'
});
});
</script>
</html>
