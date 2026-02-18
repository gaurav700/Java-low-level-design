package com.pm.entity;

import java.util.*;

public class Folder extends FileSystemNode {

    private final Map<String, FileSystemNode> children = new HashMap<>();

    public Folder(String name, User owner, Folder parentFolder) {
        super(name, owner, parentFolder);
    }

    public void addChild(FileSystemNode child) {

        String name = child.getName();

        if (children.containsKey(name)) {
            throw new IllegalStateException("File/Folder already exists: " + name);
        }

        children.put(name, child);
    }

    public void removeChild(String name) {

        if (!children.containsKey(name)) {
            throw new IllegalStateException("File/Folder does not exist: " + name);
        }

        children.remove(name);
    }

    public FileSystemNode getChild(String name) {
        return children.get(name);
    }

    public Collection<FileSystemNode> getChildren() {
        return Collections.unmodifiableCollection(children.values());
    }

    @Override
    public long getSize() {

        long totalSize = 0;

        for (FileSystemNode node : children.values()) {
            totalSize += node.getSize();
        }

        return totalSize;
    }
}
