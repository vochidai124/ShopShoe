package com.vochidai.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.vochidai.daoimp.DanhMucImp;
import com.vochidai.entity.DanhMucSanPham;
import com.vochidai.entity.SanPham;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DanhMucDAO implements DanhMucImp{

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<DanhMucSanPham> LayDanhMuc() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from DanhMucSanPham";
		List<DanhMucSanPham> danhMucSanPhams = session.createQuery(sql).getResultList();
		return danhMucSanPhams;
	}
	
	
	

}
