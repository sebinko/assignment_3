package dk.via.assignment_3.shared;

import dk.via.assignment_3.client.ChatReceiver;
import dk.via.assignment_3.model.Message;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Chat extends Remote {
    void addPropertyChangeListener(String propertyName, RemotePropertyChangeListener listener) throws RemoteException;

    public ArrayList<Message> getMessages() throws RemoteException;

    public void addMessage(Message message) throws Exception;

    public void registerReceiver(ChatReceiver chatReceiver) throws Exception;

    public void removeReceiver(ChatReceiver chatReceiver) throws Exception;
}
