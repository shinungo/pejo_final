
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
    "scaRedirect",
    "self",
    "status",
    "scaStatus"
})
public class Links implements Serializable
{

    @JsonProperty("scaRedirect")
    private ScaRedirect scaRedirect;
    @JsonProperty("self")
    private Self self;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("scaStatus")
    private ScaStatus scaStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5032009195944471973L;

    @JsonProperty("scaRedirect")
    public ScaRedirect getScaRedirect() {
        return scaRedirect;
    }

    @JsonProperty("scaRedirect")
    public void setScaRedirect(ScaRedirect scaRedirect) {
        this.scaRedirect = scaRedirect;
    }

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("scaStatus")
    public ScaStatus getScaStatus() {
        return scaStatus;
    }

    @JsonProperty("scaStatus")
    public void setScaStatus(ScaStatus scaStatus) {
        this.scaStatus = scaStatus;
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
