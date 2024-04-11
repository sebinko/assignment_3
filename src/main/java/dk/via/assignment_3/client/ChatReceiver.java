package dk.via.assignment_3.client;

import dk.via.assignment_3.model.Message;
import dk.via.assignment_3.util.User;

import java.rmi.Remote;
import java.util.ArrayList;

public interface ChatReceiver extends Remote {
    void receiveMessages(ArrayList<Message> messages) throws Exception;
    void receiveUsersUpdate(ArrayList<User> users) throws Exception;
    User getUser() throws Exception;
}
