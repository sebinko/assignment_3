package dk.via.assignment_3.util;

import java.io.Serializable;
import java.util.UUID;

public class ClientUser extends User implements Serializable {
    private static ClientUser instance;

    private ClientUser() {
        super(UUID.randomUUID().toString());
    }

    public static ClientUser getInstance() {
        if (instance == null) {
            instance = new ClientUser();
        }

        return instance;
    }

    public void setUsername(String username) {this.username = username;
        System.out.println("ClientUser: " + username);
    }

    @Override
    public String toString() {
        return getUsername();
    }
}

