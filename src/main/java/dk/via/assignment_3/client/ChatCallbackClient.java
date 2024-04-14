package dk.via.assignment_3.client;

import dk.via.assignment_3.model.Message;
import dk.via.assignment_3.model.Model;
import dk.via.assignment_3.util.ClientUser;
import dk.via.assignment_3.util.User;
import dk.via.assignment_3.viewmodel.ChatViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatCallbackClient extends UnicastRemoteObject implements ChatReceiver {
    private Model model;
    private ChatViewModel viewModel;

    public ChatCallbackClient(Model model, ChatViewModel viewModel) throws RemoteException {
        super(0);
        this.model = model;
        this.viewModel = viewModel;
    }

    @Override
    public void receiveMessages(ArrayList<Message> messages) throws Exception {
        Platform.runLater(() -> {
            ObservableList<Message> observableMessages = FXCollections.observableArrayList(messages);
            viewModel.setMessages(observableMessages);
        });
    }

    @Override
    public void receiveUsersUpdate(ArrayList<User> users) throws Exception {
        Platform.runLater(() -> {
            ObservableList<User> observableUsers = FXCollections.observableArrayList(users);
            viewModel.setUsers(observableUsers);

            viewModel.setUsersCountString("Users online: " + users.size());
        });

    }

    @Override
    public User getUser() throws Exception {
        return ClientUser.getInstance();
    }
}
