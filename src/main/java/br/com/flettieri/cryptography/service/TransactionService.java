package br.com.flettieri.cryptography.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.flettieri.cryptography.converter.TransactionConverter;
import br.com.flettieri.cryptography.dto.TransactionDTO;
import br.com.flettieri.cryptography.dto.TransactionUpdateDTO;
import br.com.flettieri.cryptography.entity.Transaction;
import br.com.flettieri.cryptography.repository.TransactionRepository;
import br.com.flettieri.cryptography.utils.ReflectionUtil;

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
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	public ResponseEntity<TransactionDTO> update(Long id, TransactionUpdateDTO updateDTO) {
		Transaction transaction = findById(id).getBody();
		
		if (transaction != null) {
			ReflectionUtil.copyProperties(updateDTO, transaction);
			
			TransactionDTO response = save(transaction).getBody();
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.badRequest().build();
		}
		
	}

	public ResponseEntity<Transaction> findById(Long id) {
		Transaction response = repository.findById(id).orElse(null);
		
		return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}

	public ResponseEntity<TransactionDTO> delete(Long id) {
		Transaction transaction = findById(id).getBody();
		
		if (transaction != null) {
			transaction.setDeleted(true);
			return save(transaction);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
}
