package com.developmentid.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.developmentid.crud.model.nilai;


//import net.guides.springboot2.springboot2jpacrudexample.model.Employee;

@Repository
public interface nilaiRepository extends JpaRepository<nilai, Long>{

nilai findOneByIdmhsAndIdmatkul(long idmhs, long idmatkul);


@Query(value = "SELECT AVG(nilai)" +
		"" +
		"FROM rusak.nilai A"
		+ "" + 
		"where A.idmhs = ?1"
		, nativeQuery=true)
nilai getAvg(long idmhs);
	
}
