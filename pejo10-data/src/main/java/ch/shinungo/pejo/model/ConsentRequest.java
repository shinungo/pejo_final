
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "access",
    "recurringIndicator",
    "validUntil",
    "frequencyPerDay",
    "combinedServiceIndicator"
})
public class ConsentRequest implements Serializable
{

    @JsonProperty("access")
    private Access access;
    @JsonProperty("recurringIndicator")
    private Boolean recurringIndicator;
    @JsonProperty("validUntil")
    private String validUntil;
    @JsonProperty("frequencyPerDay")
    private Integer frequencyPerDay;
    @JsonProperty("combinedServiceIndicator")
    private Boolean combinedServiceIndicator;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3506473173755766743L;

    @JsonProperty("access")
    public Access getAccess() {
        return access;
    }

    @JsonProperty("access")
    public void setAccess(Access access) {
        this.access = access;
    }

    @JsonProperty("recurringIndicator")
    public Boolean getRecurringIndicator() {
        return recurringIndicator;
    }

    @JsonProperty("recurringIndicator")
    public void setRecurringIndicator(Boolean recurringIndicator) {
        this.recurringIndicator = recurringIndicator;
    }

    @JsonProperty("validUntil")
    public String getValidUntil() {
        return validUntil;
    }

    @JsonProperty("validUntil")
    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    @JsonProperty("frequencyPerDay")
    public Integer getFrequencyPerDay() {
        return frequencyPerDay;
    }

    @JsonProperty("frequencyPerDay")
    public void setFrequencyPerDay(Integer frequencyPerDay) {
        this.frequencyPerDay = frequencyPerDay;
    }

    @JsonProperty("combinedServiceIndicator")
    public Boolean getCombinedServiceIndicator() {
        return combinedServiceIndicator;
    }

    @JsonProperty("combinedServiceIndicator")
    public void setCombinedServiceIndicator(Boolean combinedServiceIndicator) {
        this.combinedServiceIndicator = combinedServiceIndicator;
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
