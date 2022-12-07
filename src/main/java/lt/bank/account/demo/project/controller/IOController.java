package lt.bank.account.demo.project.controller;

import lombok.extern.slf4j.Slf4j;
import lt.bank.account.demo.project.service.IOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/file")
public class IOController {

    @Autowired
    private IOService ioService;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(@RequestBody MultipartFile file) {

        if (file == null || file.isEmpty()) {
            log.error("File is not provided or empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ioService.readFromFile(file);
    }
}
