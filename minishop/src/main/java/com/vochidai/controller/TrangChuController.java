package com.vochidai.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vochidai.entity.NhanVien;

@Controller
@RequestMapping("/")
public class TrangChuController{
	
/*	@Autowired
	SessionFactory sessionFactory;
	@GetMapping
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
	public String Default(){
		
		return "trangchu";
	}
}