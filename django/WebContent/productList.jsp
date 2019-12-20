<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/style.css">
	<title>商品一覧</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<h1>商品一覧画面</h1>
		<s:if test="keywordsErrorMessageList!=null && keywordsErrorMessageList.size()>0">
			<div class="messageError">
				<s:iterator value="keywordsErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</s:if>
		<s:elseif test="productInfoDTOList!=null && productInfoDTOList.size()>0">
			<table class="product-list-table">
				<s:iterator value="productInfoDTOList" status="st">
					<s:if test="#st.index%3 == 0"><tr> </s:if>
						<td>
							<a href='<s:url action="ProductDetailsAction"><s:param name="productId" value="%{productId}"/></s:url>'>
								<img src='<s:property value="productImageFilePath"/>/<s:property value="productImageFileName"/>' class="itemlist-img"/><br></a>
								<s:property value="productName"/><br>
								<s:property value="productNameKana"/><br>
								<s:property value="productPrice"/>円<br>

						</td>
					<!-- この警告はifタグに囲まれていて開始タグが見つからなく、文法上問題ないため、無視する。 -->
					<s:if test="#st.index%3 == 2"></tr> </s:if>
				</s:iterator>
			</table>
		</s:elseif>
		<s:else>
			<div class="messageResult">
				検索結果がありません。
			</div>
		</s:else>
	</div>
</body>
</html>