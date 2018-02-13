<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	
    <title>로그인페이지</title>

	<script type="text/javascript" src="/resources/js/jquery-3.2.1.min.js" ></script>

	<script type="text/javascript">
		// 스프링 시큐리티 403 오류
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		var login = function () {
			var newName = 'test';
			var xhr = new XMLHttpRequest();
			
			xhr.open('POST', '/login/login');
			xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xhr.setRequestHeader(header, token);	// 스프링 시큐리티 403 오류나서 추가해야 함.
			xhr.onload = function() {
				if (xhr.status === 200) {
					console.log("### onload");
				}
			};
			xhr.send(encodeURI('id=' + newName));
			xhr.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			       // Typical action to be performed when the document is ready:
		    	   console.log("### onreadystatechange");
			       document.getElementById("demo").innerHTML = xhr.responseText;
			    }
			};
		};
		
		
		var loginJq = function () {
			var myForm = document.getElementById('myForm');
			var formData = new FormData(myForm);
			
			$.ajax({
				url : '/login/login'
				, data : formData
				, type : 'POST'
				, success : function (result) {
					alert(result);
				}
				, processData : false
			});
		};
		
		
		$(function () {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(document).ajaxSend(function(e, xhr, options) {
				console.log("### 1 " + header);
				console.log("### 2 " + token);
				xhr.setRequestHeader(header, token);
			});
		});
		
		
		$(document).ready(function() {
			$("#ajaxBtn").click(function() {
				//loginJq();
				//login();
				test();
			});
			/** ***** **/
			

			
			
		});

		
		var test = function() {
			console.log("appName      : " + window.navigator.appName);
			console.log("appCodeName  : " + window.navigator.appCodeName);
			console.log("product      : " + window.navigator.product);
			console.log("appVersion   : " + window.navigator.appVersion);
			console.log("userAgent    : " + window.navigator.userAgent);
			console.log("platform     : " + window.navigator.platform);
			console.log("language     : " + window.navigator.language);
			console.log("onLine       : " + window.navigator.onLine);
			
			
			
			var appVersionTxt = '5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; rv:11.0) like Gecko';
			var userAgentTxt = 'Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; rv:11.0) like Gecko';
			
			
			window.navigator.__defineGetter__('appVersion', function () {
			    return appVersionTxt;
			});
			window.navigator.__defineGetter__('userAgent', function () {
			    return userAgentTxt;
			});
			
			window.open('www.naver.com', 'testPop', '_blank');
			console.log("appVersion   : " + window.navigator.appVersion);
			console.log("userAgent    : " + window.navigator.userAgent);
		}
		
		var uploadFile = function() {
			$("#form").ajaxForm({
				beforeSubmit: function (data, form, option) {
					//validation 체크
					//막기 위해서는 return false를 잡아주면 됨
					return true;
				},
				success: function (response, status) {
					//성공 후 서버에서 받은 데이터 처리
					alert("업로드 성공");
					
				},
				error: function () {
					//에러발생을 위한 code 페이지
				}
			});
		}
	</script>
	
	
</head>
  
<body>
<h2>로그인 </h2>
<form id="form" name="form" method="post" action="loginProcess" enctype="multipart/form-data">
	<div id="demo" style="width: 100%; height: 200px;">
	</div>
	<table>
	    <tr height="40px">
	        <td>사용자아이디</td>
	        <td><input type="text" name="id" /></td>
	    </tr>
	    <tr height="40px">
	        <td>패스워드</td>
	        <td><input type="password" name="pw" /></td>
	    </tr>
	    <tr>
	    	<td>첨부파일</td>
	    	<td><input type="file" name="uploadFile" /></td>
	    </tr>
	</table>
	<table>
	    <tr>
	        <td align="center"><input id="ajaxBtn" type="button" value="ajax test" /></td>
	        <td align="center"><input type="submit" value="로그인" /></td>
	        <td align="center"><input type="reset" value="리셋" /></td>
	    </tr>
	</table>
</form>
</body>
</html>


<!-- 출처: http://goodcodes.tistory.com/entry/Spring-01-스프링-시큐리티로그인-사용자-인증 [Good Codes Make a Good Program] -->