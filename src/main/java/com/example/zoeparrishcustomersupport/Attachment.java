package com.example.zoeparrishcustomersupport;

public class Attachment {
    String name;
    byte[] contents;

    String getName(){
        return name;
    }
    byte[] getContents(){
        return contents;
    }
    void setName(String n){
        name = name;
    }
    void setContents(byte[] c){
        contents = c;
    }
}
