<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="./css/style.css">
	<link rel="stylesheet" type="text/css" href="./css/table.css">
	<title>ユーザー退会画面</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<h1>ユーザー退会画面</h1>
	<p>
	<div class="messageError">
		<font color="red">
		<p>退会後は、会員向け提供サービス（ショッピングやマイページ、購入履歴の閲覧など）の利用ができなくなります。<br>
		また、ご登録中のユーザーIDと購入履歴が削除されます。<p>
		上記の確認事項をよく読み、承諾した上で退会確認画面にお進み下さい。<p>
		</font>
	</div>
	<s:form action="HomeAction">
		<div class="submit_btn_box">
			<s:submit class="submit_btn" value="キャンセル"/>
		</div>
	</s:form>
	<s:form action="UnsubscribeConfirmAction">
		<div class="submit_btn_box">
			<s:submit class="submit_btn" value="承諾して退会する"/>
		</div>
	</s:form>
</body>
</html>