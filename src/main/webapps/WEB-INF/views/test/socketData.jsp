<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>

<html lang="ko">
<head>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<script type="text/javascript" src="${contextPath}/js/jquery-1.12.1.min.js"></script>
</head>

	<h1>방화벽 API 수신 테스트</h1>
	<textarea id="jsonTxt" rows="20" cols="200">
		{
			"DEVICEID":"1223391865"
			,"DEVICETYPE":"D"
			,"OPCODE":"000"
			,"RSSI":"-100"
			,"IN24V":"23"
			,"INB24V":"24"
			,"S1":"00"
			,"OUT1":"00"
			,"INTEMP":"-010"
			,"EXTEMP":"-010"
		}
	</textarea>
<div><input type="button" onclick="testSoket();" value="소캣 호출"></div>
<script>

</script>
</html>