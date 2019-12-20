<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/table.css">
<title>宛先情報入力</title>
</head>
<body>
       <jsp:include page="header.jsp"/>
        <div id="main">
        <h1>宛先情報入力画面</h1>
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
                 <!-- 電話番号入力チェックでfalseのとき表示 -->
                <s:if test="telNumberErrorMessageList != null && telNumberErrorMessageList.size() > 0 ">
                 <div class="messageError">
                      <s:iterator value="telNumberErrorMessageList"><s:property /><br></s:iterator>
                 </div>
                 </s:if>
                 <!-- 住所入力チェックでfalseのとき表示 -->
                 <s:if test="userAddressErrorMessageList != null && userAddressErrorMessageList.size() > 0 ">
                 <div class="messageError">
                      <s:iterator value="userAddressErrorMessageList"><s:property /><br></s:iterator>
                 </div>
                 </s:if>
                 <!-- 宛先入力 -->
              <s:form action="CreateDestinationConfirmAction">
                <table class="vertical-list-table">
                   <tr>
                      <th scope="row">
                        <s:label value="姓"/>
                      </th>
                      <td>
                         <s:textfield name="familyName" value="%{#session.familyName}" class="txt" />
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="名"/>
                      </th>
                      <td>
                         <s:textfield name="firstName" value="%{#session.firstName}" class="txt" />
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="姓ふりがな"/>
                      </th>
                      <td>
                         <s:textfield name="familyNameKana" value="%{#session.familyNameKana}"  class="txt"/>
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="名ふりがな"/>
                      </th>
                      <td>
                         <s:textfield name="firstNameKana" value="%{#session.firstNameKana}" class="txt"/>
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="住所"/>
                      </th>
                      <td>
                         <s:textfield name="userAddress" value="%{#session.userAddress}"  class="txt"/>
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="電話番号"/>
                      </th>
                      <td>
                         <s:textfield name="telNumber" value="%{#session.telNumber}" class="txt"/>
                      </td>
                   </tr>
                   <tr>
                      <th scope="row">
                        <s:label value="メールアドレス"/>
                      </th>
                      <td>
                          <s:textfield name="email" value="%{#session.email}"  class="txt"/>
                      </td>
                   </tr>
                    </table>
                    <!-- 宛先入力確認画面に遷移 -->
                   <div class="submit_btn_box">
                      <s:submit value="確認" class="submit_btn"/>
                   </div>
              </s:form>
          </div>

</body>
</html>