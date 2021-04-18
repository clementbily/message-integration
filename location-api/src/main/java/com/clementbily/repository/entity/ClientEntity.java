package com.clementbily.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    private long id;
    private String name;

    /**
     * @return the name column tuble value
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set the name column value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id column tuple value
     */
    public long getId() {
        return id;
    }

    /**
     * @param id set the id column value
     */
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientEntity)) return false;
        ClientEntity that = (ClientEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

