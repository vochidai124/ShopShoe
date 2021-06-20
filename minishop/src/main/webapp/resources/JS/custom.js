$(document).ready(function() {
	
	$("#btnDangNhap").click(function() {
		var ten = $("#email").val();
		var password = $("#matkhau").val();
		$.ajax({
			url: "/minishop/api/KiemTraDangNhap",
			type: "GET",
			data: {
				email: ten,
				matkhau: password
			},
			success: function(value) {
				if(value == "true"){
					duongDanHienTai = window.location.href
					duongDan = duongDanHienTai.replace("dangnhap/","");
					window.location = duongDan;
				}
				else{
					$("#ketqua").text("thatbai");
				}
		}
		});
	});
});
	
	$("#dangnhap").click(function(){
		$(this).addClass("actived");
		$("#dangky").removeClass("actived");
		$(".container-login-form").show();
		$(".container-signup-form").css("display","none");
	});
	$("#dangky").click(function(){
		$(this).addClass("actived");
		$("#dangnhap").removeClass("actived");
		$(".container-login-form").css("display","none");
		$(".container-signup-form").show();
	});
	
	$(".btn-cart").click(function(){
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var tenmau = $(this).closest("tr").find(".mau").text();
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var tensize = $(this).closest("tr").find(".size").text();
		var soluong = $(this).closest("tr").find(".soluong").text();
		var tensp = $("#tensp").text();
		var masp = $("#tensp").attr("data-masp");
		var giatien = $("#giatien").attr("data-value");
		
		$.ajax({
			url: "/minishop/api/ThemGioHang",
			type: "GET",
			data: {
				masp: masp,
				masize: masize,
				mamau: mamau,
				soluong: soluong,
				tensp: tensp,
				giatien: giatien,
				tenmau: tenmau,
				tensize: tensize
			},
			success: function(value) {
				$(".header__cart-wrap").find("span").addClass("header__cart-notice");
				$(".header__cart-wrap").find("span").html(value);
			}
		})/*.done(function(){
			$.ajax({
			url: "/minishop/api/LaySoLuongGioHang",
			type: "GET",
			success: function(value) {
				$(".header__cart-wrap").find("span").addClass("header__cart-notice");
				$(".header__cart-wrap").find("span").html(value);
			}
			
		})
		});*/
		
});
