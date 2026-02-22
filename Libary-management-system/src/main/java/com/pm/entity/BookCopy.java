package com.pm.entity;

import java.util.UUID;

public class BookCopy {
    private final String bookId;
    private final String copyId;
    private BookStatus status;

    public BookCopy(String bookId) {
        this.bookId = bookId;
        this.copyId = UUID.randomUUID().toString();
        this.status = BookStatus.AVAILABLE;
    }
    public String getBookId() {
        return bookId;
    }

    public String getCopyId() {
        return copyId;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void issue() {
        if (status != BookStatus.AVAILABLE) {
            throw new IllegalStateException("Book not available");
        }
        status = BookStatus.ISSUED;
    }

    public void markAvailable(){
        status =  BookStatus.AVAILABLE;
    }

}
