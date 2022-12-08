package lt.bank.account.demo.project.controller;

import lombok.extern.slf4j.Slf4j;
import lt.bank.account.demo.project.model.Operation;
import lt.bank.account.demo.project.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping("/all")
    public List<Operation> getOperations() {
        return operationService.getAllOperations();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Operation>> getOperationsByAccountDateFromDateTo(
            @RequestParam(name = "account") String account,
            @RequestParam(name = "dateFrom", required = false) String from,
            @RequestParam(name = "dateTo", required = false) String to) {

        return new ResponseEntity<>(operationService.getOperationsByAccountDateFromDateTo(account, from, to), HttpStatus.OK);
    }
}
