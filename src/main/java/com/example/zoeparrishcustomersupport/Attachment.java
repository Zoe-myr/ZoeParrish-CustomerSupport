package com.example.zoeparrishcustomersupport;

public class Attachment {
    private String name;
    private byte[] contents;

    String getName(){
        return name;
    }
    byte[] getContents(){
        return contents;
    }
    void setName(String n){
        name = n;
    }
    void setContents(byte[] c){
        contents = c;
    }
}
