<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/table.css">
<title>再設定画面</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="main">
		<div id="top">
			<h1>パスワード再設定画面</h1>

			<s:if test="userIdErrorMessageList!=null && userIdErrorMessageList.size()>0">
				<div class="messageError">
				<s:iterator value="userIdErrorMessageList"><s:property />
				<br></s:iterator>
				</div>
			</s:if>

			<s:if test="passwordErrorMessageList!=null && passwordErrorMessageList.size()>0">
				<div class="messageError">
				<s:iterator value="passwordErrorMessageList"><s:property />
				<br></s:iterator>
				</div>
			</s:if>
			<s:if test="newPasswordErrorMessageList!=null && newPasswordErrorMessageList.size()>0">
				<div class="messageError">
				<s:iterator value="newPasswordErrorMessageList"><s:property />
				<br></s:iterator>
				</div>
			</s:if>

			<s:if test="reConfirmationNewPasswordErrorMessageList!=null && reConfirmationNewPasswordErrorMessageList.size()>0">
				<div class="messageError">
				<s:iterator value="reConfirmationNewPasswordErrorMessageList"><s:property />
				<br></s:iterator>
				</div>
			</s:if>

			<s:if test="passwordIncorrectErrorMessage!=null && !passwordIncorrectErrorMessage.isEmpty()">
			<div class="messageError">
				<s:property value="passwordIncorrectErrorMessage"/>
				<br>
			</div>
			</s:if>
			<s:if test="newPasswordIncorrectErrorMessage!=null && !newPasswordIncorrectErrorMessage.isEmpty()">
				<div class="messageError">
					<s:property value="newPasswordIncorrectErrorMessage"/>
					<br>
				</div>
			</s:if>
		</div>
		<div>
			<s:form action="ResetPasswordConfirmAction">

					<s:if test="errorMessage !=''">
							<s:property value="errorMessage" escape="false"/>
					</s:if>

					<table class="vertical-list-table" >
							<tr>
								<th><s:label value="ユーザーID"/></th>
								<td><s:textfield name="userId" value="%{#session.userIdForResetPassword}" placeholder="ユーザーID" maxlength="8"/></td>
							</tr>
							<tr>
								<th><s:label value="現在のパスワード"/></th>
								<td><input type="password" name="password" value="" placeholder="現在のパスワード" maxlength="16"/></td>
							</tr>
							<tr>
								<th><s:label value="新しいパスワード"/></th>
								<td><input type="password" name="newPassword" value="" placeholder="新しいパスワード" maxlength="16"/></td>
							</tr>
							<tr>
								<th><s:label value="(再確認)"/></th>
								<td><input type="password" name="reConfirmation" value=""placeholder="新しいパスワード（再確認用）" maxlength="16"/></td>
							</tr>
					</table>
					<div class="submit_btn_box">
						<s:submit value="確認" class="submit_btn"/>
					</div>
			</s:form>
		</div>
	</div>
</body>
</html>