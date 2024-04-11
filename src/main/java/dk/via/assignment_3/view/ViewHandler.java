package dk.via.assignment_3.view;

import dk.via.assignment_3.viewmodel.ViewModelFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler {
    private final Scene currentScene;

    private Stage primaryStage;

    private ViewFactory viewFactory;

    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewFactory = new ViewFactory(this, viewModelFactory);
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView(ViewFactory.LOGIN);
    }

    public void openView(String id) {
        Region root = viewFactory.load(id);
        currentScene.setRoot(root);
        if (root.getUserData() == null) {
            primaryStage.setTitle("");
        } else {
            primaryStage.setTitle(root.getUserData().toString());
        }
        primaryStage.setScene(currentScene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }

    public void closeView() {
        primaryStage.close();

    }
}