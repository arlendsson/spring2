<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/include/incHeader.jsp" %>

<script type="text/javascript">
var hasId = function() {
	var id = $.cookie('id');
	if (id != null && id != '') {
		$("#id").val($.cookie('id'));
		$("#rememberChk").prop("checked", true);
	}
}

$(document).ready(function () {
	hasId();

	$("#testBtn").click(function () {
		$.ajax({
			url : '/mypage/ajax/data'
			, data : $("#form").serialize()
			, processData : false
			, type : 'POST'
			//, dataType : 'application/json'
			, success : function (result) {
				var data = JSON.parse(result);
				if (data.success) {
				} else {
					$("#body").html(result);
				}
			}
			, error : function () {
				alert('ajax error');
			}
		});
	});

	var rememberId = function() {
		if ($("#rememberChk").prop("checked")) {
			$.cookie('id', $("#id").val(), { expires: 7, path: '/login/loginPage'/* , domain: '' */, secure: false });
		} else {
			$.cookie('id', '');
			$.cookie('id', '', { expires: -1, path: '/login/loginPage' });
		}
	}

	$("#loginBtn").click(function () {
		rememberId();

		$.ajax({
			url : '/login/login'
			, data : $("#form").serialize()
			, processData : false
			, type : 'POST'
			, success : function (result) {
				var data = JSON.parse(result);
				if (data.success) {
					location.href = '/mypage/myPage';
				} else {
					alert("로그인 실패");
				}
			}
		});
	});

	$("#rememberChk").click(function () {
		rememberId();
	});

});

</script>

<form class="form-horizontal" id="form">
	<div class="form-group">
	  <label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
	  <div class="col-sm-10">
	    <input type="text" class="form-control" id="id" name="id" placeholder="아이디 입력">
	  </div>
	</div>
	<div class="form-group">
	  <label for="inputPassword3" class="col-sm-2 control-label">패스워드</label>
	  <div class="col-sm-10">
	    <input type="password" class="form-control" id="pw" name="pw" placeholder="패스워드 입력">
	  </div>
	</div>
	<div class="form-group">
	  <div class="col-sm-offset-2 col-sm-10">
	    <div class="checkbox">
	      <label>
	        <input type="checkbox" id="rememberChk"> Remember me
	      </label>
	    </div>
	  </div>
	</div>
	<div class="form-group">
	  <div class="col-sm-offset-2 col-sm-10">
	    <button class="btn btn-default" type="button" id="loginBtn">로그인</button>
	  </div>
	</div>
</form>

<%@include file="/WEB-INF/views/include/incFooter.jsp" %>