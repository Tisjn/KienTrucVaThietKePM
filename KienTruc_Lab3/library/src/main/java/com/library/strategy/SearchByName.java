package com.library.strategy;

import com.library.factory.Book;
import java.util.ArrayList;
import java.util.List;

public class SearchByName implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }
}
