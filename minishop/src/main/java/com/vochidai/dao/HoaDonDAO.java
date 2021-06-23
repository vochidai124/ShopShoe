package com.vochidai.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.vochidai.daoimp.HoaDonImp;
import com.vochidai.entity.HoaDon;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HoaDonDAO implements HoaDonImp{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public int ThemHoaDon(HoaDon hoaDon) {
		
		Session session = sessionFactory.getCurrentSession();
		int id = (Integer) session.save(hoaDon);
		if(id > 0) {
			return id;
		}else {
			return 0;
		}

	}

}
