package lt.bank.account.demo.project.controller;

import lombok.extern.slf4j.Slf4j;
import lt.bank.account.demo.project.model.Operation;
import lt.bank.account.demo.project.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping()
    public List<Operation> getOperations() {
        return operationService.getAllOperations();
    }
}
