package com.library.factory;

public class PaperBook implements Book {
    private String title;
    private String author;
    private String category;
    private int pages;

    public PaperBook(String title, String author, String category, int pages) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.pages = pages;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getFormat() {
        return "Paper Book";
    }

    @Override
    public void displayInfo() {
        System.out.println("Paper Book: " + title + " | Author: " + author +
                         " | Pages: " + pages + " | Category: " + category);
    }
}
