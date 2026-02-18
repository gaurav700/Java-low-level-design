package com.pm.entity;

public class File extends FileSystemNode{
    private long fileSize;
    private String fileContent;

    public File(String name, User owner, Folder parentFolder, long size) {
        super(name, owner, parentFolder);
        this.fileSize = size;
    }

    @Override
    public long getSize() {
        return this.fileSize;
    }
}
