package ch.shinungo.pejo.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "account", "transactions" })
@Data
public class TransactionResponse {

	@JsonProperty("account")
	private Account account;
	@JsonProperty("transactions")
	private Transactions transactions;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}