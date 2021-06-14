package com.vochidai.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "khuyenmai")
public class KhuyenMai {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int makhuyenmai;
	int giagiam;
	String tenkhuyenmai;
	String thoigianbatdau;
	String thoigianketthuc;
	String mota;
	String hinhkhuyenmai;

	
	
	public int getGiagiam() {
		return giagiam;
	}
	public void setGiagiam(int giagiam) {
		this.giagiam = giagiam;
	}
	public int getMakhuyenmai() {
		return makhuyenmai;
	}
	public void setMakhuyenmai(int makhuyenmai) {
		this.makhuyenmai = makhuyenmai;
	}
	public String getTenkhuyenmai() {
		return tenkhuyenmai;
	}
	public void setTenkhuyenmai(String tenkhuyenmai) {
		this.tenkhuyenmai = tenkhuyenmai;
	}
	public String getThoigianbatdau() {
		return thoigianbatdau;
	}
	public void setThoigianbatdau(String thoigianbatdau) {
		this.thoigianbatdau = thoigianbatdau;
	}
	public String getThoigianketthuc() {
		return thoigianketthuc;
	}
	public void setThoigianketthuc(String thoigianketthuc) {
		this.thoigianketthuc = thoigianketthuc;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getHinhkhuyenmai() {
		return hinhkhuyenmai;
	}
	public void setHinhkhuyenmai(String hinhkhuyenmai) {
		this.hinhkhuyenmai = hinhkhuyenmai;
	}
	
	
	
}
