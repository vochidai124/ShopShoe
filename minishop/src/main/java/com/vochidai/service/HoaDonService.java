package com.vochidai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vochidai.dao.HoaDonDAO;
import com.vochidai.daoimp.HoaDonImp;
import com.vochidai.entity.HoaDon;

@Service
public class HoaDonService implements HoaDonImp{

	@Autowired
	HoaDonDAO hoaDonDAO;

	public int ThemHoaDon(HoaDon hoaDon) {
		return hoaDonDAO.ThemHoaDon(hoaDon);
	}
	
}
