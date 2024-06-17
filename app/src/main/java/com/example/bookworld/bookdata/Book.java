package com.example.bookworld.bookdata;

public class Book {
    private String thumbnailUrl;
    private String title;

    public Book(String thumbnailUrl, String title) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }
}
