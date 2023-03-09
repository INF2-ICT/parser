package com.quintor.parser.controller;

import com.quintor.parser.service.XmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

@RestController
public class XmlController {
    private XmlService xmlService;

    @Autowired
    public XmlController(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @PostMapping("/MT940toXML")
    public String MT940toXML(@RequestParam("file") MultipartFile file) throws Exception {
        return xmlService.parseFile(file);
    }
}
