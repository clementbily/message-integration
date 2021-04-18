package com.clementbily.repository.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "LOCATION")
public class LocationEntity {

    @Id
    @GeneratedValue()
    private long id;
    @OneToOne
    private ClientEntity client;
    private String description;
    private double lon;
    private double lat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationEntity)) return false;
        LocationEntity that = (LocationEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

