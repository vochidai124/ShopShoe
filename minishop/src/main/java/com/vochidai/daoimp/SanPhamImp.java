package com.vochidai.daoimp;

import java.util.List;

import com.vochidai.entity.SanPham;

public interface SanPhamImp {
	List<SanPham> LayDanhSachSanPham(int spbatdau);
	
	SanPham LayDanhSachChiTietSanPhamTheoMa(int masanpham);
	
	List<SanPham> LayDanhSachSanPhamTheoMaDanhMuc(int madanhmuc);
	
	boolean XoaSanPhamTheoMaSanPham(int masanpham);
	boolean ThemSanPham(SanPham sanPham);
	boolean CapNhatSanPham(SanPham sanPham);
}
