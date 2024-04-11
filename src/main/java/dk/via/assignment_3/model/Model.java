package dk.via.assignment_3.model;

import dk.via.assignment_3.shared.Chat;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model {
    public ArrayList<Message> getMessages() throws RemoteException;

    public void addMessage(Message message) throws Exception;

    public void setMessages(ArrayList<Message> messages) throws RemoteException;

    public Chat getChat();
}
