package com.pm.service;

import com.pm.entity.File;
import com.pm.entity.FileSystemNode;
import com.pm.entity.Folder;
import com.pm.entity.User;

import java.util.ArrayList;
import java.util.List;

public class StorageService {

    public File uploadFile(User user, Folder folder, String name, long size) {

        if (!folder.getOwner().equals(user)) {
            throw new IllegalStateException("Unauthorized access");
        }

        if (!user.hasSpace(size)) {
            throw new IllegalStateException("Quota exceeded");
        }

        File file = new File(name, user, folder, size);

        folder.addChild(file);
        user.increaseUsage(size);

        return file;
    }

    public Folder createFolder(User user, Folder parent, String name) {

        if (!parent.getOwner().equals(user)) {
            throw new IllegalStateException("Unauthorized access");
        }

        Folder folder = new Folder(name, user, parent);
        parent.addChild(folder);

        return folder;
    }

    public void deleteFile(User user, Folder folder, String name) {

        if (!folder.getOwner().equals(user)) {
            throw new IllegalStateException("Unauthorized access");
        }

        FileSystemNode node = folder.getChild(name);

        if (node == null || !(node instanceof File)) {
            throw new IllegalStateException("File not found");
        }

        folder.removeChild(name);
        user.decreaseUsage(node.getSize());
    }

    public void deleteFolder(User user, Folder folder) {

        if (!folder.getOwner().equals(user)) {
            throw new IllegalStateException("Unauthorized access");
        }

        Folder parent = folder.getParent();

        if (parent == null) {
            throw new IllegalStateException("Cannot delete root folder");
        }

        long size = folder.getSize();

        parent.removeChild(folder.getName());
        user.decreaseUsage(size);
    }

    public List<Folder> listAllFolders(Folder folder) {

        List<Folder> result = new ArrayList<>();

        for (FileSystemNode node : folder.getChildren()) {
            if (node instanceof Folder) {
                result.add((Folder) node);
            }
        }

        return result;
    }

    public User createUser(String userId, String name, long totalStorage) {
        return new User(userId, name, totalStorage);
    }
}
