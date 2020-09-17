package com.developmentid.crud.dto;

public class nilaiDto {
	private long idmhs;
	private long idmatkul;
	private Integer nilai;
	private String keterangan;
//Relasi
	private String nama;
	private String mataKuliah;

	//a
	public long getIdmhs() {
		return idmhs;
	}
	public void setIdmhs(long idmhs) {
		this.idmhs = idmhs;
	}
	public long getIdmatkul() {
		return idmatkul;
	}
	public void setIdmatkul(long idmatkul) {
		this.idmatkul = idmatkul;
	}
	public Integer getNilai() {
		return nilai;
	}
	public void setNilai(Integer nilai) {
		this.nilai = nilai;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	
	//Relasi
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getMataKuliah() {
		return mataKuliah;
	}
	public void setMataKuliah(String mataKuliah) {
		this.mataKuliah = mataKuliah;
	}
	
	
}
