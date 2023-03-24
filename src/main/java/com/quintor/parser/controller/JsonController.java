package com.quintor.parser.controller;

import com.quintor.parser.service.JsonService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class JsonController {
    private JsonService jsonService;

    @Autowired
    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("/MT940toJSON")
    public String MT940toJSON(@RequestParam("file") File file) throws Exception {

        FileInputStream inputStream = new FileInputStream(file);

        MultipartFile result = new MockMultipartFile(file.getName(), file.getName(), "text/plain", inputStream.readAllBytes());
        
        return jsonService.parseFile(result);
    }
}
