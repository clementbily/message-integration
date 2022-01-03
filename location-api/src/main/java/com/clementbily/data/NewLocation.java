package com.clementbily.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class NewLocation {

    @NotEmpty
    private String description;
    @NotNull
    private Double lon;
    @NotNull
    private Double lat;
    @NotNull
    private Long clientId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewLocation)) return false;
        NewLocation that = (NewLocation) o;
        return Double.compare(that.lon, lon) == 0 &&
                Double.compare(that.lat, lat) == 0 &&
                clientId == that.clientId &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, lon, lat, clientId);
    }
}



