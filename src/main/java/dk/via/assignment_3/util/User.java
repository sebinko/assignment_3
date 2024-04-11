package dk.via.assignment_3.util;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected String username;

    protected User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
