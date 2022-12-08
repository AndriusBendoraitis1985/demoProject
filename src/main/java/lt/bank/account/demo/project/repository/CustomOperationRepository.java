package lt.bank.account.demo.project.repository;

import lt.bank.account.demo.project.model.Operation;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomOperationRepository {

    List<Operation> findAllByAccountDateFromDateTo(String account, LocalDateTime dateFrom, LocalDateTime dateTo);
}
