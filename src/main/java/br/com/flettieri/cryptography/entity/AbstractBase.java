package br.com.flettieri.cryptography.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
public class AbstractBase implements Serializable {

	private static final long serialVersionUID = 1925787778606736876L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "update_date")
	private Date updateDate;
	
	@Column(name = "deleted")
	private Boolean deleted;
	

	@PrePersist
	private void persist() {
		this.createDate = new Date();
		this.updateDate = new Date();
		this.deleted = false;
	}
	
	@PreUpdate
	private void update() {
		this.updateDate = new Date();
	}
	
}
