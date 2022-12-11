package lt.bank.account.demo.project.utils;

import lt.bank.account.demo.project.model.Operation;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    private CsvUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String[]> sixColumnsCsvString(List<Operation> operations) {
        List<String[]> linesForExport = new ArrayList<>();
        operations.forEach(operation -> {
            String[] line = new String[6];
            line[0] = operation.getAccountNumber();
            line[1] = operation.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            line[2] = operation.getBeneficiary();
            line[3] = operation.getComment();
            line[4] = String.valueOf(operation.getAmount());
            line[5] = operation.getCurrency().name();
            linesForExport.add(line);
        });
        return linesForExport;
    }
}
