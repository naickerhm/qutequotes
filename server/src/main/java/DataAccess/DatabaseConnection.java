package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {

    // public static final String DISK_DB_URL = "jdbc:sqlite:QuteQuotes.sqlite";
    public static final String DISK_DB_URL = "jdbc:sqlite::memory";

    public  Connection connectionObj = null;


    public DatabaseConnection() {
        try( final Connection connection = DriverManager.getConnection(DISK_DB_URL) ){
            System.out.println( "Connected to database " );
            connectionObj = connection;
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

//    public Map<Integer, Quote> retrieveQuotes() {
//        return null;
//
//    }

    public void createTable() throws SQLException {
        String sqlQuery =  "CREATE TABLE IF NOT EXISTS QuteQuotesList (quote TEXT, author TEXT)";
        try( final Statement stmt = connectionObj.createStatement() ){
            stmt.execute(sqlQuery);
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }



    private void addQuote() throws SQLException { //incomplete
        String sqlQuery =  "INSERT INTO QuteQuotesList (quote, author) VALUES ()";
        try( final Statement stmt = connectionObj.createStatement() ){
            stmt.execute(sqlQuery);
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    private void readQuotes () throws SQLException { //TODO
        String sqlQuery =  "SELECT Author FROM QuteQuotesList";
        try( final Statement stmt = connectionObj.createStatement() ){
            stmt.execute(sqlQuery);
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

}
