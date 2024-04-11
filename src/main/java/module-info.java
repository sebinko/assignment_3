module dk.via.assignment_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires remoteobserver;
    requires java.logging;
    requires java.desktop;


    opens dk.via.assignment_3 to javafx.fxml;
    opens dk.via.assignment_3.view to javafx.fxml;
    exports dk.via.assignment_3.client to java.rmi;
    exports dk.via.assignment_3;
    exports dk.via.assignment_3.view to javafx.fxml;
    exports dk.via.assignment_3.shared to java.rmi;
}