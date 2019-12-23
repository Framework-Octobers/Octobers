<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="./css/style.css">
	<link rel="stylesheet" type="text/css" href="./css/table.css">
	<title>退会確認画面</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<h1>退会確認画面</h1>
	<div class="messageError">
		<font color="red">
		<p>本当に退会しますか。<br>（退会ボタンを押すと、ユーザ情報が完全に削除されます。）<p>
		</font>
	</div>
	<s:form action="HomeAction">
		<div class="submit_btn_box">
			<s:submit class="submit_btn" value="キャンセル"/>
		</div>
	</s:form>
	<s:form action="UnsubscribeCompleteAction">
		<div class="submit_btn_box">
			<s:submit class="submit_btn" value="退会する" />
		</div>
	</s:form>
</body>
</html>