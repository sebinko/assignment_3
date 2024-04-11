package dk.via.assignment_3.server;

import dk.via.assignment_3.client.ChatReceiver;
import dk.via.assignment_3.model.Message;
import dk.via.assignment_3.shared.Chat;
import dk.via.assignment_3.util.User;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatImplementation implements Chat {
    private final RemotePropertyChangeSupport<ArrayList<Message>> support;
    private ArrayList<Message> messages;

    private ArrayList<ChatReceiver> receivers;

    public ChatImplementation() {
        this.support = new RemotePropertyChangeSupport<>();
        this.messages = new ArrayList<>();
        this.receivers = new ArrayList<>();
    }


    @Override
    public void addPropertyChangeListener(String propertyName, RemotePropertyChangeListener listener) throws RemoteException {
        support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public ArrayList<Message> getMessages() throws RemoteException {
        return messages;
    }

    @Override
    public void addMessage(Message message) throws Exception {
        messages.add(message);

        for (ChatReceiver receiver : receivers) {
            receiver.receiveMessages(messages);
        }

        System.out.println("Message added: " + message);
        ServerLogger.getInstance().info("Message added: " + message);
    }

    @Override
    public void registerReceiver(ChatReceiver chatReceiver) throws Exception {
        receivers.add(chatReceiver);

        for (ChatReceiver receiver : receivers) {
            receiver.receiveUsersUpdate(getUsers());
        }

        ServerLogger.getInstance().info("Receiver registered: " + chatReceiver.getUser().getUsername());
        System.out.println("Receiver registered: " + chatReceiver.getUser().getUsername());
    }

    private ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (ChatReceiver receiver : receivers) {
            try {
                users.add(receiver.getUser());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

}
