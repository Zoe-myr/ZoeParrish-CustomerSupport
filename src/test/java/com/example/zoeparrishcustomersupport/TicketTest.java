package com.example.zoeparrishcustomersupport;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    Attachment a = new Attachment();
    Attachment b = new Attachment();
    HashMap<Integer,Attachment> attachments =  new HashMap<>(){{
         put(0,a);
         put(1,b);
    }};
    Ticket t = new Ticket("jeff","car wash","the car wash is broken",attachments);

    @Test
    void testGetCustomerName(){
        assertEquals("jeff",t.getCustomerName());
    }
    @Test
    void testSetCustomerName(){
        t.setCustomerName("beth");
        assertEquals("beth",t.getCustomerName());
    }
    @Test
    void testGetSunject(){
      assertEquals("car wash",t.getSubject());
    }
    @Test
    void testSetSubject(){
        t.setSubject("car parts");
        assertEquals("car parts",t.getSubject());
    }
    @Test
    void testGetBody(){
        assertEquals("the car wash is broken",t.getBody());
    }
    @Test
    void testSetBody(){
        t.setBody("grilled ham and cheese");
        assertEquals("grilled ham and cheese",t.getBody());
    }
    @Test
    void testGetAttachments(){
        assertEquals(attachments,t.getAttachments());
    }
    @Test
    void testSetAttachments(){
        Attachment z = new Attachment();
        attachments.put(2,z);
        t.setAttachments(attachments);
        assertEquals(attachments,t.getAttachments());
    }
    @Test
    void testAddAttachment(){
        Attachment z = new Attachment();
        attachments.put(2,z);
        t.addAttachment(z);
        assertEquals(attachments,t.getAttachments());
    }
    @Test
    void testGetNumOfAttachments(){
        assertEquals(2,t.getNumOfAttachments());
    }
}