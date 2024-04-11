package dk.via.assignment_3.server;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerLogger {
    private static ServerLogger instance;
    private Logger logger;

    // Private constructor to initialize the logger
    private ServerLogger() {
        logger = Logger.getLogger("ServerLogger");
        try {
            FileHandler fileHandler = new FileHandler("server.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL); // Log all levels
            // Prevent logging messages from propagating to the parent logger
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize logger", e);
        }
    }

    // Public method to get the instance of the class
    public static synchronized ServerLogger getInstance() {
        if (instance == null) {
            instance = new ServerLogger();
        }
        return instance;
    }

    // Wrapper method for logging messages
    public void log(Level level, String message) {
        logger.log(level, message);
    }

    // Convenience methods for different logging levels
    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void severe(String message) {
        log(Level.SEVERE, message);
    }

    // Add more methods for other levels if needed
}
