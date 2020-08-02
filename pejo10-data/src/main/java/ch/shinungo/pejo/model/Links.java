
package ch.shinungo.pejo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "scaRedirect", "self", "status", "scaStatus", "balances", "transactions", "account" })
@Data
public class Links implements Serializable {

	@JsonProperty("scaRedirect")
	private ScaRedirect scaRedirect;
	@JsonProperty("balances")
	public Balances balances;
	@JsonProperty("transactions")
	public Transactions transactions;
	@JsonProperty("self")
	private Self self;
	@JsonProperty("status")
	private Status status;
	@JsonProperty("scaStatus")
	private ScaStatus scaStatus;
	@JsonProperty("account")
	private Account_ account;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -5032009195944471973L;

}
