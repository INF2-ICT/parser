package com.quintor.parser.service;

import com.quintor.parser.interfaces.FileParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class JsonService implements FileParser {

    @Override
    public String parseFile(MultipartFile file) {
        return "Works";
    }
}
