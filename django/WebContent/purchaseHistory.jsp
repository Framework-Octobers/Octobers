<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/table.css">

<title>PurchaseHistory画面</title>
</head>
<body>

	<jsp:include page="header.jsp"/>

	<div id = "main">
		<h1>商品購入履歴画面</h1>
		<div>
			<s:if test="purchaseHistoryInfoDTOList == null || purchaseHistoryInfoDTOList.size() == 0">
				<div class=messageResult>商品購入履歴情報がありません。</div>
			</s:if>
			<s:elseif test="message == null">
				<table class="horizontal-list-table">

				<tr>
					<th>商品名</th>
					<th>ふりがな</th>
					<th>商品画像</th>
					<th>販売会社名</th>
					<th>販売年月日</th>
					<th>値段</th>
					<th>個数</th>
					<th>合計金額</th>
					<th>宛先名前</th>
					<th>宛先住所</th>
				</tr>

				<s:iterator value="purchaseHistoryInfoDTOList">
				<tr>
					<td><s:property value="productName"/></td>
					<td><s:property value="productNameKana"/></td>
					<td><img src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'width="50px" height="50px"/></td>
					<td><s:property value="releaseCompany"/></td>
					<td><s:property value="releaseDate"/></td>
					<td><s:property value="price"/><span>円</span></td>
					<td><s:property value="productCount"/><span>個</span></td>
					<td><s:property value="totalPrice"/><span>円</span></td>
					<td><s:property value="familyName"/>　<s:property value="firstName"/></td>
					<td><s:property value="userAddress"/></td>
				</tr>
				</s:iterator>
				</table>
				<div class="submit_btn_box">
				<s:form action="DeletePurchaseHistoryAction">
					<input type="hidden" name="deleteFlg" value="1">
					<s:submit value="削除" class="submit_btn"/>
				</s:form>
				</div>
			</s:elseif>
		</div>
	</div>
</body>
</html>