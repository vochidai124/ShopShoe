<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="header" class="container-fluid">
		<nav class="navbar navbar-expand-sm navbar-dark fixed-top"> <!-- Brand -->
		<a class="navbar-brand col-sm-2" href="#"><img id="icon-yame" class="float-right" src='<c:url value="/resources/image/icon-yame.jpg"/>'/></a> <!-- Links -->
		<ul class="nav navbar-nav col-sm-6 justify-content-center nav_center">
			<li class="nav-item active"><a class="nav-link" href="#">TRANG CHỦ</a></li>
			
			<!-- Dropdown -->
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">  SẢN PHẨM  </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="#">Áo Thun</a> 
					<a class="dropdown-item" href="#">Áo Sơ Mi</a> 
					<a class="dropdown-item" href="#">Áo Khoát</a>
				</div>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">DỊCH VỤ</a></li>
			<li class="nav-item"><a class="nav-link" href="#">LIÊN HỆ</a></li>
		</ul>
		<ul class="nav navbar-nav col-sm-4 nav_left">
			<c:choose>
				<c:when test="${chucaidau!=null }">
					<li class="nav-item "><a class="nav-link dangnhapthanhcong" href="dangnhap/">${chucaidau }</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item "><a class="nav-link" href="dangnhap/">ĐĂNG NHẬP</a></li>
				</c:otherwise>
			</c:choose>
			<li> <a class="navbar-brand icon-gio-hang" href="#"><img class="float-right" src='<c:url value="/resources/image/icon_giohang.png"/>'/></a><li>
		</ul>
		</nav>
		<div class="event_header container-fluid">
			<span>Ngày 10/6/2021 - 1/12/2021</span><br/>
			<span style="font-size: 50px">Mua 1 Tang 1</span><br/>
			<button>XEM NGAY</button>
		</div>
	</div>

	<div id="info" class="container-fluid">
		<div class="row">
			<div class="col-12 col-lg-4 col-md-4 col-sm-4">
				<img class="icon" alt="icon_chatluong" src='<c:url value="/resources/image/icon_chatluong.png"/>'/><br/>
				<span style="font-size: 32px; font-weight: 500;">CHẤT LƯỢNG</span><br/>
				<span>Chúng tui cam kết sẽ mang đến cho các bạn chất lượng sản phẩm tốt nhất</span><br/>
			</div>
			
			<div class="col-12 col-lg-4 col-md-4 col-sm-4">
				<img class="icon" alt="icon_chatluong" src='<c:url value="/resources/image/icon_conheo.png"/>'/><br/>
				<span style="font-size: 32px; font-weight: 500;">CHẤT LƯỢNG</span><br/>
				<span>Cam kết giá rẻ nhất Việt Nam giúp các bạn tiết kiệm hơn 20% cho từng sản phẩm</span><br/>
			</div>
				
			<div class="col-12 col-lg-4 col-md-4 col-sm-4">
				<img class="icon" alt="icon_chatluong" src='<c:url value="/resources/image/icon_giaohang.png"/>'/><br/>
				<span style="font-size: 32px; font-weight: 500;">CHẤT LƯỢNG</span><br/>
				<span>Cam kết giao hàng tận nơi trong ngày. Để mang sản phẩm đến cho khách hàng nhanh nhất</span><br/>
			</div>
		</div>	
	</div>
	<div id="title-sanpham" class="container-fluid">
		<span>SẢM PHẨM HOT</span>
		<div class="row">
		<c:forEach var="listsanpham" items="${listSanPham }">
			<div class="col-lg-3 col-md-6 col-12">
				<a href="chitiet/${listsanpham.getMasanpham() }">
					<div class="sanpham">
					<img alt="hinh" src='<c:url value="/resources/image/sanpham/${listsanpham.getHinhsanpham() }"/>'/><br/>
					<span>${listsanpham.getTensanpham() }</span><br/>
					<span class="gia">${listsanpham.getGiatien() } VND</span>
					</div>	
				</a>
			</div>
		</c:forEach>
		</div>
	</div>
	<!-- end sản phẩm -->
	<div id="footer" class="container-fluid">
		<div class="row">
			<div class="col-sm-4 col-md-4">
				<p><span class="title-footer">THÔNG TIN SHOP</span></p>
				<span>Yame là một thương hiệu thời trang đầy uy tín, luôn đảm bảo chất lượng sản phẩm tốt nhất cho khác hàng</span>
			</div>
			<div class="col-sm-4 col-md-4">
				<p><span class="title-footer">LIÊN HỆ</span></p>
				<span>43 Nguyễn Trãi, phường 12, quận 5, TP. Hồ Chí Minh <br/> vochidai12@gmail.com <br/> 0372429920</span>
			</div>
			<div class="col-sm-4 col-md-4">
				<p><span class="title-footer">GÓP Ý</span></p>
				<form action="" method="post">
					<input name="tennhanvien" class="footer-input material-textinput" style="margin-bottom: 8px;" type="text" placeholder="Email"/><br/>
					<textarea name="tuoi" class="footer-textarea" rows="4" cols="50" placeholder="Nội Dung"></textarea>
					<button class="footer-button material-primary-button">Đồng Ý</button>
				</form>
			</div>
		</div>
	</div>
	
	<jsp:include page="footer.jsp" />
</body>

</html>