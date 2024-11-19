import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;
    boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true; 
    }

    public void displayInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
    }
}


class ReferenceBook extends Book {
    int edition;

    public ReferenceBook(int bookId, String title, String author, int edition) {
        super(bookId, title, author);
        this.edition = edition;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Edition: " + edition);
    }
}


class FictionBook extends Book {
    String genre;

    public FictionBook(int bookId, String title, String author, String genre) {
        super(bookId, title, author);
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Genre: " + genre);
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void borrowBook(int bookId) {
        for (Book book : books) {
            if (book.bookId == bookId) {
                if (book.isAvailable) {
                    book.isAvailable = false;
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Book is not available.");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    
    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.bookId == bookId) {
                if (!book.isAvailable) {
                    book.isAvailable = true;
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book is already available.");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    public void displayBooks() {
        for (Book book : books) {
            book.displayInfo();
            System.out.println();
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding Books
        library.addBook(new ReferenceBook(1, "DEKR", "Sam", 3));
        library.addBook(new FictionBook(2, "AML", "Yeshwanth", "Technology"));

        Scanner scanner = new Scanner(System.in);

        
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Display All Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID to Borrow: ");
                    int borrowId = scanner.nextInt();
                    library.borrowBook(borrowId);
                    break;
                case 3:
                    System.out.print("Enter Book ID to Return: ");
                    int returnId = scanner.nextInt();
                    library.returnBook(returnId);
                    break;
                case 4:
                    System.out.println("Exit");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
