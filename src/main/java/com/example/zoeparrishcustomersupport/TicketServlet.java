package com.example.zoeparrishcustomersupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;


@WebServlet(name="ticket", value="/ticket")
@MultipartConfig(fileSizeThreshold = 5_242_880,maxFileSize = 20_971_520L,maxRequestSize = 41_943_040L)
public class TicketServlet extends HttpServlet {

    private volatile int ticketId = 0;
    private HashMap<Integer,Ticket> ticketDB = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action== null){
            action = "list";
        }
        switch (action){
            case "create":
                this.showTicketForm(response);
                break;
            case "view":
                this.viewTicket(request,response);
                break;
            case "download":
                this.downloadAttachment(request,response);
                break;
            case"list":
            default:
                this.listTickets(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if(action== null){
            action = "list";
        }
        switch (action){
            case "create":
                this.createTicket(request,response);
                break;
            case "download":
            default:
                response.sendRedirect("tickets");
                break;
        }
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = new Ticket();
        ticket.setCustomerName("name");
        ticket.setSubject("subject");
        ticket.setBody("body");

        Part file = request.getPart("file1");
        if (file != null){
            Attachment attachment = this.processAttachment(file);
                if(attachment != null){
                    ticket.addAttachment(attachment);
                }
            }

        int id;
        synchronized (this){
            id =this.ticketId++;
            ticketDB.put(id,ticket);
            }
        response.sendRedirect("ticket?action=view&ticketId=" + id);
        }

    private  Attachment processAttachment(Part file) throws IOException {
        InputStream in = file.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int  read;
        final byte[] bytes= new byte[1024];
        while ((read = in.read(bytes)) != -1){
            out.write(bytes,0,read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(file.getSubmittedFileName());
        attachment.setContents(out.toByteArray());
        return attachment;
    }

    private void showTicketForm(HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();

    out.println("<html><body><h2>Create a ticket</h2>");
    out.println("<form action=\"ticket\" method=\"post\" enctype=\"multipart/form-data\">");
    out.println("<input type=\"hidden\" name=\"action\" value=\"create\">");
    out.println("Name:<input type=\"text\" name=\"name\"><br>");
    out.println("Subject:<input type=\"text\" name=\"subject\"><br>");
    out.println("Issue:<input type=\"text\" name= \"body\"><br>");
    out.println("<input type=\"file\" name=\"file1\"><br>");
    out.println("<input type=\"submit\">");
    out.println("</form></body></html>");

    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idString = request.getParameter("ticketId");
        if(idString ==null|| idString.length()==0){
            response.sendRedirect("ticket");
        }

        try{
            int id = Integer.parseInt(idString);
            Ticket ticket = ticketDB.get(id);
            PrintWriter out = response.getWriter();

            out.println("<html><body><h1>Ticket</h1>");
            out.println("Customer Name: " + ticket.getCustomerName() + "<br>");
            out.println("Subject: " + ticket.getSubject() + "<br>");
            out.println("Issue: "+ ticket.getBody()+ "<br>");

            if(ticket.getNumOfAttachments() > 0){
                out.println("<a href=\"ticket?action=download&ticketId=" + id+"&attachment=\">Attachment</a><br>");
            }
            out.println("<a href=\"ticket\">Return to tickets</a>");
            out.println("</body></html>");


        }catch (Exception e){
            response.sendRedirect("ticket");
        }
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) {
    }

    private void listTickets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body><h2>Tickets</h2>");
        out.println("<a href =\"ticket?action=create\">Create Ticket</a><br><br>");

        if (ticketDB.size()==0){
            out.println("There are no Tickets yet");
        }else{
            for (int id : ticketDB.keySet()){
                Ticket ticket = ticketDB.get(id);
                out.println("Ticket #"+ id);
                out.println(": <a href =\"ticket?action=view&ticketId="+id+"\"");
                out.println(ticket.getSubject()+"</a><br>");
            }
        }
        out.println("</body></html>");
    }
}
