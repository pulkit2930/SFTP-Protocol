package com.onedirect.sftpshopperstop.controller;

import com.onedirect.sftpshopperstop.service.ReadingFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ComponentScan
@Slf4j
public class TicketController {

    @Autowired
    private ReadingFile readingFile;

    @PostMapping(value = "/read")
    public void call(){
        try{
            log.info("calling func");
            readingFile.readDataFromExcel();

        }
        catch (Exception e)
        {
            log.error("Some error occured");
            throw new RuntimeException(e.getMessage());
        }

    }
    @GetMapping(value = "/status")
    public String getStatus(){
        return "HI";
    }
}
