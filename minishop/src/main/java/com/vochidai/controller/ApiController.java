package com.vochidai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vochidai.service.NhanVienService;

@Controller
@RequestMapping("api/")
@SessionAttributes("email")
public class ApiController {
	@Autowired
	NhanVienService nhanVienService;
	
	@GetMapping("KiemTraDangNhap")
	@ResponseBody
	public String XuLyDangNhap(@RequestParam String email, @RequestParam String matkhau, ModelMap modelMap) {

		boolean kiemtra = nhanVienService.KiemTraDangNhap(email, matkhau);
		modelMap.addAttribute("email",email);
		
		return ""+kiemtra;
	}
}
