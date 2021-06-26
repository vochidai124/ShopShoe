package com.vochidai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vochidai.dao.SizeSanPhamDAO;
import com.vochidai.daoimp.SizeSanPhamImp;
import com.vochidai.entity.SizeSanPham;

@Service
public class SizeSanPhamService implements SizeSanPhamImp{

	@Autowired
	SizeSanPhamDAO sizeSanPhamDAO;
	
	public List<SizeSanPham> LayDanhSachSize() {
		return sizeSanPhamDAO.LayDanhSachSize();
	}

}
