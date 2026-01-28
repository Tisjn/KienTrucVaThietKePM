package com.library.Availability;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private Library library = Library.getInstance();

    @PostMapping("/books")
    public String addBook(@RequestParam String type) {
        Book book = BookFactory.createBook(type);
        library.addBook(book);
        return "Book added: " + book.getInfo();
    }

    @GetMapping("/books")
    public List<String> getBooks() {
        return library.getBooks()
                .stream()
                .map(Book::getInfo)
                .toList();
    }
}

