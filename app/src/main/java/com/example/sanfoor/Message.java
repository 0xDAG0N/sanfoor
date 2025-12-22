package com.example.sanfoor;

public class Message {
    public final String text;
    public final boolean isUser;

    public Message(String text, boolean isUser) {
        this.text = text;
        this.isUser = isUser;
    }
}
