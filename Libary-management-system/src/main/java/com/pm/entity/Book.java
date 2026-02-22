package com.pm.entity;

import java.util.UUID;

public class Book {
    private final String bookId;
    private String ISBN;
    private String title;
    private String author;

    public Book(String ISBN, String title, String author) {
        this.bookId = UUID.randomUUID().toString();
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
