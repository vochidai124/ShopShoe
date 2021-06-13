package com.vochidai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vochidai.dao.NhanVienDAO;
import com.vochidai.daoimp.NhanVienImp;

@Service
public class NhanVienService implements NhanVienImp{
	@Autowired
	NhanVienDAO nhanVienDao;

	public boolean KiemTraDangNhap(String email, String matkhau) {
		boolean kiemtra = nhanVienDao.KiemTraDangNhap(email,matkhau);
		return kiemtra;
	}
	
	
}
