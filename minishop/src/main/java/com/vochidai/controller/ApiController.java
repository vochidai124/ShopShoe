package com.vochidai.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vochidai.entity.ChiTietSanPham;
import com.vochidai.entity.DanhMucSanPham;
import com.vochidai.entity.GioHang;
import com.vochidai.entity.JSON_SanPham;
import com.vochidai.entity.MauSanPham;
import com.vochidai.entity.SanPham;
import com.vochidai.entity.SizeSanPham;
import com.vochidai.service.NhanVienService;
import com.vochidai.service.SanPhamService;

@Controller
@RequestMapping("api/")
@SessionAttributes({"email", "giohang"})
public class ApiController {
	@Autowired
	NhanVienService nhanVienService;
	
	@Autowired
	SanPhamService sanPhamService;
	
	List<GioHang> gioHangs = new ArrayList<GioHang>();
	
	@GetMapping("KiemTraDangNhap")
	@ResponseBody
	public String XuLyDangNhap(@RequestParam String email, @RequestParam String matkhau, ModelMap modelMap) {

		if(email.equals("admin@gmail.com")&&matkhau.equals("1234")) {
			System.out.println(email);
			return ""+email;
		}else {
		boolean kiemtra = nhanVienService.KiemTraDangNhap(email, matkhau);
		modelMap.addAttribute("email",email);
		
		return ""+kiemtra;
		}
	}
	
	@GetMapping("CapNhatGioHang")
	@ResponseBody
	public void CapNhatGioHang(HttpSession httpSession, @RequestParam int soluong, @RequestParam int masp, @RequestParam int mamau, @RequestParam int masize) {
		if(null != httpSession.getAttribute("giohang")) {
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = KiemTraSanPhamDaTonTaiGioHang(listGioHangs, httpSession, masp, masize, mamau);
			listGioHangs.get(vitri).setSoluong(soluong);
		}
	}
	
	@GetMapping("XoaGioHang")
	@ResponseBody
	public void XoaGioHang(HttpSession httpSession, @RequestParam int masp, @RequestParam int mamau, @RequestParam int masize) {
		if(null != httpSession.getAttribute("giohang")) {
			List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = KiemTraSanPhamDaTonTaiGioHang(listGioHangs, httpSession, masp, masize, mamau);
			listGioHangs.remove(vitri);
		}
	}
	
	@GetMapping("ThemGioHang")
	@ResponseBody
	public String ThemGioHang(@RequestParam int masp, @RequestParam int masize, @RequestParam int mamau,
			@RequestParam String tensp, @RequestParam String giatien, @RequestParam String tenmau,
			@RequestParam String tensize, @RequestParam int soluong, @RequestParam int machitiet, HttpSession httpSession) {
		if(httpSession.getAttribute("giohang") == null) {
		GioHang gioHang = new GioHang();
		gioHang.setMasp(masp);
		gioHang.setMasize(masize);
		gioHang.setMamau(mamau);
		gioHang.setTensp(tensp);
		gioHang.setTensize(tensize);
		gioHang.setTenmau(tenmau);
		gioHang.setGiatien(giatien);
		gioHang.setSoluong(1);
		gioHang.setMachitiet(machitiet);
		
		gioHangs.add(gioHang);
		httpSession.setAttribute("giohang", gioHangs);

		return gioHangs.size() + "";
		
	} else {
		List<GioHang> listGioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
		int vitri=KiemTraSanPhamDaTonTaiGioHang(listGioHangs, httpSession, masp, masize, mamau);
		if(vitri==-1) {
			GioHang gioHang = new GioHang();
			gioHang.setMasp(masp);
			gioHang.setMasize(masize);
			gioHang.setMamau(mamau);
			gioHang.setTensp(tensp);
			gioHang.setTensize(tensize);
			gioHang.setTenmau(tenmau);
			gioHang.setGiatien(giatien);
			gioHang.setSoluong(1);
			gioHang.setMachitiet(machitiet);
			
			listGioHangs.add(gioHang);
			
		}else {
			int soluongmoi = listGioHangs.get(vitri).getSoluong() + 1;
			listGioHangs.get(vitri).setSoluong(soluongmoi);
		}
		return listGioHangs.size() + "";
	}
	
}

	private int KiemTraSanPhamDaTonTaiGioHang(List<GioHang> listGioHangs ,HttpSession httpSession, int masp, int masize, int mamau) {

		for (int i = 0; i < listGioHangs.size(); i++) {
			if (listGioHangs.get(i).getMasp() == masp && listGioHangs.get(i).getMamau() == mamau
					&& listGioHangs.get(i).getMasize() == masize) {
				return i;
			}
		}
		return -1;
	}
//	@GetMapping("LaySoLuongGioHang")
//	@ResponseBody
//	public String LaySoLuongGioHang(HttpSession httpSession) {
//		if(httpSession.getAttribute("giohang") != null) {
//			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
//			return gioHangs.size() + "";
//		}
//		return "";
//	}
	
	@GetMapping(path="LaySanPhamLimit",produces="text/plain; charset=utf-8")
	@ResponseBody
	public String LaySanPhamLimit(@RequestParam int spbatdau) {
		
		String html = "";
		List<SanPham> listSanPhams = sanPhamService.LayDanhSachSanPham(spbatdau);
		for (SanPham sanPham : listSanPhams) {
			html += "<tr>";
			html += "<td><div class='form-check'><label><input class='checkboxsanpham' type='checkbox' value='"+sanPham.getMasanpham()+"'></label></div></td>";
			html += "<td class='tensp' data-masp='"+sanPham.getMasanpham()+"'>"+sanPham.getTensanpham() +"</td>";
			html += "<td class='giatien'>"+sanPham.getGiatien() +"</td>";
			html += "<td class='danhcho'>"+sanPham.getDanhcho() +"</td>";
			html += "<td class='btn btn-warning capnhatsanpham' data-id='" + sanPham.getMasanpham() + "'>Sửa</td>";
					
			html += "</tr>";
		}
		
		return html;
	}
	
	@GetMapping(path="PhanTrangTrangChu",produces="text/plain; charset=utf-8")
	@ResponseBody
	public String PhanTrangTrangChu(@RequestParam int spbatdau) {
		
		String html = "";
		List<SanPham> listSanPhams = sanPhamService.LayDanhSachSanPham(spbatdau);
		for (SanPham sanPham : listSanPhams) {
//			html += "<tr>";
//			html += "<td><div class='form-check'><label><input class='checkboxsanpham' type='checkbox' value='"+sanPham.getMasanpham()+"'></label></div></td>";
//			html += "<td class='tensp' data-masp='"+sanPham.getMasanpham()+"'>"+sanPham.getTensanpham() +"</td>";
//			html += "<td class='giatien'>"+sanPham.getGiatien() +"</td>";
//			html += "<td class='danhcho'>"+sanPham.getDanhcho() +"</td>";
//			html += "<td class='btn btn-warning capnhatsanpham' data-id='" + sanPham.getMasanpham() + "'>Sửa</td>";
//					
//			html += "</tr>";
			html += "<div class='grid__column-2-4'>";
			html += "<a class='home-product-item' href='chitiet/" + sanPham.getMasanpham() + "'>'";
			html += "<div class='home-product-item__img' style='background-image: url(./resources/image/sanpham/"+sanPham.getHinhsanpham()+");'></div>";
			html += "<h4 class='home-product-item__name'>"+sanPham.getTensanpham() +"</h4>";
			html += "<div class='home-product-item__price'>";
			html += "<span class='home-product-item__price-old'>1000.000đ</span>";
			html += "<span class='home-product-item__price-current'>"+sanPham.getGiatien()+" VNĐ</span>";
			html +="</div>";
			html +="<div class='home-product-item__action'><span class='home-product-item__like home-product-item__like--liked'><i class='home-product-item__like-icon-empty far fa-heart'></i><i class='home-product-item__like-icon-fill fas fa-heart'></i></span><div class='home-product-item__rating'><i class='home-product-item__star--gold fas fa-star'></i><i class='home-product-item__star--gold fas fa-star'></i><i class='home-product-item__star--gold fas fa-star'></i><i class='home-product-item__star--gold fas fa-star'></i><i class='fas fa-star'></i></div><div class='home-product-item__sold'>88 đã bán</div></div><div class='home-product-item__origin'><span class='home-product-item__brand'>Whoo</span><span class='home-product-item__origin-name'>Nhật Bản</span></div><div class='home-product-item__favourite'><i class='fas fa-check'></i><span>Yêu Thích</span></div><div class='home-product-item__sale-off'><span class='home-product-item__sale-off-percent'>30%</span><span class='home-product-item__sale-off-label'>GIẢM</span></div></a></div>";
		}
		return html;
	}
	
	@GetMapping("XoaSanPham")
	@ResponseBody
	public String XoaSanPhamTheoMaSanPham(@RequestParam int masanpham) {
		sanPhamService.XoaSanPhamTheoMaSanPham(masanpham);
		
		return "true";
		
	}
	
	@Autowired
	ServletContext context;
	
	@PostMapping("UploadFile")
	@ResponseBody
	public String UploadFile(MultipartHttpServletRequest request) {
		
		String path_save_file = context.getRealPath("/resources/image/sanpham/");
		
		Iterator<String> listNames = request.getFileNames();
		
		MultipartFile mpf = request.getFile(listNames.next());
		
		File file_save = new File(path_save_file + mpf.getOriginalFilename());
		try {
			mpf.transferTo(file_save);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "true";
	}
	@PostMapping("ThemSanPham")
	@ResponseBody
	public void ThemSanPham(@RequestParam String dataJson) {

		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode jsonObject;
		try {
			SanPham sanPham = new SanPham();
			jsonObject = objectMapper.readTree(dataJson);
			
			DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
			danhMucSanPham.setMadanhmuc(jsonObject.get("danhmucsanpham").asInt());
			
			JsonNode jsonChitiet = jsonObject.get("chitietsanpham");
			Set<ChiTietSanPham> listChitiet = new HashSet<ChiTietSanPham>();
			for (JsonNode objectChitiet : jsonChitiet) {
				
				ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
				
				MauSanPham mauSanPham = new MauSanPham();
				mauSanPham.setMamau(objectChitiet.get("mausanpham").asInt());
				
				SizeSanPham sizeSanPham = new SizeSanPham();
				sizeSanPham.setMasize(objectChitiet.get("sizesanpham").asInt());
				
				chiTietSanPham.setMausanpham(mauSanPham);
				chiTietSanPham.setSizesanpham(sizeSanPham);
				chiTietSanPham.setSoluong(objectChitiet.get("soluong").asInt());
				
				listChitiet.add(chiTietSanPham);
				
			}
			
			String tensanpham = jsonObject.get("tensanpham").asText();
			String giatien = jsonObject.get("giatien").asText();
			String mota = jsonObject.get("mota").asText();
			String hinhsanpham = jsonObject.get("hinhsanpham").asText();
			String danhcho = jsonObject.get("danhcho").asText();
			
			sanPham.setChitietsanpham(listChitiet);
			sanPham.setDanhmucsanpham(danhMucSanPham);
			sanPham.setGiatien(giatien);
			sanPham.setHinhsanpham(hinhsanpham);
			sanPham.setTensanpham(tensanpham);
			sanPham.setMota(mota);
			sanPham.setDanhcho(danhcho);
			
			sanPhamService.ThemSanPham(sanPham);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("CapNhatSanPham")
	@ResponseBody
	public void CapNhatSanPham(@RequestParam String dataJson) {

		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode jsonObject;
		try {
			SanPham sanPham = new SanPham();
			jsonObject = objectMapper.readTree(dataJson);
			
			DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
			danhMucSanPham.setMadanhmuc(jsonObject.get("danhmucsanpham").asInt());
			
			JsonNode jsonChitiet = jsonObject.get("chitietsanpham");
			Set<ChiTietSanPham> listChitiet = new HashSet<ChiTietSanPham>();
			for (JsonNode objectChitiet : jsonChitiet) {
				
				ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
				
				MauSanPham mauSanPham = new MauSanPham();
				mauSanPham.setMamau(objectChitiet.get("mausanpham").asInt());
				
				SizeSanPham sizeSanPham = new SizeSanPham();
				sizeSanPham.setMasize(objectChitiet.get("sizesanpham").asInt());
				
				chiTietSanPham.setMausanpham(mauSanPham);
				chiTietSanPham.setSizesanpham(sizeSanPham);
				chiTietSanPham.setSoluong(objectChitiet.get("soluong").asInt());
				
				listChitiet.add(chiTietSanPham);
				
			}
			
			String tensanpham = jsonObject.get("tensanpham").asText();
			String giatien = jsonObject.get("giatien").asText();
			String mota = jsonObject.get("mota").asText();
			String hinhsanpham = jsonObject.get("hinhsanpham").asText();
			String danhcho = jsonObject.get("danhcho").asText();
			int masanpham = jsonObject.get("masanpham").asInt();
			
			sanPham.setChitietsanpham(listChitiet);
			sanPham.setDanhmucsanpham(danhMucSanPham);
			sanPham.setGiatien(giatien);
			sanPham.setHinhsanpham(hinhsanpham);
			sanPham.setTensanpham(tensanpham);
			sanPham.setMota(mota);
			sanPham.setDanhcho(danhcho);
			sanPham.setMasanpham(masanpham);
			
			sanPhamService.CapNhatSanPham(sanPham);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="LayDanhSachSanPhamTheoMa", produces="application/json; charset=utf-8")
	@ResponseBody
	public JSON_SanPham LayDanhSachSanPhamTheoMa(@RequestParam int masanpham){
		JSON_SanPham json_SanPham = new JSON_SanPham();
		
		SanPham sanPham = sanPhamService.LayDanhSachChiTietSanPhamTheoMa(masanpham);
		
		json_SanPham.setMasanpham(sanPham.getMasanpham());
		json_SanPham.setTensanpham(sanPham.getTensanpham());
		json_SanPham.setGiatien(sanPham.getGiatien());
		json_SanPham.setMota(sanPham.getMota());
		json_SanPham.setHinhsanpham(sanPham.getHinhsanpham());
		json_SanPham.setDanhcho(sanPham.getDanhcho());
		
		DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
		danhMucSanPham.setMadanhmuc(sanPham.getDanhmucsanpham().getMadanhmuc());
		danhMucSanPham.setTendanhmuc(sanPham.getDanhmucsanpham().getTendanhmuc());
			
		Set<ChiTietSanPham> chiTietSanPhams = new HashSet<ChiTietSanPham>();
		for (ChiTietSanPham value : sanPham.getChitietsanpham()) {
			ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
			
			chiTietSanPham.setMachitietsanpham(value.getMachitietsanpham());
			
			MauSanPham mauSanPham = new MauSanPham();
			mauSanPham.setMamau(value.getMausanpham().getMamau());
			mauSanPham.setTenmau(value.getMausanpham().getTenmau());
			
			chiTietSanPham.setMausanpham(mauSanPham);
			
			SizeSanPham sizeSanPham = new SizeSanPham();
			sizeSanPham.setMasize(value.getSizesanpham().getMasize());
			sizeSanPham.setSize(value.getSizesanpham().getSize());
			
			chiTietSanPham.setSizesanpham(sizeSanPham);
			chiTietSanPham.setSoluong(value.getSoluong());
			
			chiTietSanPhams.add(chiTietSanPham);
		}
		json_SanPham.setDanhmucsanpham(danhMucSanPham);
		json_SanPham.setChitietsanpham(chiTietSanPhams);
		
		return json_SanPham ;
	}
}