import java.util.ArrayList;

public class ArrayListOfBooks {

    public static void main(String[] args) {

        ArrayList<Book> bookList = new ArrayList<>();

        try {

            bookList.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 10, "Fiction", "Scribner", "978", 180, "English"));
            bookList.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960, 15, "Fiction", "Lippincott", "978", 281, "English"));
            bookList.add(new Book("1984", "George Orwell", 1949, 12, "Dystopian", "Secker", "978", 328, "English"));
            bookList.add(new Book("Pride and Prejudice", "Jane Austen", 1813, 8, "Romance", "Egerton", "978", 279, "English"));
            bookList.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937, 14, "Fantasy", "Allen & Unwin", "978", 310, "English"));

        } catch (InvalidPriceException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        for (Book b : bookList) {

            System.out.println("Title: " + b.getTitle());
            System.out.println("Author: " + b.getAuthor());
            System.out.println("Year: " + b.getYear());
            System.out.println("Price: $" + b.price);
            System.out.println("Genre: " + b.genre);
            System.out.println("Publisher: " + b.publisher);
            System.out.println("ISBN: " + b.isbn);
            System.out.println("Pages: " + b.pages);
            System.out.println("Language: " + b.language);
            System.out.println();
        }

        System.out.println("Fiction Books:");

        bookList.forEach(b -> {
            if (b.genre.equals("Fiction")) {
                System.out.println(b.getTitle());
            }
        });

        System.out.println();

        int totalPrice = bookList.stream()
                .mapToInt(b -> b.price)
                .sum();

        double avgPrice = (double) totalPrice / bookList.size();

        System.out.println("Average price: $" + avgPrice);

        System.out.println();

        try {

            Book invalidBook = new Book("Invalid Book", "Test", 2024, -5, "Fiction", "Test", "000", 100, "English");

        } catch (InvalidPriceException e) {

            System.out.println("Price Error: " + e.getMessage());

        }
    }
}