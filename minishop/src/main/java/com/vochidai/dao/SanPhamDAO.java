package com.vochidai.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.vochidai.daoimp.SanPhamImp;
import com.vochidai.entity.ChiTietSanPham;
import com.vochidai.entity.SanPham;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SanPhamDAO implements SanPhamImp{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<SanPham> LayDanhSachSanPham(int spbatdau) {
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> listSanPhams = (List<SanPham>) session.createQuery("from SanPham").setFirstResult(spbatdau).setMaxResults(10).getResultList();
		return listSanPhams;
	}
	
	@Transactional
	public SanPham LayDanhSachChiTietSanPhamTheoMa(int masanpham){
		Session session = sessionFactory.getCurrentSession();
		String sql = "from SanPham sp where sp.masanpham = " + masanpham;
		SanPham sanPham = (SanPham) session.createQuery(sql).getSingleResult();
		
		return sanPham;
		
		}

}
