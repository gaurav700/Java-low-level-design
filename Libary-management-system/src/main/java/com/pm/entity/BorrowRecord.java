package com.pm.entity;

public class BorrowRecord {
    private final String userId;
    private final String bookCopyId;
    private final long issueDate;
    private Long returnDate;
    private final long dueDate;
    private BookStatus bookStatus;

    public BorrowRecord(String userId, String bookCopyId, long dueDate, long issueDate) {
        this.userId = userId;
        this.bookCopyId = bookCopyId;
        this.dueDate = dueDate;
        this.bookStatus = BookStatus.ISSUED;
        this.issueDate = issueDate;
    }

    public void markReturned(){
        this.returnDate = System.currentTimeMillis();
        this.bookStatus = BookStatus.AVAILABLE;
    }

    public boolean isOverdue() {
        return System.currentTimeMillis() > dueDate && returnDate == null;
    }


    public Long getReturnDate() {
        return returnDate;
    }

    public String getBookCopyId(){
        return bookCopyId;
    }
}
