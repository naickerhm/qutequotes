package WebAPI;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;

import java.util.ArrayList;
import java.util.List;

import DataAccess.*;
import QuteQuoteDomain.Quote;

public class QuoteApiHandler {

    /**
     * Get all quotes
     *
     * @param context The Javalin Context for the HTTP GET Request
     */
    public static void getAll(Context context, DatabaseConnection dbcon) {
        List<Quote> list = new ArrayList<Quote>();
        list = dbcon.all();
        
        context.json(list);
    }

    /**
     * Get one quote
     *
     * @param context The Javalin Context for the HTTP GET Request
     */
    public static void getOne(Context context, DatabaseConnection dbcon) {
        Integer id = context.pathParamAsClass("id", Integer.class).get();
        Quote quote = dbcon.get(id);
        if (quote == null) {
            throw new NotFoundResponse("Quote not found: " + id);
        }
        context.json(quote);
    }

    /**
     * Create a new quote
     *
     * @param context The Javalin Context for the HTTP POST Request
     */
    public static void create(Context context, DatabaseConnection dbcon) {
        Quote quote = context.bodyAsClass(Quote.class);
        boolean quoteAdded = dbcon.add(quote);
        context.status(HttpCode.CREATED);
        context.json(quoteAdded);
    }
}