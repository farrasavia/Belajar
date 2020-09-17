package com.developmentid.crud.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.developmentid.crud.dto.ResponseJson;
import com.developmentid.crud.dto.nilaiDto;
import com.developmentid.crud.enums.ResponseCodes;
import com.developmentid.crud.exception.ResourceNotFoundException;
import com.developmentid.crud.model.nilai;
import com.developmentid.crud.repository.nilaiRepository;
import com.developmentid.crud.service.nilaiService;





@RestController
@RequestMapping("/api/v1")
public class nilaiController {
	@Autowired
	private nilaiRepository courseRepository;
	@Autowired
	private nilaiService courseService;

	@GetMapping("/nilai")
	public List<nilai> getAllNilai() {
		return courseRepository.findAll();
	}
	
	@RequestMapping(value = "/{idmhs}/{idmatkul}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseJson>getByKebutuhan(
			@PathVariable(name="idmhs", required=true, value="")long Idmhs,
			@PathVariable(name="idmatkul", required=true, value="") long Idmatkul)
	{
		try {
			
			nilai Nilai = courseRepository.findOneByIdmhsAndIdmatkul(Idmhs, Idmatkul);
			if(Nilai!=null) {
				nilaiDto NilaiDto = courseService.transformToDto(Nilai);
				return ResponseEntity.ok(new ResponseJson(ResponseCodes.SUCCESS, NilaiDto));
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseJson(ResponseCodes.NOTFOUND, "Data Not Found"));
		} 
		}catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseJson(ResponseCodes.OTHER, e.getMessage()));
			}
	}
	
	@RequestMapping(value = "avg/{idmhs}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResponseJson>getAvgNilai(
			@PathVariable(name="idmhs", required=true, value="")long Idmhs
			)
	{
		try {
			
			nilai Nilai = courseRepository.getAvg(Idmhs);
			if(Nilai!=null) {
				System.out.println(Nilai);
				return ResponseEntity.ok(new ResponseJson(ResponseCodes.SUCCESS, Nilai));
			}else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseJson(ResponseCodes.NOTFOUND, "Data Not Found"));
		} 
		}catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseJson(ResponseCodes.OTHER, e.getMessage()));
			}
	}
	
	@GetMapping("/nilai/{id}")
	public ResponseEntity<nilai> getNilaiById(@PathVariable(value = "id") Long courseId)
			throws ResourceNotFoundException {
		nilai course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
		return ResponseEntity.ok().body(course);
	}
	

	@PostMapping("/nilai")
	public nilai createNilai(@Validated @RequestBody nilai course) {
		return courseRepository.save(course);
	}

	@PutMapping("/nilai/{id}")
	public ResponseEntity<nilai> updateNilai(@PathVariable(value = "id") Long courseId,
			@Validated @RequestBody nilai courseDetails) throws ResourceNotFoundException {
		nilai course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
		
		course.setIdmhs(courseDetails.getIdmhs());
		course.setIdmatkul(courseDetails.getIdmatkul());
		course.setNilai(courseDetails.getNilai());
		course.setKeterangan(courseDetails.getKeterangan());
		final nilai updatedCourse = courseRepository.save(course);
		return ResponseEntity.ok(updatedCourse);
	}

	@DeleteMapping("/nilai/{id}")
	public Map<String, Boolean> deleteNilai(@PathVariable(value = "id") Long courseId)
			throws ResourceNotFoundException {
		nilai course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));

		courseRepository.delete(course);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
