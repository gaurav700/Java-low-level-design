package com.pm.service;

import com.pm.entity.*;
import com.pm.strategy.BorrowPolicy;
import com.pm.utils.BorrowPolicyFactory;

import java.util.*;

public class LibraryService {

    private final Map<String, Book> booksByISBN = new HashMap<>();
    private final Map<String, BookCopy> copiesById = new HashMap<>();
    private final Map<String, User> usersById = new HashMap<>();
    private final Map<String, List<BorrowRecord>> borrowRecordsByUser = new HashMap<>();

    private static final long BORROW_DURATION =
            7L * 24 * 60 * 60 * 1000; // 7 days

    // =========================
    // User Registration
    // =========================

    public User registerUser(String username, Role role) {

        BorrowPolicy policy =
                BorrowPolicyFactory.createPolicy(role);

        User user = new User(username, role, policy);

        usersById.put(user.getUserId(), user);
        borrowRecordsByUser.put(user.getUserId(), new ArrayList<>());

        return user;
    }

    // =========================
    // Add Book (Metadata)
    // =========================

    public Book addBook(String ISBN,
                        String title,
                        String author) {

        if (booksByISBN.containsKey(ISBN)) {
            throw new IllegalStateException("Book already exists");
        }

        Book book = new Book(ISBN, title, author);
        booksByISBN.put(ISBN, book);

        return book;
    }

    // =========================
    // Add Book Copy
    // =========================

    public BookCopy addBookCopy(String ISBN) {

        Book book = booksByISBN.get(ISBN);

        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        BookCopy copy = new BookCopy(book.getISBN());

        copiesById.put(copy.getCopyId(), copy);

        return copy;
    }

    // =========================
    // Search Book
    // =========================

    public Book searchBook(String ISBN) {

        Book book = booksByISBN.get(ISBN);

        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        return book;
    }

    // =========================
    // Borrow Book
    // =========================

    public BorrowRecord borrowBook(String userId,
                                   String copyId) {

        User user = usersById.get(userId);
        BookCopy copy = copiesById.get(copyId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (copy == null) {
            throw new IllegalArgumentException("Book copy not found");
        }

        if (copy.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("Book already issued");
        }

        List<BorrowRecord> records =
                borrowRecordsByUser.get(userId);

        long activeBorrows =
                records.stream()
                        .filter(r -> r.getReturnDate() == null)
                        .count();

        if (!user.getBorrowPolicy()
                .canBorrow((int) activeBorrows)) {

            throw new IllegalStateException("Borrow limit exceeded");
        }

        // Issue book
        copy.issue();

        long issueDate = System.currentTimeMillis();
        long dueDate = issueDate + BORROW_DURATION;

        BorrowRecord record =
                new BorrowRecord(
                        userId,
                        copyId,
                        issueDate,
                        dueDate
                );

        records.add(record);

        return record;
    }

    // =========================
    // Return Book
    // =========================

    public void returnBook(String userId,
                           String copyId) {

        User user = usersById.get(userId);
        BookCopy copy = copiesById.get(copyId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (copy == null) {
            throw new IllegalArgumentException("Book copy not found");
        }

        List<BorrowRecord> records =
                borrowRecordsByUser.get(userId);

        Optional<BorrowRecord> optionalRecord =
                records.stream()
                        .filter(r -> r.getBookCopyId().equals(copyId)
                                && r.getReturnDate() == null)
                        .findFirst();

        if (optionalRecord.isEmpty()) {
            throw new IllegalStateException("Active borrow record not found");
        }

        BorrowRecord record = optionalRecord.get();

        record.markReturned();
        copy.markAvailable();
    }

    // =========================
    // Get Active Borrows
    // =========================

    public List<BorrowRecord> getActiveBorrows(String userId) {

        List<BorrowRecord> records =
                borrowRecordsByUser.get(userId);

        if (records == null) {
            return Collections.emptyList();
        }

        List<BorrowRecord> active = new ArrayList<>();

        for (BorrowRecord r : records) {
            if (r.getReturnDate() == null) {
                active.add(r);
            }
        }

        return active;
    }

}