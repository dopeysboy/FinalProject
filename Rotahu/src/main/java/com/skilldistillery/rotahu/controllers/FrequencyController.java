package com.skilldistillery.rotahu.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.Frequency;
import com.skilldistillery.rotahu.services.FrequencyService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class FrequencyController {
	
	@Autowired
	private FrequencyService frequencyService;
	
	@GetMapping("frequency")
	public List<Frequency> index(HttpServletRequest req, HttpServletResponse res) {
		List<Frequency> freqs = frequencyService.findAll();
		if (freqs.size() == 0) {
			res.setStatus(404);
		}
		return freqs;
	}
	
}
