<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
<title>Pooled Admin Panel Category Flat Bootstrap Responsive Web Template | Home :: w3layouts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="Pooled Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Bootstrap Core CSS -->
<link href='<c:url value="/resources/bootstrap-4.0.0-dist/css/bootstrap.min.css"/>' rel='stylesheet'/>
<!-- Custom CSS -->
<link href='<c:url value="/resources/css/style.css"/>' rel='stylesheet'/>
<link href='<c:url value="/resources/css/morris.css"/>' rel='stylesheet'/>
<link href='<c:url value="/resources/Styles/themsanpham.css"/>' rel='stylesheet'/>
<!-- Graph CSS -->
<link href='<c:url value="/resources/css/font-awesome.css"/>' rel='stylesheet' />
<!-- jQuery -->
<script src='<c:url value="/resources/JS/jquery-3.2.1.min.js"/>'></script>
<!-- //jQuery -->
<link href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type="text/css"/>
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type="text/css">
<!-- lined-icons -->
<link href='<c:url value="/resources/css/icon-font.min.css"/>' rel='stylesheet' />
<!-- //lined-icons -->
</head>
<body>
   <div class="page-container">
   <!--/content-inner-->
<div class="row left-content container" style= "padding-top: 14px">

	<div class="form-group col-md-5 col-sm-12">
		<form class="form-group" action="" id="form-sanpham">
		<label for="tensanpham">Tên sản phẩm</label><br/>
		<input type="text" id="tensanpham" name="tensanpham" class="form-control" placeholder="nhập tên sản phẩm" />
		<br/>
		<label for="giatien">Giá tiền</label><br/>
		<input type="text" id="giatien" name="giatien" class="form-control" placeholder="nhập giá tiền" />
		<br/>
		<span>Dành cho</span><br/>
		<label class="radio-inline">
	      <input type="radio" name="danhcho" id="rd-nam" value="nam">&nbsp;Nam&emsp;
	    </label>
	    <label class="radio-inline">
	      <input type="radio" name="danhcho" id="rd-nu" value="nu">&nbsp;Nữ
	    </label>
		<br/>
		<label for="danhmucsanpham">Danh mục</label>
		<select name="danhmucsanpham" class="form-control" id="danhmucsanpham">
			<c:forEach var="valuedanhmuc" items="${danhmuc }">
				<option value="${valuedanhmuc.getMadanhmuc() }">${valuedanhmuc.getTendanhmuc() }</option>
			</c:forEach>
		</select>
		<br/>
		<label for="mota">Mô tả</label><br/>
		<textarea rows="5" type="text" id="mota" name="mota" class="form-control" placeholder="nhập mô tả"></textarea>
		<br/>
		<label for="hinhsanpham">Hình sản phẩm</label><br/>
		<input type="file" id="hinhsanpham" name="hinhsanpham" class="form-control" />
		<br/>
		
		<div id="chitietsanpham" class="chitietsanpham">
			<span>Chi tiết</span><br/>
			<select name="mausanpham" class="form-control" id="mausanpham">
				<c:forEach var="valuemau" items="${listmau }">
					<option value="${valuemau.getMamau() }">${valuemau.getTenmau() }</option>
				</c:forEach>
			</select>
			<br/>
			<select name="sizesanpham" class="form-control" id="sizesanpham">
				<c:forEach var="valuesize" items="${listsize }">
					<option value="${valuesize.getMasize() }">${valuesize.getSize() }</option>
				</c:forEach>
			</select>
			<br/>
			<label for="soluong">Số lượng</label><br/>
			<input type="number" value="1" min="1" id="soluong" name="soluong" class="form-control" placeholder="nhập số lượng" />
			<br/>
			<button type="button" class="btn btn-primary btn-chitiet">Thêm chi tiết</button>
		</div>
		
		<div class="containerchitietsanpham">
			<div class="chitietsanpham">
				<span>Chi tiết</span><br/>
				<select name="mausanpham" class="form-control" id="mausanpham">
					<c:forEach var="valuemau" items="${listmau }">
						<option value="${valuemau.getMamau() }">${valuemau.getTenmau() }</option>
					</c:forEach>
				</select>
				<br/>
				<select name="sizesanpham" class="form-control" id="sizesanpham">
					<c:forEach var="valuesize" items="${listsize }">
						<option value="${valuesize.getMasize() }">${valuesize.getSize() }</option>
					</c:forEach>
				</select>
				<br/>
				<label for="soluong">Số lượng</label><br/>
				<input type="number" value="1" min="1" id="soluong" name="soluong" class="form-control" placeholder="nhập số lượng" />
				<br/>
				<button type="button" class="btn btn-primary btn-chitiet">Thêm chi tiết</button>
			</div>
		</div>
		</form>
		<br/>
	    <button type="button" id="btnThemSanPham" class="btn btn-primary">Thêm sản phẩm</button>
	    <button type="button" id="btnCapNhatSanPham" class="btn btn-primary hidden">Cập nhật</button>
		<button type="button" id="btnThoat" class="btn btn-primary hidden">Thoát</button>
	</div>
	<div class="col-md-7 col-sm-12">
		<div style = "flex: 1;display: flex;justify-content: space-between;align-items: center;">
			<h4>SẢN PHẨM</h4>
			
		</div>
		<table id="table-sanpham" class="table">
			<thead style="border-bottom: 1px solid">
				<tr>
					<td>
						<div class="form-check">
						  <label><input type="checkbox" value="" id="checkall"></label>
						</div>
					</td>
					<td>Tên sản phẩm</td>
					<td>Giá tiền</td>
					<td>Giành cho</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="value" items="${listSanPham }">
				  	<tr>
				  	<td>
						<div class="form-check">
						  <label><input class="checkboxsanpham" type="checkbox" value="${value.getMasanpham() }"></label>
						</div>
					</td>
				      <td class="tensp" data-masp="${value.getMasanpham() }">${value.getTensanpham() }</td>
				      <td class="giatien">${value.getGiatien() }</td>
				      <td class="danhcho">${value.getDanhcho() }</td>
				      <td class="btn btn-warning capnhatsanpham" data-id="${value.getMasanpham() }">Sửa</td>
				    </tr>
				</c:forEach>
			</tbody>
		</table>
		<nav aria-label="...">
		  <ul class="pagination">
		  	<c:forEach var = "i" begin = "1" end = "${tongsopage }">
		  		<c:choose>
		  			<c:when test="${i==1 }">
		  				<li class="page-item active"><a class="page-link" href="#">${i }</a></li>
		  			</c:when>
		  			<c:otherwise>
		  				<li class="page-item"><a class="page-link" href="#">${i }</a></li>
		  			</c:otherwise>
		  		</c:choose>
      		</c:forEach>
		  </ul>
		</nav>
		<button style="background-color: red;" type="button" id="xoa-sanpham" class="btn btn-info">Xóa</button>
	</div>
</div>
  <!--//content-inner-->
			<!--/sidebar-menu-->
				<div class="sidebar-menu">
					<header class="logo1">
						<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> 
					</header>
						<div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
                           <div class="menu">
									<ul id="menu" >
										<li><a href="index.html"><i class="fa fa-tachometer"></i> <span>Dashboard</span><div class="clearfix"></div></a></li>
										
										
										 <li id="menu-academico" ><a href='<c:url value = "/themsanpham"/>'><i class="fa fa-cube nav_icon" aria-hidden="true"></i><span>Sản Phẩm</span><div class="clearfix"></div></a></li>
									<li><a href="gallery.html"><i class="fa fa-picture-o" aria-hidden="true"></i><span>Gallery</span><div class="clearfix"></div></a></li>
									<li id="menu-academico" ><a href="charts.html"><i class="fa fa-bar-chart"></i><span>Charts</span><div class="clearfix"></div></a></li>
									 <li id="menu-academico" ><a href="#"><i class="fa fa-list-ul" aria-hidden="true"></i><span> Short Codes</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										   <ul id="menu-academico-sub" >
										   <li id="menu-academico-avaliacoes" ><a href="icons.html">Icons</a></li>
											<li id="menu-academico-avaliacoes" ><a href="typography.html">Typography</a></li>
											<li id="menu-academico-avaliacoes" ><a href="faq.html">Faq</a></li>
										  </ul>
										</li>
									<li id="menu-academico" ><a href="errorpage.html"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i><span>Error Page</span><div class="clearfix"></div></a></li>
									  <li id="menu-academico" ><a href="#"><i class="fa fa-cogs" aria-hidden="true"></i><span> UI Components</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										   <ul id="menu-academico-sub" >
										   <li id="menu-academico-avaliacoes" ><a href="button.html">Buttons</a></li>
											<li id="menu-academico-avaliacoes" ><a href="grid.html">Grids</a></li>
										  </ul>
										</li>
									 <li><a href="tabels.html"><i class="fa fa-table"></i>  <span>Tables</span><div class="clearfix"></div></a></li>
									<li><a href="maps.html"><i class="fa fa-map-marker" aria-hidden="true"></i>  <span>Maps</span><div class="clearfix"></div></a></li>
							        <li id="menu-academico" ><a href="#"><i class="fa fa-file-text-o"></i>  <span>Pages</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										 <ul id="menu-academico-sub" >
											<li id="menu-academico-boletim" ><a href="calendar.html">Calendar</a></li>
											<li id="menu-academico-avaliacoes" ><a href="signin.html">Sign In</a></li>
											<li id="menu-academico-avaliacoes" ><a href="signup.html">Sign Up</a></li>
											

										  </ul>
									 </li>
									<li><a href="#"><i class="fa fa-check-square-o nav_icon"></i><span>Forms</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
									  <ul>
										<li><a href="input.html"> Input</a></li>
										<li><a href="validation.html">Validation</a></li>
									</ul>
									</li>
								  </ul>
								</div>
							  </div>
							  <div class="clearfix"></div>		
							</div>
							<script>
							var toggle = true;
										
							$(".sidebar-icon").click(function() {                
							  if (toggle)
							  {
								$(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
								$("#menu span").css({"position":"absolute"});
							  }
							  else
							  {
								$(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
								setTimeout(function() {
								  $("#menu span").css({"position":"relative"});
								}, 400);
							  }
											
											toggle = !toggle;
										});
							</script>
<!--js -->
<script src='<c:url value="/resources/JS/jquery.nicescroll.js"/>'></script>
<script src='<c:url value="/resources/JS/scripts.js"/>'></script>
<script src='<c:url value="/resources/JS/custom.js"/>'></script>
<!-- Bootstrap Core JavaScript -->
<script src='<c:url value="/resources/JS/bootstrap.min.js"/>'></script>
<!-- /Bootstrap Core JavaScript -->	   
<!-- morris JavaScript -->
<script src='<c:url value="/resources/JS/raphael-min.js"/>'></script>
<script src='<c:url value="/resources/JS/morris.js"/>'></script>
<script>
	$(document).ready(function() {
		//BOX BUTTON SHOW AND CLOSE
	   jQuery('.small-graph-box').hover(function() {
		  jQuery(this).find('.box-button').fadeIn('fast');
	   }, function() {
		  jQuery(this).find('.box-button').fadeOut('fast');
	   });
	   jQuery('.small-graph-box .box-close').click(function() {
		  jQuery(this).closest('.small-graph-box').fadeOut(200);
		  return false;
	   });
	   
	    //CHARTS
	    function gd(year, day, month) {
			return new Date(year, month - 1, day).getTime();
		}
		
		graphArea2 = Morris.Area({
			element: 'hero-area',
			padding: 10,
        behaveLikeLine: true,
        gridEnabled: false,
        gridLineColor: '#dddddd',
        axes: true,
        resize: true,
        smooth:true,
        pointSize: 0,
        lineWidth: 0,
        fillOpacity:0.85,
			data: [
				{period: '2014 Q1', iphone: 2668, ipad: null, itouch: 2649},
				{period: '2014 Q2', iphone: 15780, ipad: 13799, itouch: 12051},
				{period: '2014 Q3', iphone: 12920, ipad: 10975, itouch: 9910},
				{period: '2014 Q4', iphone: 8770, ipad: 6600, itouch: 6695},
				{period: '2015 Q1', iphone: 10820, ipad: 10924, itouch: 12300},
				{period: '2015 Q2', iphone: 9680, ipad: 9010, itouch: 7891},
				{period: '2015 Q3', iphone: 4830, ipad: 3805, itouch: 1598},
				{period: '2015 Q4', iphone: 15083, ipad: 8977, itouch: 5185},
				{period: '2016 Q1', iphone: 10697, ipad: 4470, itouch: 2038},
				{period: '2016 Q2', iphone: 8442, ipad: 5723, itouch: 1801}
			],
			lineColors:['#ff4a43','#a2d200','#22beef'],
			xkey: 'period',
            redraw: true,
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['All Visitors', 'Returning Visitors', 'Unique Visitors'],
			pointSize: 2,
			hideHover: 'auto',
			resize: true
		});
		
	   
	});
	</script>
</body>
</html>