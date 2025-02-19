package org.booking.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.booking.entities.Ticket;
import org.booking.entities.User;
import org.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String usersPath = "app/src/main/java/org/booking/localDb/users.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(usersPath);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(usersPath);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public boolean cancelBooking(String ticketId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the id of the user you would like to cancel: ");
        ticketId = scanner.next();

        if(ticketId == null || ticketId.isEmpty()){
            System.out.println("Ticket id cannot be empty or null");
            return Boolean.FALSE;
        }

        else{
            String finalTicketId = ticketId;
            boolean removed = user.getTicketsbooked().removeIf(t -> finalTicketId.equals(t.getTicketId()));
            if(removed){
                System.out.println("Ticket with id " + finalTicketId + " has been cancelled");
                return Boolean.TRUE;
            }
            else{
                System.out.println("No ticket found with id " + finalTicketId);
                return Boolean.FALSE;
            }
        }
    }
}
