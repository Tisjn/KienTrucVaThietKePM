package com.library.singleton;

import com.library.factory.Book;
import com.library.observer.Observer;
import com.library.observer.LibraryEvent;
import com.library.strategy.SearchStrategy;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private List<Book> books;
    private List<Observer> observers;

    private Library() {
        this.books = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObservers(new LibraryEvent("ADD_BOOK", "New book added: " + book.getTitle()));
    }

    public void removeBook(Book book) {
        books.remove(book);
        notifyObservers(new LibraryEvent("REMOVE_BOOK", "Book removed: " + book.getTitle()));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public List<Book> search(SearchStrategy strategy, String query) {
        return strategy.search(books, query);
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
        notifyObservers(new LibraryEvent("SUBSCRIBE", "New observer subscribed"));
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
        notifyObservers(new LibraryEvent("UNSUBSCRIBE", "Observer unsubscribed"));
    }

    public void notifyObservers(LibraryEvent event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}
