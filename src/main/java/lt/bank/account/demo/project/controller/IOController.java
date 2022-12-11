package lt.bank.account.demo.project.controller;

import lombok.extern.slf4j.Slf4j;
import lt.bank.account.demo.project.service.IOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/export")
    public ResponseEntity<Void> exportFile(
            HttpServletResponse response,
            @RequestParam(name = "dateFrom", required = false) String from,
            @RequestParam(name = "dateTo", required = false) String to) {

        String fileName = "balance.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"");

         return ioService.exportToFile(response, from, to);
    }
}
