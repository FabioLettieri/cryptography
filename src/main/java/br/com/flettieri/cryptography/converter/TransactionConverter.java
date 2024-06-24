package br.com.flettieri.cryptography.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.flettieri.cryptography.dto.TransactionDTO;
import br.com.flettieri.cryptography.entity.Transaction;

public abstract class TransactionConverter {
	
	public static TransactionDTO toDTO(Transaction transaction) {
		return TransactionDTO.builder()
				.id(transaction.getId())
				.creditCardToken(transaction.getCreditCardToken())
				.userDocument(transaction.getUserDocument())
				.value(transaction.getValue())
				.createDate(transaction.getCreateDate() != null ? transaction.getCreateDate() : null)
				.updateDate(transaction.getUpdateDate() != null ? transaction.getUpdateDate() : null)
				.deleted(transaction.getDeleted() != null ? transaction.getDeleted() : null)
				.build();
	}
	
	public static List<TransactionDTO> toListDTO(List<Transaction> list) {
		List<TransactionDTO> listDTO = new ArrayList<TransactionDTO>();
		list.stream().forEach(transaction -> {
			TransactionDTO dto = toDTO(transaction);
			listDTO.add(dto);
		});
		
		return listDTO;
	}

}
