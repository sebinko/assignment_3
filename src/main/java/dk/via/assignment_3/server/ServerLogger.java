package dk.via.assignment_3.server;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerLogger {
    private static ServerLogger instance;
    private Logger logger;

    private ServerLogger() {
        logger = Logger.getLogger("ServerLogger");
        try {
            String logFileName = "log-" + java.time.LocalDate.now() + ".log";

            FileHandler fileHandler = new FileHandler(logFileName, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize logger", e);
        }
    }

    public static synchronized ServerLogger getInstance() {
        if (instance == null) {
            instance = new ServerLogger();
        }
        return instance;
    }

    public void log(Level level, String message) {
        logger.log(level, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void severe(String message) {
        log(Level.SEVERE, message);
    }
}
