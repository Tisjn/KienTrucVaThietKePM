package com.library.decorator;

public class BorrowBook implements BorrowService {
    private String bookTitle;

    public BorrowBook(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public double getCost() {
        return 0.0;
    }

    @Override
    public String getDescription() {
        return "Borrow book: " + bookTitle;
    }
}
