<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deliveryRegist.jsp</title>
</head>
<body>
<form action="deliveryWrite.deli" method="post" name="frm">
배송정보 등록 <br/>
주문번호 : <input type="text" name="purchaseNum" value='${purchaseNum}' readonly="readonly"><br/>
송장번호 : <input type="text" name="deliveryNum" required="required"><br/>
<input type="submit" value="송장번호등록"/>
</form>
</body>
</html>