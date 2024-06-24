package br.com.flettieri.cryptography.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final String DELETE = "delete()";
	private static final String FIND_BY_ID = "findById()";
	private static final String UPDATE = "update()";
	private static final String SAVE = "save()";
	private static final String LIST = "list()";

	private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository repository;

	public ResponseEntity<List<TransactionDTO>> list() {
		LOG.info("START - " + LIST + " - " + new Date());
		List<TransactionDTO> response = TransactionConverter.toListDTO(repository.findAll());
		LOG.info("FINISH - " + LIST + " - " + new Date() + " " + response);
		
		return ResponseEntity.ok(response);
	}

	public ResponseEntity<TransactionDTO> save(Transaction transaction) {
		LOG.info("START - " + SAVE + " - " + new Date());
		TransactionDTO response = TransactionConverter.toDTO(repository.save(transaction));
		LOG.info("FINISH - " + SAVE + " - " + new Date() + " " + response);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	public ResponseEntity<TransactionDTO> update(Long id, TransactionUpdateDTO updateDTO) {
		LOG.info("START - " + UPDATE + " - " + new Date());
		Transaction transaction = findById(id).getBody();
		
		if (transaction != null) {
			ReflectionUtil.copyProperties(updateDTO, transaction);
			
			TransactionDTO response = save(transaction).getBody();
			LOG.info("FINISH - " + UPDATE + " - " + new Date() + " " + response);
			return ResponseEntity.ok().body(response);
		} else {
			LOG.error("FINISH - " + UPDATE + " - " + new Date());
			return ResponseEntity.notFound().build();
		}
		
	}

	public ResponseEntity<Transaction> findById(Long id) {
		LOG.info("START - " + FIND_BY_ID + " - " + new Date());
		Transaction response = repository.findById(id).orElse(null);
		LOG.info("FINISH - " + FIND_BY_ID + " - " + new Date() + " " + response);
		
		return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}

	public ResponseEntity<TransactionDTO> delete(Long id) {
		LOG.info("START - " + DELETE + " - " + new Date());
		Transaction transaction = findById(id).getBody();
		
		if (transaction != null) {
			transaction.setDeleted(true);
			TransactionDTO response = save(transaction).getBody();
			LOG.info("FINISH - " + FIND_BY_ID + " - " + new Date() + " " + response);
			return ResponseEntity.ok().body(response);
		} else {
			LOG.error("FINISH - " + FIND_BY_ID + " - " + new Date());
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
}
