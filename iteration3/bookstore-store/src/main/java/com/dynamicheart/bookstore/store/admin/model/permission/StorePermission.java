
package com.dynamicheart.bookstore.store.admin.model.permission;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "group"
})
public class StorePermission implements Serializable
{

    @JsonProperty("type")
    private String type;
    @JsonProperty("group")
    private StoreGroup storeGroup;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -7938476709520334066L;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("group")
    public StoreGroup getStoreGroup() {
        return storeGroup;
    }

    @JsonProperty("group")
    public void setStoreGroup(StoreGroup storeGroup) {
        this.storeGroup = storeGroup;
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
