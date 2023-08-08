import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class task1 {

    public static void main(String[] args) {
        log_message("application.log", "User logged in", LogLevel.INFO);
        log_message("application.log", "Failed login attempt", LogLevel.WARNING);
        log_message("application.log", "Database connection lost", LogLevel.ERROR);
        log_message("application.log", "Task successful", LogLevel.SUCCESS);
    }

    public enum LogLevel {
        INFO, WARNING, ERROR, SUCCESS
    }

    public static void log_message(String filename, String message, LogLevel level) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
        String timestamp = dateFormat.format(new Date());
        String logMessage = timestamp + " [" + level + "] " + message;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(logMessage);
            System.out.println("Logs: " + logMessage);
        } catch (IOException e) {
            System.out.println("Error writing log to file: " + e.getMessage());
        }
    }
}