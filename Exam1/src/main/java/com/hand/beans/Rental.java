package com.hand.beans;

import java.util.Date;

public class Rental {

    private Integer filmId;

    private String filmName;

    private Date rentalDate;

    public Rental(Integer filmId, String filmName, Date rentalDate) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.rentalDate = rentalDate;
    }

    public Rental() {
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }
}
