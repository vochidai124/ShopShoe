package com.vochidai.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vochidai.entity.GioHang;
import com.vochidai.entity.SanPham;
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

		boolean kiemtra = nhanVienService.KiemTraDangNhap(email, matkhau);
		modelMap.addAttribute("email",email);
		
		return ""+kiemtra;
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
			html += "</tr>";
		}
		
		return html;
	}
	@GetMapping("XoaSanPham")
	@ResponseBody
	public String XoaSanPhamTheoMaSanPham(@RequestParam int masanpham) {
		sanPhamService.XoaSanPhamTheoMaSanPham(masanpham);
		
		return "true";
		
	}
	
}