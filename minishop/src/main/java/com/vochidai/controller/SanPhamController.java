package com.vochidai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vochidai.entity.DanhMucSanPham;
import com.vochidai.entity.SanPham;
import com.vochidai.service.DanhMucService;
import com.vochidai.service.SanPhamService;

@Controller
@RequestMapping("/sanpham")
public class SanPhamController {

	@Autowired
	DanhMucService danhMucService;
	
	@Autowired
	SanPhamService sanPhamService;
	
	@GetMapping("/{id}/{tendanhmuc}")
	public String Default(ModelMap modelMap, @PathVariable int id,@PathVariable String tendanhmuc) {
		
		List<SanPham> listSanPhams = sanPhamService.LayDanhSachSanPhamTheoMaDanhMuc(id);
		List<DanhMucSanPham> danhMucSanPhams = danhMucService.LayDanhMuc();
		
		modelMap.addAttribute("danhmuc", danhMucSanPhams);
		modelMap.addAttribute("tendanhmuc", tendanhmuc);
		modelMap.addAttribute("listSanPhams", listSanPhams);
		
		return "sanpham";
	}
	
}
