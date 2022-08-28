package org.sapataria.dbo.cashier;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class CashierDBO{
    Connection connection;

    private boolean connect(){
        Dotenv dotenv = Dotenv.load();
        String serverName = dotenv.get("DB_SERVER_NAME");
        String driverName = dotenv.get("DB_DRIVER_NAME");
        String database = dotenv.get("DB_DATABASE");
        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String pass = dotenv.get("DB_PASS");
        String connectionURL = String.format("%s%s/%s", url, serverName, database);

        try {
            Class.forName(driverName);
            this.connection = DriverManager.getConnection(connectionURL, user, pass);
            return true;

        } catch (ClassNotFoundException | SQLException ignored){
            return false;
        }
    }

    public CashierDBO() throws SQLException {
        boolean connected = this.connect();
        if (!connected){
            throw new SQLException();
        }
    }

    public void close() throws SQLException {
        if (this.connection.isValid(60)){
            try {
                this.connection.close();
            } catch (SQLException ignored){

            }
        }
    }

    public void open() throws SQLException {
        /*
        Returns true if connection was sucessful or if
         */

        if (this.connection.isClosed()){
            this.connect();
        }
    }

}
