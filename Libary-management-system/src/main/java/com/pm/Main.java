package com.pm;

import com.pm.entity.*;
import com.pm.service.LibraryService;

public class Main {

    public static void main(String[] args) {

        LibraryService libraryService = new LibraryService();

        // ===============================
        // 1️⃣ Register Users
        // ===============================

        User normalUser =
                libraryService.registerUser("Gaurav", Role.USER_NORMAL);

        User premiumUser =
                libraryService.registerUser("Rahul", Role.USER_PREMIUM);

        System.out.println("Users registered successfully");

        // ===============================
        // 2️⃣ Add Book Metadata
        // ===============================

        Book book =
                libraryService.addBook(
                        "ISBN123",
                        "Clean Code",
                        "Robert Martin"
                );

        System.out.println("Book added: " + book.getTitle());

        // ===============================
        // 3️⃣ Add Book Copies
        // ===============================

        BookCopy copy1 =
                libraryService.addBookCopy("ISBN123");

        BookCopy copy2 =
                libraryService.addBookCopy("ISBN123");

        System.out.println("Book copies added");

        // ===============================
        // 4️⃣ Borrow Book (Normal User)
        // ===============================

        BorrowRecord record1 =
                libraryService.borrowBook(
                        normalUser.getUserId(),
                        copy1.getCopyId()
                );

        System.out.println("Normal user borrowed book copy: "
                + copy1.getCopyId());

        // ===============================
        // 5️⃣ Borrow Another Copy
        // ===============================

        BorrowRecord record2 =
                libraryService.borrowBook(
                        premiumUser.getUserId(),
                        copy2.getCopyId()
                );

        System.out.println("Premium user borrowed book copy: "
                + copy2.getCopyId());

        // ===============================
        // 6️⃣ Check Active Borrows
        // ===============================

        System.out.println("Normal User Active Borrows: "
                + libraryService
                .getActiveBorrows(normalUser.getUserId())
                .size());

        System.out.println("Premium User Active Borrows: "
                + libraryService
                .getActiveBorrows(premiumUser.getUserId())
                .size());

        // ===============================
        // 7️⃣ Return Book
        // ===============================

        libraryService.returnBook(
                normalUser.getUserId(),
                copy1.getCopyId()
        );

        System.out.println("Normal user returned book");

        // ===============================
        // 8️⃣ Active Borrows After Return
        // ===============================

        System.out.println("Normal User Active Borrows After Return: "
                + libraryService
                .getActiveBorrows(normalUser.getUserId())
                .size());

        System.out.println("Simulation Completed Successfully!");
    }
}