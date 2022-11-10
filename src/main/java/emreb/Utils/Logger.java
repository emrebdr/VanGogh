package emreb.Utils;

import emreb.Models.LogLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\033[0;34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static LogLevel Log(LogLevel logLevel, String message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        if (logLevel == LogLevel.INFO) {
            System.out.println(ANSI_BLUE + "[INFO] [" + dtf.format(now) + "] " + message + ANSI_RESET);
            return LogLevel.INFO;
        } else if (logLevel == LogLevel.DEBUG) {
            System.out.println(ANSI_WHITE + "[DEBUG] [" + dtf.format(now) + "] " + message + ANSI_RESET);
            return LogLevel.DEBUG;
        } else if (logLevel == LogLevel.WARNING) {
            System.out.println(ANSI_YELLOW + "[WARNING] [" + dtf.format(now) + "] " + message + ANSI_RESET);
            return LogLevel.WARNING;
        }

        System.out.println(ANSI_RED + "[ERROR] [" + dtf.format(now) + "] " + message + ANSI_RESET);
        return LogLevel.ERROR;
    }
}
