package com.vochidai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vochidai.dao.DanhMucDAO;
import com.vochidai.daoimp.DanhMucImp;
import com.vochidai.entity.DanhMucSanPham;

@Service
public class DanhMucService implements DanhMucImp{
	
	@Autowired
	DanhMucDAO danhMucDAO;

	public List<DanhMucSanPham> LayDanhMuc() {
		
		return danhMucDAO.LayDanhMuc();
	}

}
