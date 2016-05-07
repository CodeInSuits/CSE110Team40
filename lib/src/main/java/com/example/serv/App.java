package com.example.serv;

/**
 * Created by XuanpeiEstherOuyang on 5/6/16.
 */

import com.example.server.vo.Content;

public class App {
    public static void main(String[] args) {

        System.out.println("Sending POST to GCM");

        String apiKey = "AIzaSyBaLQaVXywAXzasKBrSh8PC7KmfJ0XnndI";
        Content content = createContent();

        Post2Gcm.post(apiKey, content);
    }

    public static Content createContent() {
        Content c = new Content();

        c.addRegId("");

        c.createData("Test title", "Test message");

        return c;
    }
}