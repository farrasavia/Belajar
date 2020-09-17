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
@IdClass(mataKuliahKey.class)
@Table(name = "matakuliah")
public class mataKuliah {
	@Id
	private long id;
	@Id
	private long idmatkul;
	private String mataKuliah;
	private long idmhs;
	
	
	
	



	@Override
	public String toString() {
		return "Employee [id=" + idmatkul + ", idmhs=" + idmhs
				+ ",mataKuliah=" +mataKuliah +"]";
	}
	
}
