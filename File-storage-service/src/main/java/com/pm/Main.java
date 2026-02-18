package com.pm;

import com.pm.entity.*;
import com.pm.service.StorageService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        StorageService storageService = new StorageService();

        // 1️⃣ Create User (quota = 1000 units)
        User user = storageService.createUser("U1", "Gaurav", 1000);

        Folder root = user.getRootFolder();

        System.out.println("User created: Gaurav");
        System.out.println("Root folder: " + root.getName());

        // 2️⃣ Create Folders
        Folder docs = storageService.createFolder(user, root, "Documents");
        Folder pics = storageService.createFolder(user, root, "Pictures");

        System.out.println("\nFolders created under root:");
        for (Folder folder : storageService.listAllFolders(root)) {
            System.out.println("- " + folder.getName());
        }

        // 3️⃣ Upload Files
        storageService.uploadFile(user, docs, "resume.pdf", 200);
        storageService.uploadFile(user, docs, "notes.txt", 100);
        storageService.uploadFile(user, pics, "photo.jpg", 300);

        System.out.println("\nStorage used after uploads: " + root.getSize());

        // 4️⃣ Delete a file
        storageService.deleteFile(user, docs, "notes.txt");
        System.out.println("\nDeleted notes.txt");
        System.out.println("Storage used: " + root.getSize());

        // 5️⃣ Delete a folder (recursive)
        storageService.deleteFolder(user, pics);
        System.out.println("\nDeleted Pictures folder");
        System.out.println("Final storage used: " + root.getSize());

        // 6️⃣ List final folders
        System.out.println("\nFinal folders in root:");
        List<Folder> finalFolders = storageService.listAllFolders(root);
        for (Folder folder : finalFolders) {
            System.out.println("- " + folder.getName());
        }
    }
}
