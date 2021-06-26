package com.vochidai.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.vochidai.daoimp.MauSanPhamImp;
import com.vochidai.entity.MauSanPham;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MauSanPhamDAO implements MauSanPhamImp{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<MauSanPham> LayDanhSachMau() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from MauSanPham";
		List<MauSanPham> listMauSanPham = session.createQuery(sql).getResultList();
		return listMauSanPham;
	}

}
