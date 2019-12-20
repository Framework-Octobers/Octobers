<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/table.css">
<title>宛先情報入力確認</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div id="main">
    <h1>宛先情報入力画面</h1>
           <!-- 宛先情報入力確認 -->
              <table class="vertical-list-table">
               <tr>
                   <th scope="row">
                      <s:label value="姓"/>
                   </th>
                   <td>
                      <s:property value="familyName"/>
                   </td>
               </tr>
        	   <tr>
                   <th scope="row">
                      <s:label value="名"/>
                   </th>
                   <td>
                      <s:property value="firstName"/>
                   </td>
               </tr>
               <tr>
                   <th scope="row">
                      <s:label value="姓ふりがな"/>
                   </th>
                   <td>
                      <s:property value="familyNameKana"/>
                   </td>
               </tr>
               <tr>
                   <th scope="row">
                      <s:label value="名ふりがな"/>
                   </th>
                   <td>
                      <s:property value="firstNameKana"/>
                   </td>
               </tr>
               <tr>
                   <th scope="row">
                      <s:label value="住所"/>
                   </th>
                   <td>
                      <s:property value="userAddress"/>
                   </td>
               </tr>
               <tr>
                   <th scope="row">
                      <s:label value="電話番号"/>
                   </th>
                   <td>
                      <s:property value="telNumber"/>
                   </td>
               </tr>
               <tr>
                   <th scope="row">
                      <s:label value="メールアドレス"/>
                   </th>
                   <td>
                      <s:property value="email"/>
                   </td>
               </tr>

              </table>
              <!-- 宛先入力完了画面に遷移 -->
              <s:form action="CreateDestinationCompleteAction">
              <div class="submit_btn_box">
                <s:submit value="登録" class="submit_btn"/>
                </div>
              </s:form>
              <!-- 宛先入力画面に遷移 -->
              <s:form action="CreateDestinationAction">
              <div class="submit_btn_box" >
                <s:submit value="戻る" class="submit_btn"/>
              </div>
              <s:hidden name="backFlg" value="1"/>
              </s:form>
          </div>
    <div id="footer">
    </div>

</body>
</html>