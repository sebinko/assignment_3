package dk.via.assignment_3.util;

import java.io.Serializable;

public class ServerUser extends User implements Serializable {
    public ServerUser(String username) {
        super(username);
    }
}
