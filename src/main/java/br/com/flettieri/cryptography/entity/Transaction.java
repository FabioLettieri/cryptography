package br.com.flettieri.cryptography.entity;

import java.math.BigDecimal;

import br.com.flettieri.cryptography.utils.CryptographyUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "transaction")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends AbstractBase {

	private static final long serialVersionUID = 7841237652751698468L;

	@Column(name = "user_document", nullable = false)
	private String userDocument;
	
	@Column(name = "credit_card_token", nullable = false)
	private String creditCardToken;
	
	@Column(name = "value", precision = 10, scale = 2, nullable = false)
	private BigDecimal value;
	
	@PrePersist
	private void encrypt() {
		this.userDocument = CryptographyUtil.encryptSHA512(userDocument);
		this.creditCardToken = CryptographyUtil.encryptSHA512(creditCardToken);
	}
}
