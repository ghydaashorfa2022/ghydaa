package com.example.myapplication.model;

public class noteUser {
     String id;
    String head;
    String content;

    public noteUser() {
    }

    public noteUser(String id, String head, String content) {
        this.id = id;
        this.head = head;
        this.content = content;
    }


    public String getId() {
        return id;
    }

    public String getHead() {

        return head;
    }

    public String getcontent() {
        return content;
    }
}


