package com.quintor.parser.controller;

import com.quintor.parser.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;

@RestController
public class JsonController {
    private JsonService jsonService;

    @Autowired
    public JsonController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("/MT940toJSON")
    public String MT940toJSON(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        return jsonService.parseFile(multipartFile);
    }
}
