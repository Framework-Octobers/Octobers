<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/table.css">
<title>ユーザー情報入力</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div id = "main">
    <h1>ユーザー情報入力画面</h1>
               <!-- 姓入力チェックでfalseのとき表示 -->
                 <s:if test="familyNameErrorMessageList != null && familyNameErrorMessageList.size() > 0 ">
                   <div class="messageError">
                      <s:iterator value="familyNameErrorMessageList"><s:property /><br></s:iterator>
                   </div>
                 </s:if>
                 <!-- 名入力チェックでfalseのとき表示 -->
                 <s:if test="firstNameErrorMessageList != null && firstNameErrorMessageList.size() > 0 ">
                   <div class="messageError">
                      <s:iterator value="firstNameErrorMessageList"><s:property /><br></s:iterator>
                   </div>
                 </s:if>
                 <!-- 姓かな入力チェックでfalseのとき表示 -->
                 <s:if test="familyNameKanaErrorMessageList != null && familyNameKanaErrorMessageList.size() > 0 ">
                   <div class="messageError">
                      <s:iterator value="familyNameKanaErrorMessageList"><s:property /><br></s:iterator>
                   </div>
                 </s:if>
                 <!-- 名かな入力チェックでfalseのとき表示 -->
                 <s:if test="firstNameKanaErrorMessageList != null && firstNameKanaErrorMessageList.size() > 0 ">
                   <div class="messageError">
                      <s:iterator value="firstNameKanaErrorMessageList"><s:property /><br></s:iterator>
                   </div>
                 </s:if>
                 <!-- メールアドレス入力チェックでfalseのとき表示 -->
                 <s:if test="emailErrorMessageList != null && emailErrorMessageList.size() > 0 ">
                   <div class="messageError">
                      <s:iterator value="emailErrorMessageList"><s:property /><br></s:iterator>
                   </div>
                 </s:if>
                 <!--ユーザーID入力チェックでfalseのとき表示 -->
                 <s:if test="userIdErrorMessageList != null && userIdErrorMessageList.size() > 0 ">
                   <div class="messageError">
                      <s:iterator value="userIdErrorMessageList"><s:property /><br></s:iterator>
                   </div>
                 </s:if>
                  <!--パスワード入力チェックでfalseのとき表示 -->
                <s:if test="passwordErrorMessageList != null && passwordErrorMessageList.size() > 0 ">
                   <div class="messageError">
                      <s:iterator value="passwordErrorMessageList"><s:property /><br></s:iterator>
                   </div>
                 </s:if>
                  <!--入力したユーザーIDがすでに存在するとき表示 -->
                 <s:if test="isExistsUserErrorMessage != null && !isExistsUserErrorMessage.isEmpty() ">
                   <div class="messageError">
                      <s:property value="isExistsUserErrorMessage" />
                   </div>
                 </s:if>
                 <!-- 入力画面 -->
              <s:form action="CreateUserConfirmAction">
                <table class="vertical-list-table">
                   <tr>
                      <th scope="row">
                        <s:label value="姓"/>
                      </th>
                      <td>
                         <s:textfield name="familyName" value="%{#session.familyName}"  placeholder="姓" class="txt" />
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="名"/>
                      </th>
                      <td>
                         <s:textfield name="firstName" value="%{#session.firstName}" placeholder="名" class="txt" />
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="姓ふりがな"/>
                      </th>
                      <td>
                         <s:textfield name="familyNameKana" value="%{#session.familyNameKana}" placeholder="姓ふりがな" class="txt"/>
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="名ふりがな"/>
                      </th>
                      <td>
                         <s:textfield name="firstNameKana" value="%{#session.firstNameKana}" placeholder="名ふりがな" class="txt"/>
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="性別"/>
                      </th>
                      <td>
                         <s:radio name="sex" list="%{#session.sexList}" value="%{#session.sex}"  placeholder="性別" />
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="メールアドレス"/>
                      </th>
                      <td>
                          <s:textfield name="email" value="%{#session.email}" placeholder="メールアドレス" class="txt"/>
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="ユーザーID"/>
                      </th>
                      <td>
                         <s:textfield name="userId" value="%{#session.userIdForCreateUser}" placeholder="ユーザーID" class="txt"/>
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="パスワード"/>
                      </th>
                      <td>
                         <s:password name="password" value="" placeholder="パスワード" class="txt"/>
                      </td>
                   </tr>
                   </table>
                   <!-- 完了画面に遷移する -->
                   <div class="submit_btn_box">
                      <s:submit value="確認" class="submit_btn" />
                   </div>
              </s:form>
          </div>

</body>
</html>