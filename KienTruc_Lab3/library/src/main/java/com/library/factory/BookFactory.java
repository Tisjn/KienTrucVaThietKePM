package com.library.factory;

public class BookFactory {
    public static Book createBook(String type, String title, String author, String category, Object... args) {
        switch (type.toLowerCase()) {
            case "paper":
                int pages = (args.length > 0) ? (Integer) args[0] : 300;
                return new PaperBook(title, author, category, pages);
            case "ebook":
                String format = (args.length > 0) ? (String) args[0] : "PDF";
                return new EBook(title, author, category, format);
            case "audiobook":
                int duration = (args.length > 0) ? (Integer) args[0] : 360;
                return new AudioBook(title, author, category, duration);
            default:
                throw new IllegalArgumentException("Unknown book type: " + type);
        }
    }
}
