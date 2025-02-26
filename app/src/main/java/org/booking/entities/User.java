package org.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String password;
    private String hashPassword;
    private List<Ticket> ticketsbooked;
    private String userId;

    public User(String name, String password, String hashPassword, List<Ticket> ticketsbooked, String userId) {
        this.name = name;
        this.password = password;
        this.hashPassword = hashPassword;
        this.ticketsbooked = ticketsbooked;
        this.userId = userId;
    }

    public User(){}

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getHashPassword() {
        return this.hashPassword;
    }

    public List<Ticket> getTicketsbooked() {
        return this.ticketsbooked;
    }

    public void printTickets(){
        for(int i = 0; i<this.ticketsbooked.size(); i++){
            System.out.println(this.ticketsbooked.get(i).getTicketInfo());
        }
    }

    public String getUserId() {
        return this.userId;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setHashPassword(String newHashPassword) {
        this.hashPassword = newHashPassword;
    }

    public void setTicketsbooked(List<Ticket> newTicketsbooked) {
        this.ticketsbooked = newTicketsbooked;
    }
    public void setUserId(String newUserId) {
        this.userId = newUserId;
    }
}
