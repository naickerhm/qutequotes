package QuteQuoteDomain;

public class Quote {
    private String text;
    private String name;
    private int id;

    public Quote(){
        
    }

    public Quote(String quote, String author, int id) {
        this.text = quote;
        this.name = author;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Use this convenient factory method to create a new quote.
     * Warning the ID will be null!
     * @param text the text of the quote
     * @param name the name of the person that said the text
     * @return a Quote object
     */
    public static Quote create(String text, String name) {
        Quote quote = new Quote();
        quote.setText(text);
        quote.setName(name);
        return quote;
    }
}
