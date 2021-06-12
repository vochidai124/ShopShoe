package com.vochidai.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "chitiethoadon")
public class ChiTietHoaDon {
	
	@EmbeddedId
	ChiTietHoaDonid chiTietHoaDonid;
	
	int soluong;
	String giatien;
	
}
