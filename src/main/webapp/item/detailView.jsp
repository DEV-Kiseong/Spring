<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"></script>

<script>
	function inquire(){
	option = {
			type:"post",
			url: "inquireList.inq",
			data:{"goodsNum":"${dto.goodsNum}"},
			dataType : "html",
			success : function(result){
				$("#content").html(result);
			},
			error : function(){
				alert('에러가 나왔다 홀홀홀~');
				return;
			}
		}
		$.ajax(option);
	}

	$(function(){
	/*
	$("#inquire").click(function(){
		//location.href = "inquireList.inq?goodsNum=${dto.goodsNum}";
		option = {
			type:"post",
			url: "inquireList.inq",
			data:{"goodsNum":"${dto.goodsNum}"},
			dataType : "html",
			success : function(result){
				$("#content").html(result);
			},
			error : function(){
				alert('에러가 나왔다 홀홀홀~');
				return;
			}
		}
		$.ajax(option);
	});
	*/
	
	$("#descript").click(function(){
		//location.reload();
		//location.href = "descript.item?num=${dto.goodsNum}";
		   option = {
				type:"post",
				url: "descript.item",
				data:{"num":"${dto.goodsNum}"},
				dataType : "html",
				success : function(result){
					$("#content").html(result);
				},
				error : function(){
					alert('에러가 나왔다 홀홀홀~');
					return;
				}
			}
			$.ajax(option);
	});
	$("#review").click(function(){
		//location.href = "reviewList.review?goodsNum=${dto.goodsNum}";
		option = {
			type:"post",
			url: "reviewList.review",
			data:{"goodsNum":"${dto.goodsNum}"},
			dataType : "html",
			success : function(result){
				$("#content").html(result);
			},
			error : function(){
				alert('에러가 나왔다 홀홀홀~');
				return;
			}
		}
		$.ajax(option);
	});
		$("#wish").click(function(){
			$.ajax({
				type:"post",
				url:"wishItem.item",
				data:{"goodsNum":"${dto.goodsNum}"},
				dataType : "text",
				success:function(result){
					if(result == "0"){
						$("#wish").attr("src","images/right_arrow.png");
						alert("관심상품으로 등록되었습니다.");
					}else{
						$("#wish").attr("src","images/left_arrow.png");
						alert("관심상품으로 등록이 취소되었습니다.");
					}
				},
				error:function(){
					alert('로그인 아웃 되었습니다.\n다시 로그인 해 주세요.');
					//window.open("loginCk.login","loginck","width=400,height=400");
					location.href='<c:url value="/"/>';
					return false;
				}
			});
		});
		$("#buyItem").click(function(){
			location.href=
				"buyItem.item?goodsNum=${dto.goodsNum}&cartQty="+$("#cartQty").val();
		});
		
		$("#cartBtn").click(function(){
			// Ajax  
			// Json자료형 : a = {"key":"value", "key1":"value1",...,"key_n":"value_n"}
			if(${auth != null}){
				if(${auth.grade == 'mem'}){
					$.ajax({
						type : "post",
						url : "cart.item",
						dataType : "html",
						data : {"goodsNum":"${dto.goodsNum}","cartQty":$("#cartQty").val()}, 
						success : function(){
							con = confirm("장바구니로 이동하시겠습니까?");
							if(con){
								location.href="cartList.item";
							}
						},
						error : function(){
							window.open("loginCk.login","loginck","width=400,height=400");
							return;
						}
					});
				}else alert("직원은 직원전용 사이트를 사용하세요.");
			}else{
				window.open("loginCk.login","loginck","width=400,height=400");
			}
		});
	});
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="800" align="center">
		<tr>
			<td rowspan="5"><img src="goods/images/${dto.goodsMainStore }"
				height="200" /></td>
			<td>상품명 : ${dto.goodsName }</td>
		</tr>
		<tr>
			<td>가격 : ${dto.goodsPrice }</td>
		</tr>
		<tr>
			<td>배송비 : <c:if test="${dto.deliveryCost == 0 }">무료배송</c:if> <c:if
					test="${dto.deliveryCost != 0 }">${dto.deliveryCost}</c:if>
			</td>
		</tr>
		<tr>
			<td>수량 : <input type="number" id="cartQty" min="1" value="1"
				step="1" name="cartQty"></td>
		</tr>
	</table>
	<table width="800" align="center">
		<tr>
			<td>
				<button type="button" id="cartBtn">장바구니</button> |
				<button type="button" id="buyItem">바로구매</button> | 찜
				<c:if test="${isTrue == 0 }">
				<img src="images/left_arrow.png" id="wish" width="20" />
				</c:if>
				<c:if test="${isTrue == 1 }">
				<img src="images/right_arrow.png" id="wish" width="20" />
				</c:if>
			</td>
		</tr>
	</table>
	<table width="800" align="center">
		<tr>
			<td><span id="descript">제품 상세 설명</span> | 
				<span id="review">리뷰</span> | 
				<span id="inquire" onclick="inquire();">상품문의</span></td>
		</tr>
	</table>
	<div id="content">
		<table width="800" align="center">
			<tr>
				<td colspan="2">${fn:replace(dto.goodsContent,newLine,"<br />") }<br />
					<c:forTokens items="${dto.goodsImages }" delims="`" var="img">
						<img src="goods/images/${img }" height="300" />
						<br />
					</c:forTokens>
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>