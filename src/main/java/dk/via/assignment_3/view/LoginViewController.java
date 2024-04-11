package dk.via.assignment_3.view;

import dk.via.assignment_3.client.ChatCallbackClient;
import dk.via.assignment_3.util.ClientUser;
import dk.via.assignment_3.viewmodel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class LoginViewController {
    private ViewHandler viewHandler; 
    private LoginViewModel viewModel;
    private Region root;
    
    @FXML
    TextField usernameTextField;
    
    @FXML
    Button loginButton;


    public void init(ViewHandler viewHandler, LoginViewModel loginViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = loginViewModel;
        this.root = root;
        
        loginButton.setOnAction(event -> {
            try {
                login();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        viewModel.bindUsername(usernameTextField.textProperty());
    }

    private void login() throws Exception {
        viewModel.beforeLogin();
        System.out.println(ClientUser.getInstance().getUsername());
        viewHandler.openView(ViewFactory.CHAT);
    }
}
