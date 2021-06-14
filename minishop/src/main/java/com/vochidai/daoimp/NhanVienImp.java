package com.vochidai.daoimp;

import com.vochidai.entity.NhanVien;

public interface NhanVienImp {
	boolean KiemTraDangNhap(String email, String matkhau);
	boolean ThemNhanVien(NhanVien nhanVien);
}
