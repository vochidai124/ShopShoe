package com.vochidai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vochidai.dao.SanPhamDAO;
import com.vochidai.daoimp.SanPhamImp;
import com.vochidai.entity.SanPham;

@Service
public class SanPhamService implements SanPhamImp{

	@Autowired
	SanPhamDAO sanPhamDAO;
	
	public List<SanPham> LayDanhSachSanPham(int spbatdau) {
		
		return sanPhamDAO.LayDanhSachSanPham(spbatdau);
	}

}
