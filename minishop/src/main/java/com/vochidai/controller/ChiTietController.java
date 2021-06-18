package com.vochidai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vochidai.entity.SanPham;
import com.vochidai.service.SanPhamService;

@Controller
@RequestMapping("/chitiet")
public class ChiTietController {
	@Autowired
	SanPhamService sanPhamService;
	
	@GetMapping("/{masanpham}")
	public String Default(@PathVariable int masanpham, ModelMap modelMap) {

		SanPham sanPham = sanPhamService.LayDanhSachChiTietSanPhamTheoMa(masanpham);
		modelMap.addAttribute("sanPham", sanPham);
		
		return "chitiet";
	}
	
}
