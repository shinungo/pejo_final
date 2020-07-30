package ch.shinungo.pejo.repository;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Account_ID {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Integer id;
	private String accountId;

	public Account_ID() {

	}

	public Account_ID(String accountId) {
		this.accountId = accountId;
	}

}
