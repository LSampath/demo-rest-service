package com.example.restservice;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class BookRepository {
    private final Map<Long,Book> bookDatabase;

    public BookRepository() {
        this.bookDatabase = new HashMap<>();
    }

    public Collection<Book> getBooks() {
        return bookDatabase.values();
    }

    public Set<Long> getBookISBNs() {
        return bookDatabase.keySet();
    }

    public Book getBook(long isbn) {
        return bookDatabase.get(isbn);
    }

    public void addBook(Book book) {
        bookDatabase.put(book.getIsbn(), book);
    }

    public void updateBook(long isbn, Book book) {
        bookDatabase.put(isbn, book);
    }

    public boolean deleteBook(long isbn) {
        Book removed = bookDatabase.remove(isbn);
        return removed != null;
    }
}
