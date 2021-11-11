package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import QuteQuoteDomain.Quote;

public class DatabaseConnection implements QuoteDB{

    public static final String DISK_DB_URL = "jdbc:sqlite:qutequotes.db";
    //public static final String DISK_DB_URL = "jdbc:sqlite::memory";

    public Connection connectionObj = null;

    public DatabaseConnection() {

    }

    public boolean Connect(){
        try{
            connectionObj = DriverManager.getConnection(DISK_DB_URL);
            System.out.println( "Connected to database " );
            return true;
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }

        return false;
    }

    public void Close(){
        try{
            connectionObj.close();
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }


    public void createTable() throws SQLException {
        String sqlQuery =  "CREATE TABLE IF NOT EXISTS QuteQuotesList (id INTEGER PRIMARY KEY AUTOINCREMENT, quote TEXT, author TEXT)";
        try( final Statement stmt = connectionObj.createStatement() ){
            stmt.execute(sqlQuery);
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }


    @Override
    public Quote get(Integer id) {
        // unimplemented
        return null; //quotes.get(id);
    }

    @Override
    public List<Quote> all() {
        String sqlQuery =  "SELECT * FROM QuteQuotesList";
        List<Quote> quoteList = new ArrayList<Quote>();

        try{
            final Statement stmt = connectionObj.createStatement();
            ResultSet quotes = stmt.executeQuery(sqlQuery);

            while(quotes.next()){
                Quote newQuote = new Quote(quotes.getString("quote"), quotes.getString("author"), quotes.getInt("id"));
                quoteList.add(newQuote);
            }
        } catch( SQLException e ){
            System.err.println( e.getMessage() );
        }

        return quoteList;
    }

    @Override
    public boolean add(Quote quote) {
        String sqlQuery =  "INSERT INTO QuteQuotesList (quote, author) VALUES (\'" + quote.getText() + "\', \'" + quote.getName() + "\')";
        System.out.println("Query: " + sqlQuery);
        try( final Statement stmt = connectionObj.createStatement() ){
            stmt.execute(sqlQuery);
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }

        return false;
    }

}
