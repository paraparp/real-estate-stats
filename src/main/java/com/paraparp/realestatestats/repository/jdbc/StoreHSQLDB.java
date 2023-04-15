package com.paraparp.realestatestats.repository.jdbc;


import com.paraparp.realestatestats.model.entities.RealEstateData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public class StoreHSQLDB {

    public void store(List<RealEstateData> data ) {
        try {
            // Load the HSQLDB JDBC driver
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:/path/to/database", "SA", "");

            // Create a statement
            Statement statement = connection.createStatement();

            // Create a table
            statement.executeUpdate("CREATE TABLE real_estate_data ( date DATE, location VARCHAR(255), amount INTEGER, type0 INTEGER,  percentil13 INTEGER, percentil33 INTEGER, price_m2 INTEGER, PRIMARY KEY (date, location))")
            ;

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
