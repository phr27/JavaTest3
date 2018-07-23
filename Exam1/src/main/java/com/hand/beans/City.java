package com.hand.beans;

import java.util.Date;

public class City {

    private Integer id;

    private String city;

    private Integer countryId;

    private Date lastUpdate;

    public City() {
    }

    public City(Integer id, String city, Integer countryId, Date lastUpdate) {
        this.id = id;
        this.city = city;
        this.countryId = countryId;
        this.lastUpdate = lastUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
