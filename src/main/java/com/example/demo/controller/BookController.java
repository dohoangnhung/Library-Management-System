package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/book")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PostMapping(value = "/book/{isbn}")
    public void addBookItem(@PathVariable String isbn, @RequestParam String barcode) {
        bookService.addBookItem(isbn, barcode);
    }

    @PutMapping("/book/{isbn}")
    public void editBook(@PathVariable String isbn, @RequestBody Book book) {
        bookService.editBook(isbn, book);
    }

    @DeleteMapping("/book/{isbn}")
    public void removeBook(@PathVariable String isbn) {
        bookService.removeBook(isbn);
    }

    @GetMapping("/book/{keyword}")
    public List<Book> searchBooksByTitleOrAuthor(@PathVariable String keyword) {
        return bookService.searchBooksByTitleOrAuthor(keyword);
    }

    @GetMapping("/book/category/{category}")
    public List<Book> searchBooksByCategory(@PathVariable String category) {
        return bookService.searchBooksByCategory(category);
    }

}
