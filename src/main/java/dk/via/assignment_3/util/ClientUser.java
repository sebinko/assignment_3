package dk.via.assignment_3.util;

import java.io.Serializable;
import java.util.UUID;

public class ClientUser extends User implements Serializable {

    private ClientUser() {
        super(UUID.randomUUID().toString());
    }

    public static ClientUser getInstance() {
        return new ClientUser();
    }

    @Override
    public String toString() {
        return getUsername();
    }
}

