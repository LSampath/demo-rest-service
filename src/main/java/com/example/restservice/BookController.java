package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseDTO<Book> getBooks() {
        return new ResponseDTO<>(200, bookService.getBooks());
    }

    @GetMapping("/{isbn}")
    public ResponseDTO<Book> getBook(@PathVariable(value = "isbn") long isbn) {
        try {
            return new ResponseDTO<>(200, List.of(bookService.getBook(isbn)));
        } catch (Exception e) {
            return new ResponseDTO<>(400, e.getMessage());
        }
    }

    @PostMapping
    public ResponseDTO<Book> addBook(@RequestBody Book newBook) {
        try {
            return new ResponseDTO<>(200, List.of(bookService.addBook(newBook)));
        } catch (Exception e) {
            return new ResponseDTO<>(400, e.getMessage());
        }
    }

    @PutMapping("/{isbn}")
    public ResponseDTO<Book> updateBook(@PathVariable(value = "isbn") long isbn, @RequestBody Book updatedBook) {
        try {
            return new ResponseDTO<>(200, List.of(bookService.updateBook(isbn, updatedBook)));
        } catch (Exception e) {
            return new ResponseDTO<>(400, e.getMessage());
        }
    }

    @DeleteMapping("/{isbn}")
    public ResponseDTO<Book> deleteBook(@PathVariable(value = "isbn") long isbn) {
        boolean deleted = bookService.deleteBook(isbn);
        if (deleted) {
            return new ResponseDTO<>(200,"Successfully deleted.");
        } else {
            return new ResponseDTO<>(400,"Book not found for ISBN: " + isbn);
        }
    }
}
