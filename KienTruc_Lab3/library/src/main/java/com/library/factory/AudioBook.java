package com.library.factory;

public class AudioBook implements Book {
    private String title;
    private String author;
    private String category;
    private int duration;

    public AudioBook(String title, String author, String category, int duration) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.duration = duration;
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
        return "AudioBook";
    }

    @Override
    public void displayInfo() {
        System.out.println("AudioBook: " + title + " | Author: " + author +
                         " | Duration: " + duration + " mins | Category: " + category);
    }
}
