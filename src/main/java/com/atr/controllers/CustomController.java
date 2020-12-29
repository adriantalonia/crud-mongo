package com.atr.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.atr.models.Tutorial;
import com.atr.repository.CustomRepoImpl;

@RestController
@RequestMapping("/custom")
public class CustomController {

	@Autowired
	private CustomRepoImpl customRepo;

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title, 
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null && page == null)
				customRepo.getAllTutorial().forEach(tutorials::add);
			else if ( title != null )
				customRepo.findByTitle(title).forEach(tutorials::add);
			else if ( page != null )
				customRepo.getAllTutorialPaginated(page, size).forEach(tutorials::add);
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> TutorialPaginated(@RequestParam(required = true) int page,
			@RequestParam(required = true) int size) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			customRepo.getAllTutorialPaginated(page, size).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = customRepo
					.saveTutorial(tutorial);
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/tutorials")
	public ResponseEntity<Tutorial> updateTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = customRepo
					.updateOneTutorial(tutorial);
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteTutorial(@RequestBody Tutorial tutorial) {
		try {
			customRepo.deleteTutorial(tutorial);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
