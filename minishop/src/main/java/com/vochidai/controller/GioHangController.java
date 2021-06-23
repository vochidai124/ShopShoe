package com.vochidai.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vochidai.entity.ChiTietHoaDon;
import com.vochidai.entity.ChiTietHoaDonid;
import com.vochidai.entity.GioHang;
import com.vochidai.entity.HoaDon;
import com.vochidai.service.ChiTietHoaDonService;
import com.vochidai.service.HoaDonService;

@Controller
@RequestMapping("/giohang")
public class GioHangController {

	@Autowired
	HoaDonService hoaDonService;
	
	@Autowired
	ChiTietHoaDonService chiTietHoaDonService;
	
	@GetMapping
	public String Default(HttpSession httpSession, ModelMap modelMap) {
		
		if(httpSession.getAttribute("giohang") != null) {
			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluongspgiohang", gioHangs.size());
			modelMap.addAttribute("giohang", gioHangs);
		}
		
		return "giohang";
	}
	
	@PostMapping
	public String ThemHoaDon(@RequestParam String tenkhachhang, @RequestParam String sodt, @RequestParam String diachigiaohang, @RequestParam String hinhthucgiaohang, @RequestParam String ghichu, HttpSession httpSession) {
		
		if(httpSession.getAttribute("giohang") != null) {
			List<GioHang> gioHangs = (List<GioHang>) httpSession.getAttribute("giohang");
			
			HoaDon hoaDon = new HoaDon();
			hoaDon.setTenkhachhang(tenkhachhang);
			hoaDon.setDiachigiaohang(diachigiaohang);
			hoaDon.setSodt(sodt);
			hoaDon.setGhichu(ghichu);
			hoaDon.setHinhthucgiaohang(hinhthucgiaohang);
			
			int idHoaDon = hoaDonService.ThemHoaDon(hoaDon);
			if(idHoaDon > 0) {
				Set<ChiTietHoaDon> listChiTietHoaDons = new HashSet<ChiTietHoaDon>();
				for(GioHang gioHang : gioHangs) {
					ChiTietHoaDonid chiTietHoaDonid = new ChiTietHoaDonid();
					chiTietHoaDonid.setMachitietsanpham(gioHang.getMachitiet());
					chiTietHoaDonid.setMahoadon(hoaDon.getMahoadon());
					
					ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
					chiTietHoaDon.setChiTietHoaDonid(chiTietHoaDonid);
					chiTietHoaDon.setGiatien(gioHang.getGiatien().toString());
					chiTietHoaDon.setSoluong(gioHang.getSoluong());
					
					chiTietHoaDonService.ThemChiTietHoaDon(chiTietHoaDon);
				}
			}else {
				System.out.println("them that bai");
			}
			
		}
	
		return "giohang";
	}
	
}
