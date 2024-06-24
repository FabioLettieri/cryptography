package br.com.flettieri.cryptography.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {

	private Long id;
	private String creditCardToken;
	private String userDocument;
	private BigDecimal value;
	private Date createDate;
	private Date updateDate;
	private Boolean deleted;
	
	
}
