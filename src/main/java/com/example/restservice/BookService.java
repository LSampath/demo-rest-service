package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return new ArrayList<>(bookRepository.getBooks());
    }

    public Book getBook(long isbn) throws Exception {
        Book book = bookRepository.getBook(isbn);
        if (book != null) {
            return book;
        }
        throw new Exception("Book not found for ISBN: " + isbn);
    }

    public Book addBook(Book book) throws Exception {
        if (book.getIsbn() > 0 && isNoneEmpty(book.getTitle())
                && isNoneEmpty(book.getAuthor()) && book.getPageCount() > 0) {

            if (!bookRepository.getBookISBNs().contains(book.getIsbn())) {
                bookRepository.addBook(book);
                return book;
            } else {
                throw new Exception("Book already exists for ISBN:" + book.getIsbn());
            }
        } else {
            throw new Exception("Missing some properties for given book");
        }
    }

    public Book updateBook(long isbn, Book book) throws Exception {
        if (isbn > 0 && book.getIsbn() > 0 && isNoneEmpty(book.getTitle())
                && isNoneEmpty(book.getAuthor()) && book.getPageCount() > 0) {
            if (isbn == book.getIsbn()) {
                bookRepository.updateBook(isbn, book);
                return book;
            } else {
                throw new Exception("Cannot change ISBN value.");
            }
        } else {
            throw new Exception("Some properties are missing.");
        }
    }

    public boolean deleteBook(long isbn) {
        return bookRepository.deleteBook(isbn);
    }

    private boolean isNoneEmpty(String value) {
        return value != null && !value.trim().isBlank();
    }
}
