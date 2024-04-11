package dk.via.assignment_3.util;

import java.io.Serializable;

public abstract class User implements Serializable {
    private final String username;

    protected User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
