package lt.bank.account.demo.project.service;

import lt.bank.account.demo.project.model.Operation;
import lt.bank.account.demo.project.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

}
