
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
@JsonPropertyOrder({ "iban", "otherAccountIdentification", "currency" })
@Data
public class Transaction implements Serializable {

	@JsonProperty("iban")
	private String iban;
	@JsonProperty("otherAccountIdentification")
	private String otherAccountIdentification;
	@JsonProperty("currency")
	private String currency;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 2444992203634415828L;

	@JsonProperty("iban")
	public String getIban() {
		return iban;
	}

	@JsonProperty("iban")
	public void setIban(String iban) {
		this.iban = iban;
	}

	@JsonProperty("otherAccountIdentification")
	public String getOtherAccountIdentification() {
		return otherAccountIdentification;
	}

	@JsonProperty("otherAccountIdentification")
	public void setOtherAccountIdentification(String otherAccountIdentification) {
		this.otherAccountIdentification = otherAccountIdentification;
	}

	@JsonProperty("currency")
	public String getCurrency() {
		return currency;
	}

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
