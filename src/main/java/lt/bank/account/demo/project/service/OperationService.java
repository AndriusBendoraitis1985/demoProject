package lt.bank.account.demo.project.service;

import lombok.extern.slf4j.Slf4j;
import lt.bank.account.demo.project.model.Operation;
import lt.bank.account.demo.project.repository.OperationRepository;
import lt.bank.account.demo.project.utils.LocalDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public ResponseEntity<List<Operation>> getOperationsByAccountDateFromDateTo(String account, String from, String to) {
        try {
            LocalDateTime dateFrom = LocalDateUtils.checkAndParseLocalDatetime(from);
            LocalDateTime dateTo = LocalDateUtils.checkAndParseLocalDatetime(to);
            return new ResponseEntity<>(operationRepository.findAllByAccountDateFromDateTo(account, dateFrom, dateTo), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Request getting operations with parameters {}, {}, {} could not be executed", account, from, to);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
