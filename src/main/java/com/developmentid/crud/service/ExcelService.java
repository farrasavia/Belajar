package com.developmentid.crud.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.developmentid.crud.helper.ExcelHelper;
import com.developmentid.crud.model.mahasiswa;
import com.developmentid.crud.repository.mahasiswaRepository;



@Service
public class ExcelService {
  @Autowired
  mahasiswaRepository repository;

  public void save(MultipartFile file) {
    try {
      List<mahasiswa> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public List<mahasiswa> getAllTutorials() {
    return repository.findAll();
  }
}
