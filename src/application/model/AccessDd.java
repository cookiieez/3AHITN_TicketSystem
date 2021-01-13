package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDd {

    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static  Connection connection = null;

    public static Connection getConnection(){
        // Wenn connection nicht befüllt wurde (connection == null)
        // da es sich um eine statische Variable handelt, ist diese
        // in allen Objektinstanzen gleich!
        if(connection == null){
            try{
                // erzeuge neue Verbinung zur Datenbank
                connection = DriverManager.getConnection("jdbc:ucanaccess://db/Ticket_Verwaltung.accdb");
            } catch (SQLException throwables){

            }
        }
        return connection;
    }
}
