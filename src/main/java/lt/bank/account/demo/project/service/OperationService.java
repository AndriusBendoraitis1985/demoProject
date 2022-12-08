package lt.bank.account.demo.project.service;

import lt.bank.account.demo.project.model.Operation;
import lt.bank.account.demo.project.repository.OperationRepository;
import lt.bank.account.demo.project.utils.LocalDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public List<Operation> getOperationsByAccountDateFromDateTo(String account, String from, String to) {
        LocalDateTime dateFrom = LocalDateUtils.parseToLocalDateTime(from);
        LocalDateTime dateTo = LocalDateUtils.parseToLocalDateTime(to);

        return operationRepository.findAllByAccountDateFromDateTo(account, dateFrom, dateTo);
    }
}
