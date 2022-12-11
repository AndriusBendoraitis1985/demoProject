package lt.bank.account.demo.project.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class LocalDateUtils {

    private LocalDateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime parseToLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDateTime checkAndParseLocalDatetime(String date) {
        try {
            return StringUtils.isNotEmpty(date) ? parseToLocalDateTime(date) : null;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
    }
}
