package dk.via.assignment_3.server;

import dk.via.assignment_3.client.ChatReceiver;
import dk.via.assignment_3.model.Message;
import dk.via.assignment_3.shared.Chat;
import dk.via.assignment_3.util.ServerUser;
import dk.via.assignment_3.util.User;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatImplementation implements Chat {
    private final RemotePropertyChangeSupport<ArrayList<Message>> support;
    private ArrayList<Message> messages;

    private ArrayList<ChatReceiver> receivers;

    private ServerUser serverUser;

    public ChatImplementation() {
        this.support = new RemotePropertyChangeSupport<>();
        this.messages = new ArrayList<>();
        this.receivers = new ArrayList<>();
        this.serverUser = new ServerUser("SERVER");
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

        syncReceiverMessages();

        System.out.println("Message added: " + message);
        ServerLogger.getInstance().info("Message added: " + message);
    }

    private void syncReceiverMessages() throws Exception {
        for (ChatReceiver receiver : receivers) {
            receiver.receiveMessages(messages);
        }
    }

    @Override
    public void registerReceiver(ChatReceiver chatReceiver) throws Exception {
        receivers.add(chatReceiver);

        syncReceiverUsers();

        messages.add((new Message(serverUser.getUsername(), chatReceiver.getUser().getUsername() + " has joined the chat")));

        syncReceiverMessages();

        ServerLogger.getInstance().info("Receiver registered: " + chatReceiver.getUser().getUsername());
        System.out.println("Receiver registered: " + chatReceiver.getUser().getUsername());
    }

    @Override
    public void removeReceiver(ChatReceiver chatReceiver) throws Exception {
        receivers.remove(chatReceiver);

        syncReceiverUsers();

        messages.add((new Message(serverUser.getUsername(), chatReceiver.getUser().getUsername() + " has left the chat")));

        syncReceiverMessages();

        ServerLogger.getInstance().info("Receiver removed: " + chatReceiver.getUser().getUsername());
        System.out.println("Receiver removed: " + chatReceiver.getUser().getUsername());
    }

    private void syncReceiverUsers() throws Exception {
        for (ChatReceiver receiver : receivers) {
            receiver.receiveUsersUpdate(getUsers());
        }
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
