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

import com.vochidai.entity.DanhMucSanPham;
import com.vochidai.entity.SanPham;
import com.vochidai.service.DanhMucService;
import com.vochidai.service.NhanVienService;
import com.vochidai.service.SanPhamService;

@Controller
@RequestMapping("/")
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
		
		List<SanPham> listSanPhams = sanPhamService.LayDanhSachSanPham(0);
		modelMap.addAttribute("listSanPhams", listSanPhams);
		modelMap.addAttribute("danhmuc", danhMucSanPhams);
		
		return "trangchu";
	}
	
}
