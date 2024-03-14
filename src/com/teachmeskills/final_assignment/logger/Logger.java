package com.teachmeskills.final_assignment.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.teachmeskills.final_assignment.consts.PathLoggerFile.*;

public class Logger{
    public static void logInfo(Date date, String infoMessage) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateToLog = simpleDateFormat.format(date);
        String message = "[INFO] - > " + dateToLog + " - > " + infoMessage + "\n";
        try {
            Files.write(Paths.get(PATH_INFO_LOF_FILE), message.getBytes(), StandardOpenOption.APPEND);
            writerExecutionLogFile(message);
        } catch (IOException ignored) { }
    }

    public static void logError(Date date, String errorMessage, Exception exception) {
        try (BufferedWriter writerError = new BufferedWriter(new FileWriter(PATH_ERROR_LOG_FILE, true))) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateFormat = simpleDateFormat.format(date);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n[ERROR] -> " + dateFormat + " -> " + errorMessage + "\n");

            StackTraceElement[] stackTraceElements = exception.getStackTrace();
            for (StackTraceElement element : stackTraceElements) {
                stringBuilder.append("\t\t\t" + element.toString());
                stringBuilder.append("\n");
            }

            writerError.write(stringBuilder.toString());
        } catch (IOException ignored) { }
    }

    private static void writerExecutionLogFile(String messageError) {
        try (BufferedWriter writerExecution = new BufferedWriter(new FileWriter(PATH_EXECUTION_LOG_FILE, true))) {
            writerExecution.write(messageError);
        } catch (IOException ignored) { }
    }
}
