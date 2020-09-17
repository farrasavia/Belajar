package com.developmentid.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@IdClass(mahasiswaKey.class)
@Table(name = "mahasiswa")
public class mahasiswa {
	@Id
	private long id;
	@Id
	private long idmhs;
	private String nama;
	private String alamat;
	
	
	//a
	@Override
	public String toString() {
		return "Mahasiswa [id=" + idmhs + ", namaDepan=" + nama + ", namaBelakang=" + alamat  + "]";
	}

}

