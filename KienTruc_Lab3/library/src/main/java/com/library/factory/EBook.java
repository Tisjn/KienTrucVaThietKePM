package com.library.factory;

public class EBook implements Book {
    private String title;
    private String author;
    private String category;
    private String format_type;

    public EBook(String title, String author, String category, String format_type) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.format_type = format_type;
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
        return "EBook";
    }

    @Override
    public void displayInfo() {
        System.out.println("EBook: " + title + " | Author: " + author +
                         " | Format: " + format_type + " | Category: " + category);
    }
}
