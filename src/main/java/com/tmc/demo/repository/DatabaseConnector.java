package com.tmc.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.tmc.demo.config.DatabaseConnectorConfiguration;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnector {

    private Connection dbConnection;
    private Statement dbQuery;
    private ResultSet dbResult;

    private DatabaseConnectorConfiguration dbcConfig;

    public DatabaseConnector(DatabaseConnectorConfiguration dbcConfig) {
        this.dbcConfig = dbcConfig;
        try {
            Class.forName(this.dbcConfig.getJDBC_URL());
            this.dbConnection = DriverManager.getConnection(dbcConfig.getDB_URL(),
            dbcConfig.getUsername(), dbcConfig.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            this.dbQuery = dbConnection.createStatement();
            this.dbResult = dbQuery.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return dbResult;
    }

    public void terminateConnection() {
        try {
            this.dbResult.close();
            this.dbQuery.close();
            this.dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
