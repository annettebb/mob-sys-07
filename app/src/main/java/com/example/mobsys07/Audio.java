package com.example.mobsys07;

public class Audio {
    private String name;
    private String author;
    private int file;

    public Audio(String name, String author, Integer file) {
        this.name = name;
        this.author = author;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getFile() {
        return file;
    }
}
