package com.quintor.parser.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileParser {
    String parseFile(MultipartFile file) throws IOException;
}
