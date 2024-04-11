package dk.via.assignment_3;

import dk.via.assignment_3.client.ChatCallbackClient;
import dk.via.assignment_3.model.Model;
import dk.via.assignment_3.model.ModelManager;
import dk.via.assignment_3.shared.Chat;
import dk.via.assignment_3.view.ViewHandler;
import dk.via.assignment_3.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Registry registry = LocateRegistry.getRegistry(1099);
        Chat chat = (Chat) registry.lookup("chat");
        Model model = new ModelManager(chat);


        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);

        viewHandler.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}