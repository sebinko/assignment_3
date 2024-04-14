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

    private ChatView chatView;
    private LoginView loginView;


    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.chatView = null;
        this.loginView = null;
    }

    public Region loadChatView() {
        if (chatView == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ChatView.fxml"));
            try {
                Region root = loader.load();
                chatView = loader.getController();
                chatView.init(viewHandler, viewModelFactory.getChatViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        chatView.reset();
        return chatView.getRoot();
    }

    public Region loadLoginView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginView.fxml"));
        try {
            Region root = loader.load();
            loginView = loader.getController();
            loginView.init(viewHandler, viewModelFactory.getLoginViewModel(), root);
            return root;
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    public Region load(String id) {
        Region root = switch (id) {
            case CHAT -> loadChatView();
            case LOGIN -> loadLoginView();
            default -> throw new IllegalArgumentException("Unknown view: " + id);
        };
        return root;
    }
}
