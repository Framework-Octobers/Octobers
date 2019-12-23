<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/table.css">

<title>マイページ画面</title>
</head>
<body>

		<jsp:include page="header.jsp"/>

		<div id="main">
			<div id="top">
					<h1>マイページ画面</h1>
			</div>
			<div>
			<s:if test="userInfoDTO!=null"/>
			<table class="vertical-list-table">
			<tr>
				<th><s:label value="姓" /></th>
				<td><s:property value="userInfoDTO.familyName" /></td>
			</tr>
			<tr>
				<th><s:label value="名" /></th>
				<td><s:property value="userInfoDTO.firstName" /></td>
			</tr>
			<tr>
				<th><s:label value="ふりがな" /></th>
				<td><s:property value="userInfoDTO.familyNameKana"/><span> </span><s:property value="userInfoDTO.firstNameKana"/></td>
			</tr>
			<tr>
				<th><s:label value="性別" /></th>
				<td><s:if test="userInfoDTO.sex==1">女性</s:if><s:else>男性</s:else></td>
			</tr>
			<tr>
				<th><s:label value="メールアドレス" /></th>
				<td><s:property value="userInfoDTO.email" /></td>
			</tr>
			</table>
			<s:form action="PurchaseHistoryAction">
				<input type="hidden" name="loginFlg" value="1">
				<div class="submit_btn_box">
				<s:submit value="購入履歴" class="submit_btn"/>
				</div>
			</s:form>
			<s:form action="UnsubscribeAction">
				<div class="submit_btn_box">
				<s:submit value="退会する" class="submit_btn"/>
				</div>
			</s:form>

		<s:else>
			<div class="error">
			ユーザー情報がありません。
			</div>
		</s:else>
			</div>
		</div>
</body>
</html>
