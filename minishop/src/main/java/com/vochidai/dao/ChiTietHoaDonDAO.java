package com.vochidai.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.vochidai.daoimp.ChiTietHoaDonImp;
import com.vochidai.entity.ChiTietHoaDon;
import com.vochidai.entity.ChiTietHoaDonid;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ChiTietHoaDonDAO implements ChiTietHoaDonImp{

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		Session session = sessionFactory.getCurrentSession();
		ChiTietHoaDonid id = (ChiTietHoaDonid) session.save(chiTietHoaDon);
		
		if(id != null) {
			return true;
		}else {
			return false;
		}
	}

}
