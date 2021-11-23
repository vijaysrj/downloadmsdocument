package com.example.msdoc;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(value = "/msworddocument", produces = { "application/octet-stream" })
	public ResponseEntity<byte[]> download() {

		try {

			File file = ResourceUtils.getFile("classpath:test.docx");

			byte[] contents = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDisposition(ContentDisposition.attachment().filename("yourfile.docx").build());

			return new ResponseEntity<>(contents, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
