
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
@JsonPropertyOrder({ "iban", "otherAccountIdentification", "currency", "resourceId", "product", "cashAccountType",
		"name", "_links"

})
@Data // DIES IST LOMCOK UND MACHT GETER SETTER 29.7.
public class Account implements Serializable {

	@JsonProperty("iban")
	private String iban;
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
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 2228834932433386726L;

}
