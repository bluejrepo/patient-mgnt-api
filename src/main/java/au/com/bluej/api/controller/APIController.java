package au.com.bluej.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class APIController {
	@GetMapping("/heart-beat")
	ResponseEntity<String> heartBeat() {
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
