package com.vochidai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vochidai.entity.DanhMucSanPham;
import com.vochidai.entity.GioHang;
import com.vochidai.entity.SanPham;
import com.vochidai.service.DanhMucService;
import com.vochidai.service.NhanVienService;
import com.vochidai.service.SanPhamService;

@Controller
@RequestMapping("/")
@SessionAttributes("giohang")
public class TrangChuController{
	
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	DanhMucService danhMucService;
	
	@GetMapping
	@Transactional
	public String Default(ModelMap modelMap,HttpSession httpSession) {
		
		List<DanhMucSanPham> danhMucSanPhams = danhMucService.LayDanhMuc();
		
		if(httpSession.getAttribute("email")!=null) {
			String temp=(String) httpSession.getAttribute("email");
			String chucaidau=temp.substring(0,1);
			modelMap.addAttribute("chucaidau",chucaidau);
		}
		
		if(httpSession.getAttribute("giohang") != null) {
			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluongspgiohang", gioHangs.size());
			modelMap.addAttribute("giohang", gioHangs);
		}
		
		List<SanPham> listSanPhams = sanPhamService.LayDanhSachSanPham(0);
		List<SanPham> allSanPham = sanPhamService.LayDanhSachSanPham(-1);
		
		double tongsopage = Math.ceil((double) allSanPham.size() / 10);
		
		modelMap.addAttribute("tongsopage", tongsopage);
		modelMap.addAttribute("listSanPhams", listSanPhams);
		modelMap.addAttribute("danhmuc", danhMucSanPhams);
		
		return "trangchu";
	}
	
}
