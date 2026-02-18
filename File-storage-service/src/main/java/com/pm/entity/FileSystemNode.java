package com.pm.entity;

public abstract class FileSystemNode {

    private final String name;
    private final User owner;
    private final long createdTime;
    private final Folder parentFolder;

    public FileSystemNode(String name, User owner, Folder parentFolder) {
        this.name = name;
        this.owner = owner;
        this.createdTime = System.currentTimeMillis();
        this.parentFolder = parentFolder;
    }

    public String getName() {
        return name;
    }

    public Folder getParent() {
        return parentFolder;
    }

    public User getOwner() {
        return owner;
    }

    public abstract long getSize();
}

