package lt.bank.account.demo.project.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import lt.bank.account.demo.project.enums.Currency;
import lt.bank.account.demo.project.model.Operation;
import lt.bank.account.demo.project.repository.OperationRepository;
import lt.bank.account.demo.project.utils.CsvUtils;
import lt.bank.account.demo.project.utils.LocalDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class IOService {

    @Autowired
    private OperationRepository operationRepository;

    public List<String[]> readAllLines(MultipartFile file) throws IOException {
        try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    public ResponseEntity<Void> readFromFile(MultipartFile file) {
        try {
            saveNewOperations(readAllLines(file));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            log.error("Cannot read file {}", file.getName());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public void writeAllLines(List<String[]> lines, HttpServletResponse response) throws IOException {
        CSVWriter writer = new CSVWriter(response.getWriter());
        lines.forEach(writer::writeNext);
        writer.close();
    }

    public ResponseEntity<Void> exportToFile(HttpServletResponse response, String from, String to) {

        try {
            List<Operation> filteredOperations = getFilteredOperationsByDates(from, to);
            writeAllLines(CsvUtils.sixColumnsCsvString(filteredOperations), response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            log.error("An error was occurred while writing data into the file");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e){
            log.error("An error occurred while exporting data to file. {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private List<Operation> getFilteredOperationsByDates(String from, String to) {
        LocalDateTime dateFrom = LocalDateUtils.checkAndParseLocalDatetime(from);
        LocalDateTime dateTo = LocalDateUtils.checkAndParseLocalDatetime(to);
        return operationRepository.findAllByAccountDateFromDateTo(null, dateFrom, dateTo);
    }

    private void saveNewOperations(List<String[]> lines) {
        lines.forEach(line -> {
            try {
                operationRepository.save(createNewOperation(line));
            } catch (Exception e) {
                log.error("Cannot import line {} from provided CSV file. {}", line, e.getMessage());
            }
        });
    }

    private Operation createNewOperation(String[] line) {
        return Operation.builder()
                .accountNumber(line[0])
                .date(LocalDateUtils.parseToLocalDateTime(line[1]))
                .beneficiary(line[2])
                .comment(line[3])
                .amount(Double.parseDouble(line[4]))
                .currency(Currency.valueOf(line[5]))
                .build();
    }

}
