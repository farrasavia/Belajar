package com.developmentid.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "nilai")
public class nilai {
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	private long idmhs;
	private long idmatkul;
	private Integer nilai;
	private String keterangan;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumns({
	@JoinColumn(name = "idmhs", insertable = false, updatable = false,referencedColumnName = "idmhs"),
	})
	
	@JsonIgnore
	private mahasiswa mhs;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumns({
	@JoinColumn(name = "idmatkul", insertable = false, updatable = false,referencedColumnName = "idmatkul"),
	})
	
	@JsonIgnore
	private mataKuliah matkul;

	public nilai orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	}

