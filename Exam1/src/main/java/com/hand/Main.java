package com.hand;

import com.hand.beans.City;
import com.hand.beans.Country;
import com.hand.beans.Customer;
import com.hand.beans.Rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Country findCountryById(int id, Connection connection) {
        String sql = "SELECT country, last_update FROM country WHERE country_id = ?";
        Country country = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setMaxRows(1);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                country = new Country(id, resultSet.getString("country"), resultSet.getDate("last_update"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(resultSet, statement);
        }

        return country;
    }

    private static Customer findCustomerById(int id, Connection connection) {
        String sql = "SELECT first_name, last_name FROM customer WHERE customer_id = ?";
        Customer customer = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setMaxRows(1);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                customer = new Customer(id, resultSet.getString("first_name"), resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(resultSet, statement);
        }

        return customer;
    }

    private static List<City> getCitiesOf(Country country, Connection connection) {
        String sql = "SELECT city_id, city, last_update FROM city WHERE country_id = ?";
        List<City> cities = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, country.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cities.add(new City(
                        resultSet.getInt("city_id"),
                        resultSet.getString("city"),
                        country.getId(),
                        resultSet.getDate("last_update"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(resultSet, statement);
        }
        return cities;
    }

    private static List<Rental> getRentalRecords(Customer customer, Connection connection) {
        String sql = "SELECT r.rental_date, i.film_id, f.title FROM rental r INNER JOIN inventory i INNER JOIN film f " +
                " ON r.inventory_id = i.inventory_id AND f.film_id = i.film_id WHERE r.customer_id = ?;";

        List<Rental> rentals = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, customer.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rentals.add(new Rental(resultSet.getInt(2), resultSet.getString(3), resultSet.getDate(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(resultSet, statement);
        }

        return rentals;
    }

    public static void main(String[] args) {
        System.out.println("country:" + System.getenv("COUNTRYID"));
        System.out.println("customer:" + System.getenv("CUSTOMERID"));
        int countryId = Integer.parseInt(System.getenv("COUNTRYID"));
        int customerId = Integer.parseInt(System.getenv("CUSTOMERID"));

        Connection connection = ConnectionFactory.getInstance().getConnection();
        if (countryId != Integer.MAX_VALUE) {
            Country country = findCountryById(countryId, connection);
            List<City> cities = getCitiesOf(country, connection);
            System.out.println("Country id: " + country.getId());
            System.out.println("Country " + country.getCountry() + " 的城市");
            System.out.println("城市 id | 城市名称");
            for (int i = 0; i < cities.size(); i++) {
                System.out.println(cities.get(i).getId() + " | " + cities.get(i).getCity());
            }
        }

        System.out.println();
        if (customerId != Integer.MAX_VALUE) {
            Customer customer = findCustomerById(customerId, connection);
            List<Rental> rentals = getRentalRecords(customer, connection);
            System.out.println("Customer id: " + customer.getId());
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " 租用的 Film");
            System.out.println("Film ID | Film 名称 | 租用时间");
            for (int i = 0; i < rentals.size(); i++) {
                System.out.println(rentals.get(i).getFilmId() + " | " + rentals.get(i).getFilmName() + " | " + rentals.get(i).getRentalDate());
            }
        }
        Util.close(connection);
    }
}
