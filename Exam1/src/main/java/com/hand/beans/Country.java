package com.hand.beans;

import java.util.Date;

public class Country {

    private Integer id;

    private String country;

    private Date lastUpdate;

    public Country() {
    }

    public Country(Integer id, String country, Date lastUpdate) {
        this.id = id;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
