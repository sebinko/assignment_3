package dk.via.assignment_3.model;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model {
    public ArrayList<Message> getMessages() throws RemoteException;

    public void addMessage(Message message) throws Exception;

    public void setMessages(ArrayList<Message> messages) throws RemoteException;
}
