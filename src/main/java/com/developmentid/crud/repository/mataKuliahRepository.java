package com.developmentid.crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developmentid.crud.model.mataKuliah;




@Repository
public interface mataKuliahRepository extends JpaRepository<mataKuliah, Long>{



}