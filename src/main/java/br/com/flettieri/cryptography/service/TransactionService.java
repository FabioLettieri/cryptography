package br.com.flettieri.cryptography.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.flettieri.cryptography.converter.TransactionConverter;
import br.com.flettieri.cryptography.dto.TransactionDTO;
import br.com.flettieri.cryptography.entity.Transaction;
import br.com.flettieri.cryptography.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	public ResponseEntity<List<TransactionDTO>> list() {
		List<Transaction> response = repository.findAll();

		return ResponseEntity.ok(TransactionConverter.toListDTO(response));
	}

	public ResponseEntity<TransactionDTO> save(Transaction transaction) {
		TransactionDTO response = TransactionConverter.toDTO(repository.save(transaction));
		return ResponseEntity.ok(response);
	}

}
