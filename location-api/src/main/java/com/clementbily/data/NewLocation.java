package com.clementbily.data;

import java.util.Objects;

public class NewLocation {

    private String description;
    private double lon;
    private double lat;
    private long clientId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
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



