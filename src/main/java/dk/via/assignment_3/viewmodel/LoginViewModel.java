package dk.via.assignment_3.viewmodel;

import dk.via.assignment_3.client.ChatCallbackClient;
import dk.via.assignment_3.model.Model;
import dk.via.assignment_3.util.ClientUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeListener {
    private final Model model;

    private StringProperty username;

    public LoginViewModel(Model model) {
        this.model = model;
        username = new SimpleStringProperty();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void bindUsername(StringProperty property) {
        property.bindBidirectional(username);
    }

    public void beforeLogin() throws Exception {
        ClientUser.getInstance().setUsername(username.get());
    }
}
