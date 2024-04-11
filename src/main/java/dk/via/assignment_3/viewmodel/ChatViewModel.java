package dk.via.assignment_3.viewmodel;

import dk.via.assignment_3.client.ChatCallbackClient;
import dk.via.assignment_3.model.Message;
import dk.via.assignment_3.model.Model;
import dk.via.assignment_3.util.ClientUser;
import dk.via.assignment_3.util.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

public class ChatViewModel implements PropertyChangeListener {
    private final Model model;

    private SimpleListProperty<Message> messages;
    private SimpleListProperty<User> users;
    private StringProperty usersCountString;

    private  ChatCallbackClient chatCallbackClient;

    public ChatViewModel(Model model) {
        this.model = model;
        this.messages = new SimpleListProperty<>();
        this.users = new SimpleListProperty<>();
        this.usersCountString = new SimpleStringProperty();
    }

    public void init() throws RemoteException {
        chatCallbackClient = new ChatCallbackClient(model, this);
        try {
            model.getChat().registerReceiver(chatCallbackClient);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return ClientUser.getInstance().getUsername();
    }

    public void addMessage(String chatMessage) throws Exception {
        Message message = new Message(getUsername(), chatMessage);
        model.addMessage(message);
    }

    public void setMessages(ObservableList<Message> newMessages) {
        this.messages.set(FXCollections.observableArrayList(newMessages));
    }

    public void setUsers(ObservableList<User> newUsers) {
        this.users.set(FXCollections.observableArrayList(newUsers));
    }

    public void setUsersCountString(String usersCountString) {
        this.usersCountString.set(usersCountString);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void quit() {
        try {
            model.getChat().removeReceiver(chatCallbackClient);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.exit(0);
    }

    public void bindMessages(ObjectProperty<ObservableList<Message>> property) {
        property.bindBidirectional(messages);
    }

    public void bindUsers(ObjectProperty<ObservableList<User>> property) {
        property.bindBidirectional(users);
    }

    public void bindUsersCountString(StringProperty property) {
        property.bind(usersCountString);
    }
}
