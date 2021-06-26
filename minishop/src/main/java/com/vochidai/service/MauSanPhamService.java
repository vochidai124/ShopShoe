package com.vochidai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vochidai.dao.MauSanPhamDAO;
import com.vochidai.daoimp.MauSanPhamImp;
import com.vochidai.entity.MauSanPham;

@Service
public class MauSanPhamService implements MauSanPhamImp{

	@Autowired
	MauSanPhamDAO mauSanPhamDAO;
	
	public List<MauSanPham> LayDanhSachMau() {
		return mauSanPhamDAO.LayDanhSachMau();
	}

}
