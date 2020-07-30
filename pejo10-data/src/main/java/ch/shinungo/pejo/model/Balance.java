
package ch.shinungo.pejo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "iban", "otherAccountIdentification", "currency", "balanceType", "balanceAmount", "referenceDate",
		"lastChangeDateTime" })
@Data
public class Balance implements Serializable {
	@JsonProperty("balanceType")
	private String balanceType;
	@JsonProperty("balanceAmount")
	private BalanceAmount balanceAmount;
	@JsonProperty("referenceDate")
	private String referenceDate;
	@JsonProperty("lastChangeDateTime")
	private String lastChangeDateTime;
	@JsonProperty("iban")
	private String iban;
	@JsonProperty("otherAccountIdentification")
	private String otherAccountIdentification;
	@JsonProperty("currency")
	private String currency;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 4318029759573065937L;

	@JsonProperty("currency")
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
