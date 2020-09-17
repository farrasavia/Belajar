package com.developmentid.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developmentid.crud.exception.ResourceNotFoundException;
import com.developmentid.crud.model.mataKuliah;
import com.developmentid.crud.repository.mataKuliahRepository;



@RestController
@RequestMapping("/api/v1")
public class mataKuliahController {
	@Autowired
	private mataKuliahRepository lectureRepository;

	@GetMapping("/mataKuliah")
	public List<mataKuliah> getAllMatkul() {
		return lectureRepository.findAll();
	}

	@GetMapping("/mataKuliah/{id}")
	public ResponseEntity<mataKuliah> getMatkulById(@PathVariable(value = "id") Long lectureId)
			throws ResourceNotFoundException {
		mataKuliah lecture = lectureRepository.findById(lectureId)
				.orElseThrow(() -> new ResourceNotFoundException("Lecture not found for this id :: " + lectureId));
		return ResponseEntity.ok().body(lecture);
	}

	@PostMapping("/mataKuliah")
	public mataKuliah createMatkul(@Validated @RequestBody mataKuliah lecture) {
		return lectureRepository.save(lecture);
	}

	@PutMapping("/mataKuliah/{id}")
	public ResponseEntity<mataKuliah> updateMatkul(@PathVariable(value = "id") Long lectureId,
			@Validated @RequestBody mataKuliah lectureDetails) throws ResourceNotFoundException {
		mataKuliah lecture = lectureRepository.findById(lectureId)
				.orElseThrow(() -> new ResourceNotFoundException("Lecture not found for this id :: " + lectureId));

		lecture.setMataKuliah(lectureDetails.getMataKuliah());
		lecture.setIdmhs(lectureDetails.getIdmhs());
		final mataKuliah updatedLecture = lectureRepository.save(lecture);
		return ResponseEntity.ok(updatedLecture);
	}

	@DeleteMapping("/mataKuliah/{id}")
	public Map<String, Boolean> deleteMatkul(@PathVariable(value = "id") Long lectureId)
			throws ResourceNotFoundException {
		mataKuliah lecture = lectureRepository.findById(lectureId)
				.orElseThrow(() -> new ResourceNotFoundException("lecture not found for this id :: " + lectureId));

		lectureRepository.delete(lecture);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
