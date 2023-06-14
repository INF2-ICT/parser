package com.quintor.parser.controller;

import com.quintor.parser.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;

@RestController
public class XmlController {
    private XmlService xmlService;

    @Autowired
    public XmlController(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @PostMapping("/MT940toXML")
    public String MT940toXML(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        return xmlService.parseFile(multipartFile);
    }
}
