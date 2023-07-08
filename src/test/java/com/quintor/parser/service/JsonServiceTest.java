package com.quintor.parser.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JsonServiceTest {

    JsonService jsonFile;

    @BeforeEach
    void setUp() {
        jsonFile = new JsonService();
    }

    @Test
    void parseFileJsonEquals() throws Exception {

        File jsonTest1File = new File("src\\test\\java\\com\\quintor\\parser\\files\\test.940");

        BufferedInputStream jsonBs1 = new BufferedInputStream(new FileInputStream(jsonTest1File));

        byte[] jsonFileContent = jsonBs1.readAllBytes();

        MockMultipartFile jsonFileTest = new MockMultipartFile("file", "test.940", "text/plain", jsonFileContent);

        String jsonResult = jsonFile.parseFile(jsonFileTest);

        assertEquals(jsonResult, jsonResult, "the json file isn't the same as the other file or something is broken in JsonService");
    }

    @Test
    void parseFileJsonNotEquals() throws Exception {

        File jsonTest1File = new File("src\\test\\java\\com\\quintor\\parser\\files\\test.940");

        File jsonTest2File = new File("src\\test\\java\\com\\quintor\\parser\\files\\test3.940");

        BufferedInputStream jsonBs1 = new BufferedInputStream(new FileInputStream(jsonTest1File));

        BufferedInputStream jsonBs2 = new BufferedInputStream(new FileInputStream(jsonTest2File));

        byte[] jsonFileContent = jsonBs1.readAllBytes();

        byte[] jsonFileContent2 = jsonBs2.readAllBytes();

        MockMultipartFile jsonFileTest1 = new MockMultipartFile("file", "test.940", "text/plain", jsonFileContent);

        MockMultipartFile jsonFileTest2 = new MockMultipartFile("file", "test3.940", "text/plain", jsonFileContent2);

        String jsonResultTest1 = jsonFile.parseFile(jsonFileTest1);

        String jsonResultTest2 = jsonFile.parseFile(jsonFileTest2);

        assertNotEquals(jsonResultTest1, jsonResultTest2, "the json file is the same as the other file which shouldn't happen so something is broken in JsonService");
    }
}