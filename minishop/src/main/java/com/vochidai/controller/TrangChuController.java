package com.vochidai.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vochidai.entity.NhanVien;
import com.vochidai.entity.SanPham;

@Controller
@RequestMapping("/")
public class TrangChuController{
	
	@Autowired
	SessionFactory sessionFactory;
/*	@GetMapping
	@Transactional
	public String Default(){
		
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM NhanVien";
		List<NhanVien> list = session.createQuery(sql, NhanVien.class).getResultList();
		
		for(NhanVien nv : list) {
			System.out.println("ten nhan vien " + nv.getTennhanvien());
		}
		
		return "trangchu";
	}
	*/
	@GetMapping
	@Transactional
	public String Default(){
		Session session = sessionFactory.getCurrentSession();
		
		SanPham sanPham = new SanPham();
		sanPham.setTensanpham("ga ran");
		sanPham.setGiatien("100.000 VND");
		
		NhanVien nhanVien = new NhanVien();
		nhanVien.setTennhanvien("che cong binh");
		nhanVien.setTuoi(18);
		
		sanPham.setNhanvien(nhanVien);
		session.save(sanPham);
		
		return "trangchu";
	}

}
