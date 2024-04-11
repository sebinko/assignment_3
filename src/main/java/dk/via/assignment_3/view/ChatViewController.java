package dk.via.assignment_3.view;

import dk.via.assignment_3.model.Message;
import dk.via.assignment_3.util.User;
import dk.via.assignment_3.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class ChatViewController {
    private ViewHandler viewHandler;
    private ChatViewModel viewModel;
    private Region root;

    @FXML
    Label usernameLabel;

    @FXML
    ListView<Message> chatListView;

    @FXML
    TextField chatMessageField;

    @FXML
    Button chatMessageButton;

    @FXML
    ListView<User> usersListView;

    @FXML
    Label usersLabel;

    public void init(ViewHandler viewHandler, ChatViewModel chatViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = chatViewModel;
        this.root = root;

        usernameLabel.setText("User: " + viewModel.getUsername());
//        chatViewModel.bindFirst(firstOperand.textProperty());
//        chatViewModel.bindSecond(secondOperand.textProperty());
//        chatViewModel.bindResult(result.textProperty());
//        chatViewModel.bindError(error.textProperty());

        chatMessageButton.setOnAction(event -> {
            try {
                sendMessage();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        chatViewModel.bindMessages(chatListView.itemsProperty());
        chatViewModel.bindUsers(usersListView.itemsProperty());
        chatViewModel.bindUsersCountString(usersLabel.textProperty());
    }

    @FXML
    public void sendMessage() throws Exception {
        try {
            viewModel.addMessage(chatMessageField.getText());
        } catch (RemoteException e) {
            // TODO: Handle exception
        }
    }


    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}
