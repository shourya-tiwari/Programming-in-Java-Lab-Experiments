public class Book {

    private String title;
    private String author;
    private int year;
    public int price;
    public String genre;
    public String publisher;
    public String isbn;
    public int pages;
    public String language;

    public Book() {
        title = "Unknown";
        author = "Unknown";
        year = 0;
        price = 0;
        genre = "Unknown";
        publisher = "Unknown";
        isbn = "Unknown";
        pages = 0;
        language = "Unknown";
    }

    public Book(String title, String author, int year, int price, String genre,
                String publisher, String isbn, int pages, String language) throws InvalidPriceException {

        if (price < 0) {
            throw new InvalidPriceException("Price must be greater than 0. Provided price: " + price);
        }

        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.genre = genre;
        this.publisher = publisher;
        this.isbn = isbn;
        this.pages = pages;
        this.language = language;
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book(Book b) {
        this.title = b.title;
        this.author = b.author;
        this.year = b.year;
        this.price = b.price;
        this.genre = b.genre;
        this.publisher = b.publisher;
        this.isbn = b.isbn;
        this.pages = b.pages;
        this.language = b.language;
    }

    public Book(Book b, int price, String genre) throws InvalidGenreException, InvalidPriceException {
        this(b);

        if (price < 0) {
            throw new InvalidPriceException("Price must be greater than 0. Provided price: " + price);
        }

        if (!genre.equalsIgnoreCase("Novel") &&
            !genre.equalsIgnoreCase("Dystopian") &&
            !genre.equalsIgnoreCase("Romance") &&
            !genre.equalsIgnoreCase("Fantasy") &&
            !genre.equalsIgnoreCase("Adventure") &&
            !genre.equalsIgnoreCase("Historical") &&
            !genre.equalsIgnoreCase("Fiction")) {

            throw new InvalidGenreException("Invalid Genre.");
        }

        this.price = price;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public void setPrice(int price) throws InvalidPriceException {

        if (price <= 0) {
            throw new InvalidPriceException("Price must be greater than 0. Provided price: " + price);
        }

        this.price = price;
    }

    public void setGenre(String genre) throws InvalidGenreException {

        if (!genre.equalsIgnoreCase("Novel") &&
            !genre.equalsIgnoreCase("Dystopian") &&
            !genre.equalsIgnoreCase("Romance") &&
            !genre.equalsIgnoreCase("Fantasy") &&
            !genre.equalsIgnoreCase("Adventure") &&
            !genre.equalsIgnoreCase("Historical") &&
            !genre.equalsIgnoreCase("Fiction")) {

            throw new InvalidGenreException("Invalid Genre.");
        }

        this.genre = genre;
    }

    public String getDetails() {
        return "Title: " + title + ", Author: " + author + ", Year: " + year +
                ", Price: " + price + ", Genre: " + genre;
    }
}