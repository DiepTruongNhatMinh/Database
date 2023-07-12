package com.example.database;

public class Book {
    private int book_id;
    private String title;
    private int author_id;

    public Book(int book_id, String title, int author_id) {
        this.book_id = book_id;
        this.title = title;
        this.author_id = author_id;
    }
    public Book() {
        this.book_id = 0;
        this.title = null;
        this.author_id = 0;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
