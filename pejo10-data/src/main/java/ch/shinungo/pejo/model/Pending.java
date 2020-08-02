package ch.shinungo.pejo.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@JsonPropertyOrder({ "transactionId", "creditorName", "creditorAccount", "transactionAmount", "valueDate",
		"remittanceInformationUnstructured" })
public class Pending {

	@JsonProperty("transactionId")
	private String transactionId;
	@JsonProperty("creditorName")
	private String creditorName;
	@JsonProperty("creditorAccount")
	private CreditorAccount creditorAccount;
	@JsonProperty("transactionAmount")
	private TransactionAmount transactionAmount;
	@JsonProperty("valueDate")
	private String valueDate;
	@JsonProperty("remittanceInformationUnstructured")
	private String remittanceInformationUnstructured;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}