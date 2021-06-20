package com.vochidai.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vochidai.entity.GioHang;

@Controller
@RequestMapping("/giohang")
public class GioHangController {

	@GetMapping
	public String Default(HttpSession httpSession, ModelMap modelMap) {
		
		if(httpSession.getAttribute("giohang") != null) {
			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluongspgiohang", gioHangs.size());
			modelMap.addAttribute("giohang", gioHangs);
		}
		
		return "giohang";
	}
}
