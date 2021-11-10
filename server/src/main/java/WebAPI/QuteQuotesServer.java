package WebAPI;

import io.javalin.Javalin;
import java.sql.SQLException;
import DataAccess.DatabaseConnection;

public class QuteQuotesServer {
    private final Javalin server;

    public QuteQuotesServer(DatabaseConnection dbcon) {
        
        server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
            config.enableCorsForAllOrigins();
        });

        this.server.get("/quotes", context -> QuoteApiHandler.getAll(context, dbcon));
        this.server.get("/quote/{id}", context -> QuoteApiHandler.getOne(context, dbcon));
        this.server.post("/quotes", context -> QuoteApiHandler.create(context, dbcon));
    }

    public static void main(String[] args) throws SQLException {
        QuteQuotesServer server;
        DatabaseConnection dbcon = new DatabaseConnection();

        boolean dbConnected = dbcon.Connect();

        if(dbConnected) {
            dbcon.createTable();
            server = new QuteQuotesServer(dbcon);
            server.start(5000);
        } else {
            System.out.println("DB Connection failed");
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> dbcon.Close()));
    }

    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }
}