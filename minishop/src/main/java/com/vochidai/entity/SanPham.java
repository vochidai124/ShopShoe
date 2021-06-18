package com.vochidai.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sanpham")
public class SanPham {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int masanpham;
	String tensanpham;
	String giatien;
	String mota;
	String hinhsanpham;
	String danhcho;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "madanhmuc")
	DanhMucSanPham danhmucsanpham;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "masanpham")
	Set<ChiTietSanPham> chitietsanpham;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "chitietsanpham",
	joinColumns = {@JoinColumn(name = "masanpham", referencedColumnName = "masanpham")},
	inverseJoinColumns = {@JoinColumn(name = "makhuyenmai", referencedColumnName = "makhuyenmai")})
	Set<KhuyenMai> danhsachkhuyenmai;
	
	public Set<ChiTietSanPham> getChitietsanpham() {
		return chitietsanpham;
	}
	public void setChitietsanpham(Set<ChiTietSanPham> chitietsanpham) {
		this.chitietsanpham = chitietsanpham;
	}
	public Set<KhuyenMai> getDanhsachkhuyenmai() {
		return danhsachkhuyenmai;
	}
	public void setDanhsachkhuyenmai(Set<KhuyenMai> danhsachkhuyenmai) {
		this.danhsachkhuyenmai = danhsachkhuyenmai;
	}
	public String getDanhcho() {
		return danhcho;
	}
	public void setDanhcho(String danhcho) {
		this.danhcho = danhcho;
	}

	public int getMasanpham() {
		return masanpham;
	}
	public void setMasanpham(int masanpham) {
		this.masanpham = masanpham;
	}
	public DanhMucSanPham getDanhmucsanpham() {
		return danhmucsanpham;
	}
	public void setDanhmucsanpham(DanhMucSanPham danhmucsanpham) {
		this.danhmucsanpham = danhmucsanpham;
	}
	public String getTensanpham() {
		return tensanpham;
	}
	public void setTensanpham(String tensanpham) {
		this.tensanpham = tensanpham;
	}
	public String getGiatien() {
		return giatien;
	}
	public void setGiatien(String giatien) {
		this.giatien = giatien;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getHinhsanpham() {
		return hinhsanpham;
	}
	public void setHinhsanpham(String hinhsanpham) {
		this.hinhsanpham = hinhsanpham;
	}
	
}
