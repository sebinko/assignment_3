package dk.via.assignment_3.model;

import dk.via.assignment_3.shared.Chat;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements Model {
    private final Chat chat;

    public ModelManager(Chat chat) {
        this.chat = chat;
    }

    @Override
    public ArrayList<Message> getMessages() throws RemoteException {
        return chat.getMessages();
    }

    @Override
    public void addMessage(Message message) throws Exception {
        chat.addMessage(message);
    }

    @Override
    public void setMessages(ArrayList<Message> messages) throws RemoteException {

    }
}
