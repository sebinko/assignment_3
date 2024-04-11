package dk.via.assignment_3.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Message implements Serializable {
    private String sender;
    private String message;
    private LocalDate timestamp;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
        this.timestamp = LocalDate.now();
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getFormattedTimestamp() {
        return timestamp.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s", getFormattedTimestamp(), sender, message);
    }

}
