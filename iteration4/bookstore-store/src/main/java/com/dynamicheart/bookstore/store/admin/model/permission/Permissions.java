
package com.dynamicheart.bookstore.store.admin.model.permission;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "permission"
})
public class Permissions implements Serializable
{

    @JsonProperty("permission")
    private List<StorePermission> storePermission = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8484447758224382575L;

    @JsonProperty("permission")
    public List<StorePermission> getStorePermission() {
        return storePermission;
    }

    @JsonProperty("permission")
    public void setStorePermission(List<StorePermission> storePermission) {
        this.storePermission = storePermission;
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
