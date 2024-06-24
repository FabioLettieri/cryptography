package br.com.flettieri.cryptography.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flettieri.cryptography.dto.TransactionDTO;
import br.com.flettieri.cryptography.entity.Transaction;
import br.com.flettieri.cryptography.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	@GetMapping
	public ResponseEntity<List<TransactionDTO>> list() {
		return service.list();
	}
	
	@PostMapping
	public ResponseEntity<TransactionDTO> save(@RequestBody Transaction transaction) {
		return service.save(transaction);
	}

}
