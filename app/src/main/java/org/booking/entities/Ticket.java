package org.booking.entities;

import java.util.Date;
import java.util.List;

public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private String dateofTravel;
    private Train train;

    public Ticket(){}

    public Ticket(String ticketId, String userId, String source, String destination, Train train) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.train = train;
    }

    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", this.ticketId, this.userId, this.source, this.destination, this.dateofTravel);
    }

    public String getTicketId(){
        return this.ticketId;
    }

    public void setTicketId(String ticketId){
        this.ticketId = ticketId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getSource(){
        return this.source;
    }

    public void setSource(String source){
        this.source = source;
    }

    public String getDestination(){
        return this.destination;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public Train getTrain(){
        return this.train;
    }

    public void setTrain(Train train){
        this.train = train;
    }

    public String getDateOfTravel(){
        return this.dateofTravel;
    }

    public void setDateOfTravel(String dateOfTravel){
        this.dateofTravel = dateOfTravel;
    }


}
