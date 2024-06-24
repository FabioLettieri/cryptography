package br.com.flettieri.cryptography.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionUpdateDTO {

	private BigDecimal value;
	private Boolean deleted;
}
