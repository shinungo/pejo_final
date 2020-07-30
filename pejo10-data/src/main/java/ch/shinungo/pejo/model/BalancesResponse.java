package ch.shinungo.pejo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "account", "balances" })
@Data
public class BalancesResponse {

	@JsonProperty("account")
	private Account account;
	@JsonProperty("balances")
	private List<Balance> balances = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
