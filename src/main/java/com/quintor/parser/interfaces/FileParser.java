package com.quintor.parser.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileParser {
    String parseFile(MultipartFile file);
}
