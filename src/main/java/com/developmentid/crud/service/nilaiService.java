package com.developmentid.crud.service;

import org.springframework.stereotype.Service;

import com.developmentid.crud.dto.nilaiDto;
import com.developmentid.crud.model.nilai;



@Service
public class nilaiService {

	
	public nilaiDto transformToDto(nilai Nilai) {
		nilaiDto NilaiDto = new nilaiDto();
		
		NilaiDto.setIdmatkul(Nilai.getIdmatkul());
		NilaiDto.setIdmhs(Nilai.getIdmhs());
		NilaiDto.setNilai(Nilai.getNilai());
		NilaiDto.setKeterangan(Nilai.getKeterangan());
		NilaiDto.setNama(Nilai.getMhs().getNama());
		NilaiDto.setMataKuliah(Nilai.getMatkul().getMataKuliah());
		return NilaiDto;
	}
	
}
