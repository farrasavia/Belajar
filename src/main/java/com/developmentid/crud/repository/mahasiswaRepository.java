package com.developmentid.crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developmentid.crud.model.mahasiswa;





@Repository
public interface mahasiswaRepository extends JpaRepository<mahasiswa, Long>{
	mahasiswa findByidmhs(long idmhs);

}
