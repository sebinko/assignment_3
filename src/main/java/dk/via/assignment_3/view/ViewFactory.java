package dk.via.assignment_3.view;

import dk.via.assignment_3.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory {
    public static final String LOGIN = "LOGIN";
    public static final String CHAT = "CHAT";

    private final ViewHandler viewHandler;

    private final ViewModelFactory viewModelFactory;

    private ChatViewController chatViewController;


    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.chatViewController = null;
    }

    public Region loadChatView() {
        if (chatViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ChatView.fxml"));
            try {
                Region root = loader.load();
                chatViewController = loader.getController();
                chatViewController.init(viewHandler, viewModelFactory.getChatViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        chatViewController.reset();
        return chatViewController.getRoot();
    }

    public Region loadLoginView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginView.fxml"));
        try {
            Region root = loader.load();
            LoginViewController loginViewController = loader.getController();
            loginViewController.init(viewHandler, viewModelFactory.getLoginViewModel(), root);
            return root;
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    public Region load(String id) {
        Region root = switch(id) {
            case CHAT -> loadChatView();
            case LOGIN -> loadLoginView();
            default -> throw new IllegalArgumentException("Unknown view: " + id);
        };
        return root;
    }
}
