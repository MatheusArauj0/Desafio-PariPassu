package com.example.gerenciadorsenhasbancarias.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gerenciadorsenhasbancarias.entities.Password;
import com.example.gerenciadorsenhasbancarias.services.PasswordService;

@RestController
@RequestMapping(value = "/password")
public class PasswordController {

	@Autowired
	private PasswordService service;

	@PostMapping("/new")
	public ResponseEntity<Password> newPassword(@RequestBody Password entity) {
		Password pass = service.newPassword(entity);
		return ResponseEntity.ok().body(pass);
	}

	@GetMapping("/next")
	public ResponseEntity<Password> callNextPassword() {
		Password pass = service.callNext();
		return ResponseEntity.ok().body(pass);
	}

	@GetMapping("/reset")
	public ResponseEntity<Void> resetPassword() {
		service.reset();
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/list")
	public ResponseEntity<List<Password>> listPasswords() {
		List<Password> pass = service.list();
		return ResponseEntity.ok().body(pass);
	}
	
	@GetMapping("/recover")
	public ResponseEntity<List<Password>> recoverPassword() {
		List<Password> pass = service.recoverPasswords();
		return ResponseEntity.ok().body(pass);
	}

}
