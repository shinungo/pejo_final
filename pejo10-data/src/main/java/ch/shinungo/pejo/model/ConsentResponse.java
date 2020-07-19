
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
    "consentStatus",
    "consentId",
    "_links"
})
public class ConsentResponse implements Serializable
{

    @JsonProperty("consentStatus")
    private String consentStatus;
    @JsonProperty("consentId")
    private String consentId;
    @JsonProperty("_links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2286110725608081549L;

    @JsonProperty("consentStatus")
    public String getConsentStatus() {
        return consentStatus;
    }

    @JsonProperty("consentStatus")
    public void setConsentStatus(String consentStatus) {
        this.consentStatus = consentStatus;
    }

    @JsonProperty("consentId")
    public String getConsentId() {
        return consentId;
    }

    @JsonProperty("consentId")
    public void setConsentId(String consentId) {
        this.consentId = consentId;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
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
