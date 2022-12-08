package lt.bank.account.demo.project.service;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import lt.bank.account.demo.project.enums.Currency;
import lt.bank.account.demo.project.model.Operation;
import lt.bank.account.demo.project.repository.OperationRepository;
import lt.bank.account.demo.project.utils.LocalDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private void saveNewOperations(List<String[]> lines) {
        lines.forEach(line -> {
            try {
                operationRepository.save(createNewOperation(line));
            } catch (Exception e) {
                log.error("Cannot save line {} from provided CSV file", line, e);
            }
        });

    }

    private Operation createNewOperation(String[] line) {
        LocalDateTime date = LocalDateUtils.parseToLocalDateTime(line[2]);
        if (date == null) {
            throw new IllegalArgumentException();
        }
        return Operation.builder()
                .accountNumber(line[0])
                .date(date)
                .beneficiary(line[2])
                .comment(line[3])
                .amount(Double.parseDouble(line[4]))
                .currency(Currency.valueOf(line[5]))
                .build();
    }

}
