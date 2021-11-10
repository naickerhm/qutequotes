package DataAccess;

import java.sql.*;


public class NewQuteQuotes {
    public void saveNewQuotes(String authorName, String quoteText, String DISK_DB_URL)
            throws SQLException
    {
        Connection con = DriverManager.getConnection(DISK_DB_URL);
        PreparedStatement restmt = con.prepareStatement(
                "SELECT * FROM author WHERE author.authorName = ?"
        );
        restmt.setString(1, authorName);
        restmt.execute();
        ResultSet results = restmt.getResultSet();
        if(results.next()){
            con.close();
            throw new RuntimeException("World name must be unique");
        }

        try( final PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO author(authorName, quoteText) VALUES (?, ?)") ){
            stmt.setString(1, authorName);
            stmt.setString(2, quoteText);
            boolean gotAResultSet = stmt.execute();
            if(gotAResultSet){
                throw new RuntimeException( "Unexpectedly got a SQL resultset." );
            }else{
                final int updateCount = stmt.getUpdateCount();
                if( updateCount == 1 ){
                    System.out.println( "1 world inserted" );
                }else{
                    throw new RuntimeException( "Expected 1 world to be inserted, but got " + updateCount );
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Failed to connect to db");
        }
    }
}
