package com.developmentid.crud.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.developmentid.crud.dto.ResponseMessage;
import com.developmentid.crud.exception.ResourceNotFoundException;
import com.developmentid.crud.helper.ExcelHelper;
import com.developmentid.crud.model.mahasiswa;
import com.developmentid.crud.repository.mahasiswaRepository;
import com.developmentid.crud.service.ExcelService;



@RestController
@RequestMapping("/api/v1")
public class mahasiswaController {

	@Autowired
	private mahasiswaRepository studentRepository;
	
	@Autowired
	  ExcelService fileService;

	@GetMapping("/mahasiswa")
	public List<mahasiswa> getAllMahasiswa() {
		return studentRepository.findAll();
	}

	@GetMapping("/mahasiswa/{id}")
	public ResponseEntity<mahasiswa> getMahasiswaById(@PathVariable(value = "id") Long studentId)
			throws ResourceNotFoundException {
		mahasiswa student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + studentId));
		return ResponseEntity.ok().body(student);
	}

	@PostMapping("/mahasiswa")
	public mahasiswa createMahasiswa(@Validated @RequestBody mahasiswa student) {
		return studentRepository.save(student);
	}

	@PutMapping("/mahasiswa/{id}")
	public ResponseEntity<mahasiswa> updateMahasiswa(@PathVariable(value = "id") Long studentId,
			@Validated @RequestBody mahasiswa studentDetails) throws ResourceNotFoundException {
		mahasiswa student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + studentId));

		student.setNama(studentDetails.getNama());
		student.setAlamat(studentDetails.getAlamat());
		final mahasiswa updatedStudent = studentRepository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}

	@DeleteMapping("/mahasiswa/{id}")
	public Map<String, Boolean> deleteMahasiswa(@PathVariable(value = "id") Long studentId)
			throws ResourceNotFoundException {
		mahasiswa student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + studentId));

		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (ExcelHelper.hasExcelFormat(file)) {
	      try {
	        fileService.save(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    }

	    message = "Please upload an excel file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }
	
}