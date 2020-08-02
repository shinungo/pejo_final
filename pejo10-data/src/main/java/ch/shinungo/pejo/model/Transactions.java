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
@JsonPropertyOrder({ "href", "booked", "pending", "_links", "both", "bookingStatus" })
@Data
public class Transactions {

	@JsonProperty("bookingStatus")
	public String bookingStatus;

	@JsonProperty("booked")
	private List<Booked> booked = null;

	@JsonProperty("pending")
	private List<Pending> pending = null;

	@JsonProperty("_links")
	private Links links;

	@JsonProperty("href")
	public String href;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}