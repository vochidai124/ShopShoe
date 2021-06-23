package com.vochidai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vochidai.dao.ChiTietHoaDonDAO;
import com.vochidai.daoimp.ChiTietHoaDonImp;
import com.vochidai.entity.ChiTietHoaDon;

@Service
public class ChiTietHoaDonService implements ChiTietHoaDonImp{
	
	@Autowired
	ChiTietHoaDonDAO chiTietHoaDonDAO;
	
	public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		
		return chiTietHoaDonDAO.ThemChiTietHoaDon(chiTietHoaDon);
	}

}
