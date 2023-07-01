package com.example.zoeparrishcustomersupport;

import java.util.Collection;
import java.util.HashMap;

public class Ticket {
    //fields
    private String customerName;
    private String subject;
    private String body;
    private HashMap<Integer,Attachment> attachments;

    public Ticket(String customerName, String subject, String body, HashMap<Integer,Attachment> attachments){
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
    }
    public Ticket(){}

    //basic get/set
    void setCustomerName(String n){
        customerName = n;
    }
    String getCustomerName(){
        return customerName;
    }
    void setSubject(String s){
        subject = s;
    }
    String getSubject(){
        return subject;
    }
    void setBody(String b){
        body = b;
    }
    String getBody(){
        return body;
    }
    void setAttachments(HashMap<Integer, Attachment> a) {
        this.attachments = a;
    }
    HashMap<Integer, Attachment> getAttachments() {
        return attachments;
    }

    //gets # of attachments
    int getNumOfAttachments(){
        return attachments.size();
    }

    //adds an attachment
    void addAttachment(Attachment a) {
        if (this.attachments == null) {
            attachments.put(0, a);
        } else {
            attachments.put(attachments.size(), a);
        }
    }
    //gets an attachment specified by the id
    Attachment getAttachment(int x){
        return attachments.get(x);
    }

    //get all attachments in a collection
    Collection<Attachment> getAllAttachments(){
        return attachments.values();
    }
}
