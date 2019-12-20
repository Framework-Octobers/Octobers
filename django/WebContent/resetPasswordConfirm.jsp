<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/table.css">
<title>resetPasswordConfirm画面</title>
</head>
<body>

	<jsp:include page="header.jsp"/>

	<div id="main">
          <div id="top">
                  <h1>パスワード再設定確認画面</h1>
          </div>
          <div>
          	<table class="vertical-list-table" >
               <tr>
                   <th><s:label value="	ログインID"/></th>
                   <td><s:property value="userId"/></td>
               </tr>
               <tr>
                   <th><s:label value="パスワード"/></th>
                   <td><s:property value="newPassword"/></td>
               </tr>
            </table>

            <s:form action="ResetPasswordCompleteAction">
            <div class="submit_btn_box">
              <s:submit value="パスワード再設定" class="submit_btn"/>
            </div>
            </s:form>

            <s:form action="ResetPasswordAction">
            <div class="submit_btn_box">
            	<s:submit value="戻る" class="submit_btn"/>
            </div>
            	<s:hidden id ="backFlg" name="backFlg" value="1"/>
            </s:form>
          </div>
	</div>
</body>
</html>