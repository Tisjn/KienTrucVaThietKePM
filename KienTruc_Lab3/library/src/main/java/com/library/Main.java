package com.library;

import com.library.singleton.Library;
import com.library.factory.BookFactory;
import com.library.factory.Book;
import com.library.model.User;
import com.library.observer.Librarian;
import com.library.observer.UserSubscriber;
import com.library.strategy.*;
import com.library.decorator.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Initialize Library (Singleton)
        Library library = Library.getInstance();

        // Create Observers
        Librarian librarian = new Librarian("John Smith");
        UserSubscriber user1 = new UserSubscriber("USER001");
        UserSubscriber user2 = new UserSubscriber("USER002");

        // Subscribe observers
        System.out.println("=== STEP 1: SUBSCRIBING OBSERVERS ===\n");
        library.subscribe(librarian);
        library.subscribe(user1);
        library.subscribe(user2);

        // Create Books using Factory
        System.out.println("\n=== STEP 2: ADDING BOOKS TO LIBRARY (Factory Pattern) ===\n");
        
        Book book1 = BookFactory.createBook("paper", "Clean Code", "Robert C. Martin", "Programming", 464);
        Book book2 = BookFactory.createBook("ebook", "The Pragmatic Programmer", "David Thomas", "Programming", "EPUB");
        Book book3 = BookFactory.createBook("audiobook", "Atomic Habits", "James Clear", "Self-Help", 495);
        Book book4 = BookFactory.createBook("paper", "Design Patterns", "Gang of Four", "Programming", 395);
        Book book5 = BookFactory.createBook("ebook", "The Lean Startup", "Eric Ries", "Business", "PDF");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        // Display all books
        System.out.println("\n=== ALL BOOKS IN LIBRARY ===\n");
        for (Book book : library.getAllBooks()) {
            book.displayInfo();
        }

        // Search using Strategy Pattern
        System.out.println("\n=== STEP 3: SEARCHING BOOKS (Strategy Pattern) ===\n");
        
        System.out.println("--- Search by Title: 'Code' ---");
        SearchStrategy searchByName = new SearchByName();
        List<Book> resultsByName = library.search(searchByName, "Code");
        for (Book book : resultsByName) {
            book.displayInfo();
        }

        System.out.println("\n--- Search by Author: 'David' ---");
        SearchStrategy searchByAuthor = new SearchByAuthor();
        List<Book> resultsByAuthor = library.search(searchByAuthor, "David");
        for (Book book : resultsByAuthor) {
            book.displayInfo();
        }

        System.out.println("\n--- Search by Category: 'Programming' ---");
        SearchStrategy searchByCategory = new SearchByCategory();
        List<Book> resultsByCategory = library.search(searchByCategory, "Programming");
        for (Book book : resultsByCategory) {
            book.displayInfo();
        }

        // Demonstrate Decorator Pattern
        System.out.println("\n=== STEP 4: BORROWING SERVICES (Decorator Pattern) ===\n");
        
        // Basic borrow
        BorrowService basicBorrow = new BorrowBook("Clean Code");
        System.out.println("Service: " + basicBorrow.getDescription());
        System.out.println("Cost: $" + basicBorrow.getCost() + "\n");

        // Borrow with extended time
        BorrowService extendedBorrow = new ExtendTimeDecorator(basicBorrow, 7);
        System.out.println("Service: " + extendedBorrow.getDescription());
        System.out.println("Cost: $" + extendedBorrow.getCost() + "\n");

        // Borrow with special edition
        BorrowService specialBorrow = new SpecialEditionDecorator(basicBorrow, "Hardcover");
        System.out.println("Service: " + specialBorrow.getDescription());
        System.out.println("Cost: $" + specialBorrow.getCost() + "\n");

        // Borrow with both decorators
        BorrowService complexBorrow = new SpecialEditionDecorator(
            new ExtendTimeDecorator(basicBorrow, 14), 
            "Limited Edition"
        );
        System.out.println("Service: " + complexBorrow.getDescription());
        System.out.println("Cost: $" + complexBorrow.getCost() + "\n");

        // Create and display users
        System.out.println("=== STEP 5: USER MANAGEMENT ===\n");
        User user = new User("USER001", "Alice Johnson", "alice@library.com");
        System.out.println(user);

        // Statistics
        System.out.println("\n=== LIBRARY STATISTICS ===\n");
        System.out.println("Total Books in Library: " + library.getAllBooks().size());
        System.out.println("Programming Books: " + library.search(new SearchByCategory(), "Programming").size());
        System.out.println("Business Books: " + library.search(new SearchByCategory(), "Business").size());
        System.out.println("Self-Help Books: " + library.search(new SearchByCategory(), "Self-Help").size());

        System.out.println("\n========================================");
        System.out.println("   Thank you for using Library System!");
        System.out.println("========================================");
    }
}
