package com.vochidai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vochidai.entity.DanhMucSanPham;
import com.vochidai.entity.SanPham;
import com.vochidai.service.DanhMucService;
import com.vochidai.service.SanPhamService;

@Controller
@RequestMapping("/themsanpham")
public class ThemSanPhamController {

	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired
	DanhMucService danhMucService;
	
	@GetMapping
	public String Default(ModelMap modelMap) {
		List<SanPham> listSanPhams = sanPhamService.LayDanhSachSanPham(0);
		List<SanPham> allSanPham = sanPhamService.LayDanhSachSanPham(-1);
		List<DanhMucSanPham> danhMucSanPhams = danhMucService.LayDanhMuc();
		double tongsopage = Math.ceil((double) allSanPham.size() / 5);
		
		modelMap.addAttribute("listSanPham", listSanPhams);
		modelMap.addAttribute("allSanPham", allSanPham);
		modelMap.addAttribute("tongsopage", tongsopage);
		modelMap.addAttribute("danhmuc", danhMucSanPhams);
				
		return "themsanpham";
	}
}
