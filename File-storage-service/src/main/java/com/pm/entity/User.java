package com.pm.entity;

public class User {
    private String userId;
    private String name;
    private long storageUsed;
    private Folder rootFolder;
    private long totalStorage;

    public User(String userId, String name, long totalStorage) {
        this.userId = userId;
        this.name = name;
        this.totalStorage = totalStorage;
        this.storageUsed = 0;
        this.rootFolder = new Folder("root", this, null);
    }


    public boolean hasSpace(long fileSize){
        return storageUsed + fileSize <= totalStorage;
    }

    public void increaseUsage(long fileSize){
        storageUsed += fileSize;
    }
    public void decreaseUsage(long fileSize){
        storageUsed -= fileSize;
    }
    public Folder getRootFolder() {
        return rootFolder;
    }
}
