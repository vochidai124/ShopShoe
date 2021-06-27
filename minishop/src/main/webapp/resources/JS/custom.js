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
		var machitiet = $(this).attr("data-machitiet");
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
				tensize: tensize,
				machitiet: machitiet
			},
			success: function(value) {
				$(".header__cart-wrap").find("span").addClass("header__cart-notice");
				$(".header__cart-wrap").find("span").html(value);
			}
		});/*.done(function(){
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
	
	GanTongTienGioHang();
	function GanTongTienGioHang(){
		var tongtiensp = 0;
		$(".giatien").each(function(){
			var giatien = $(this).closest("tr").find(".giatien").attr("data-value");
			var soluong = $(this).closest("tr").find(".soluong-giohang").val();
			giatien = parseFloat(giatien);
			soluong = parseFloat(soluong);
			
			if(giatien >=1 && giatien <=10){
				giatien = giatien*1000000;
			}
			if(giatien >=11 && giatien <=999){
				giatien = giatien*1000;
			}
			
			var tongtien = giatien*soluong;
			$(this).closest("tr").find(".giatien").html(new Intl.NumberFormat().format(tongtien));
			tongtiensp = tongtiensp + tongtien;
			$("#tongtien").html(new Intl.NumberFormat().format(tongtiensp));		
		})
	}
	
	$(".delete-product").click(function(){
		var self = $(this);
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var masp = $(this).closest("tr").find(".tensp").attr("data-masp");
		
		$.ajax({
			url: "/minishop/api/XoaGioHang",
			type: "GET",
			data: {
				masp: masp,
				masize: masize,
				mamau: mamau
			},
			success: function(value) {
				self.closest("tr").remove();
				GanTongTienGioHang();
			}
		});
	})
	
	$(".soluong-giohang").change(function(){
		var soluong = $(this).val();
		var giatien = $(this).closest("tr").find(".giatien").attr("data-value");
		giatien = parseFloat(giatien)*1000;
		var tongtien = soluong * giatien;
		$(this).closest("tr").find(".giatien").html(new Intl.NumberFormat().format(tongtien));
		GanTongTienGioHang();
		
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var masp = $(this).closest("tr").find(".tensp").attr("data-masp");
		
		soluong = parseInt(soluong);
		mamau = parseInt(mamau);
		masize = parseInt(masize);
		masp = parseInt(masp);
		$.ajax({
			url: "/minishop/api/CapNhatGioHang",
			type: "GET",
			data: {
				masp: masp,
				masize: masize,
				mamau: mamau,
				soluong: soluong
			},
			success: function(value) {
				
			}
		});
	})
	
	$("body").on("click",".page-item",function(){
		$(".page-item").removeClass("active");
		$(this).addClass("active");
		var sotrang = $(this).text();
		var spbatdau = (sotrang - 1) * 5;
		
		$.ajax({
			url: "/minishop/api/LaySanPhamLimit",
			type: "GET",
			data: {
				spbatdau: spbatdau
			},
			success: function(value) {
				var tbodysanpham = $("#table-sanpham").find("tbody");
				tbodysanpham.empty();
				tbodysanpham.append(value);
			}
		});
	})
	
	$("#checkall").change(function(){
		if(this.checked){
			$("#table-sanpham input").each(function(){
			$(this).attr("checked",true);
		})
		}else{
			$("#table-sanpham input").each(function(){
			$(this).attr("checked",false);
			})
		}
	})
	
	$("#xoa-sanpham").click(function(){
		$("#table-sanpham > tbody input:checked").each(function(){
			var masanpham = $(this).val();
			var This = $(this);
			This.closest("tr").remove();
		$.ajax({
			url: "/minishop/api/XoaSanPham",
			type: "GET",
			data: {
				masanpham: masanpham
			},
			success: function(value) {
				This.closest("tr").remove();
			}
		})
		
		})
	})
	
	var files = [];
	var tenhinh = "";
	$("#hinhanh").change(function(event){
		files = event.target.files;
		tenhinh = files[0].name;
		forms = new FormData();
		forms.append("file", files[0]);
		$.ajax({
			url: "/minishop/api/UploadFile",
			type: "POST",
			data: forms,
			contentType: false,
			processData: false,
			enctype: "multipart/form-data",

			success: function(value) {
			}
		})
	})
	$("body").on("click",".btn-chitiet",function(){
		$(this).remove();
		var chitietclone = $("#chitietsanpham").clone();
		chitietclone.removeAttr("id");
		$(".containerchitietsanpham").append(chitietclone);
	})
	
	$("#btnThemSanPham").click(function(event){
		event.preventDefault();
		var formdata = $("#form-sanpham").serializeArray();
		json = {};
		arrayChitiet = [];
		
		$.each(formdata, function(i, field){
				json[field.name] = field.value;
		});
		$(".containerchitietsanpham > .chitietsanpham").each(function(){
			objectChitiet = {};
			
			var mausanpham = $(this).find("#mausanpham").val();
			var sizesanpham = $(this).find("#sizesanpham").val();
			var soluong = $(this).find("#soluong").val();
			
			objectChitiet["mausanpham"] = mausanpham;
			objectChitiet["sizesanpham"] = sizesanpham;
			objectChitiet["soluong"] = soluong;
			
			arrayChitiet.push(objectChitiet);
		})
		json["chitietsanpham"] = arrayChitiet;
		json["hinhsanpham"] = tenhinh;
		$.ajax({
			url: "/minishop/api/ThemSanPham",
			type: "POST",
			data: {
				dataJson: JSON.stringify(json)
			},

			success: function(value) {
			
			}
		})
		
	})
});