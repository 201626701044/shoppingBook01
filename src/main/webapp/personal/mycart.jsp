<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<link type="text/css"  href="CSS/front.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CSS Bookstore 网上书城</title>

</head>
<body>

<div id="header">
	<div class="decoration-2"></div>
</div>
<%@ include file="left.jsp" %>
<div id="content">

<a href="front/orderlist.do"><span>我的购物车</span></a>
<div class="recommendation img-left" style="border-bottom:1px solid orange;">
   <ul>
               <li style="float:left;width:200px;">商品信息</li>
			  <li style="float:left;width:100px;">单价(元)</li>
			  <li style="float:left;width:100px;">数量</li>
			  <li style="float:left;width:100px;">金额(元)</li>
  </ul>
   <div style="clear:both;"></div>
  </div>
      <c:forEach items="${cart.list}" var="single"> 
		<div class="recommendation img-left" style="border-bottom:1px solid orange;">
			
					<ul>
			  <li style="float:left;width:200px;"><a href="front/item.do?orderCode=${single.bookID}" style="color:blue;"><img src="${single.bookImage}" width="80"/>${single.bookName}</a></li>
			  <li style="float:left;width:100px;">${single.price}</li>
			  <li style="float:left;width:100px;"><input type="text" style="width:50px;" value="${single.qty}" name="qty${single.bookID}"/></li>
			  <li style="float:left;width:100px;">${single.totalPrice}</li>
			  <li style="float:left;width:100px;"><a href="front/delitem.do?cartID=${single.cartID}" style="color:blue;">删除</a></li>
			</ul>
   <div style="clear:both;"></div>
		</div>
	</c:forEach>
   <div><a href=""/>清空购物车</a></div>
   <input type="submit" value="提交" />

</div>
<div style="clear:both;"></div>
<div style="text-align:center;">
	<p class="p1"> </p>
	<p class="p2">江西师大软件学院, Copyright © 2018&nbsp; | Privacy Notice
		| Terms of Use </p>
</div>


</body>
</html>
