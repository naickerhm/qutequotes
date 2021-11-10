package WebAPI;

import io.javalin.Javalin;

import java.sql.SQLException;

// import DataAccess.DatabaseConnection;

public class QuteQuotesServer {
    private final Javalin server;

    public QuteQuotesServer() {
        server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
            config.enableCorsForAllOrigins();
        });

        this.server.get("/quotes", context -> QuoteApiHandler.getAll(context));
        this.server.get("/quote/{id}", context -> QuoteApiHandler.getOne(context));
        this.server.post("/quotes", context -> QuoteApiHandler.create(context));
    }

    public static void main(String[] args) throws SQLException {
        QuteQuotesServer server = new QuteQuotesServer();
//        DatabaseConnection dbcon = new DatabaseConnection();
        // dbcon.createTable();
        server.start(5000);
    }

    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }
}