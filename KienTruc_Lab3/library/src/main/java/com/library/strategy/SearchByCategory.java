package com.library.strategy;

import com.library.factory.Book;
import java.util.ArrayList;
import java.util.List;

public class SearchByCategory implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().toLowerCase().equals(query.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }
}
