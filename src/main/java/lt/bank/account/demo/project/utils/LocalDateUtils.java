package lt.bank.account.demo.project.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class LocalDateUtils {

    private LocalDateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime parseToLocalDateTime(String date) {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            log.error("Could not parse date {} to LocalDateTime", date);
            return null;
        }
    }
}
