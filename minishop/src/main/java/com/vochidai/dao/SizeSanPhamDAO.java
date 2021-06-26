package com.vochidai.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.vochidai.daoimp.SizeSanPhamImp;
import com.vochidai.entity.SanPham;
import com.vochidai.entity.SizeSanPham;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SizeSanPhamDAO implements SizeSanPhamImp{

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<SizeSanPham> LayDanhSachSize() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from SizeSanPham";
		List<SizeSanPham> listSizeSanPham = session.createQuery(sql).getResultList();
		
		return listSizeSanPham;
	}
	
	
	
}
