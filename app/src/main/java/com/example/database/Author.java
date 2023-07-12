package com.example.database;

public class Author {
    private int author_id;
    private String author_name;
    private String author_address;
    private String author_email;

    public Author(int author_id, String author_name, String author_address, String author_email) {
        this.author_id = author_id;
        this.author_name = author_name;
        this.author_address = author_address;
        this.author_email = author_email;
    }

    public Author() {
        this.author_id = 0;
        this.author_name = null;
        this.author_address = null;
        this.author_email = null;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_address() {
        return author_address;
    }

    public void setAuthor_address(String author_address) {
        this.author_address = author_address;
    }

    public String getAuthor_email() {
        return author_email;
    }

    public void setAuthor_email(String author_email) {
        this.author_email = author_email;
    }
}

