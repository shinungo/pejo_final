package ch.shinungo.pejo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "accounts" })
@Data
public class AccountResponse {

	@JsonProperty("accounts")
	private List<Account> accounts = null;

	// TestesHabler Neue: 8.8.2020

	@JsonProperty("balances")
	private List<Account> balances = null;

	private Map<String, List<Balance>> testListe = new HashMap<>();

	@JsonProperty("additionalProperties")
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//	@JsonIgnore
//	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}