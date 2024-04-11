package dk.via.assignment_3.server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ChatImplementation chat = new ChatImplementation();
        Remote remote = UnicastRemoteObject.exportObject(chat, 0);

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.bind("chat", remote);

        ServerLogger.getInstance().info("Server running");
        System.out.println("Server running");
    }
}
