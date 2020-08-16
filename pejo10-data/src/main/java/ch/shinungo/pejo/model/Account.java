
package ch.shinungo.pejo.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "iban", "otherAccountIdentification", "currency", "resourceId", "product", "cashAccountType",
		"name", "_links", "href", "closingBooked"

})
@Data // DIES IST LOMCOK UND MACHT GETER SETTER 29.7.
public class Account implements Serializable {

	@JsonProperty("iban")
	private String iban;
	@JsonProperty("href")
	private String href;

	@JsonProperty("resourceId")
	public String resourceId;
	@JsonProperty("product")
	public String product;
	@JsonProperty("cashAccountType")
	public String cashAccountType;
	@JsonProperty("name")
	public String name;
	@JsonProperty("_links")
	public Links links;
	@JsonProperty("otherAccountIdentification")
	private String otherAccountIdentification;
	@JsonProperty("currency")
	private String currency;

	// von Ignore herauf geholt 7.8.20
	@JsonProperty("balances")
	private List<Balance> balances = null;

	// String closing Booked mit Getter und Setter

	@JsonProperty("closingBooked")
	public String closingBooked;

	@JsonProperty("closingBooked")
	public String getClosingBooked() {
		return closingBooked;
	}

	@JsonProperty("closingBooked")
	public void setClosingBooked(String closingBooked) {
		this.closingBooked = closingBooked;
	}

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 2228834932433386726L;
	private List<Booked> transactions = null;

}
