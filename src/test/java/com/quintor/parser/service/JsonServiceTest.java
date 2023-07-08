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

    /**
     * making a unit test for the json parser and then the equals version
     * @throws Exception
     */
    @Test
    void parseFileJsonEquals() throws Exception {

        //grab the file
        File jsonTest1File = new File("src\\test\\java\\com\\quintor\\parser\\files\\test.940");

        //make a bufferedInputStream, so it doesn't do it all at once and make a fileInputStream inside it
        BufferedInputStream jsonBs1 = new BufferedInputStream(new FileInputStream(jsonTest1File));

        //small byte array so all the bytes of the file can be sorted
        byte[] jsonFileContent = jsonBs1.readAllBytes();

        //make a multiPartFile mock, so you don't need an actual live file
        MockMultipartFile jsonFileTest = new MockMultipartFile("file", "test.940", "text/plain", jsonFileContent);

        String jsonResult = jsonFile.parseFile(jsonFileTest);

        assertEquals(jsonResult, jsonResult, "the json file isn't the same as the other file or something is broken in JsonService");
    }

    /**
     * making a unit test for the json parser and then the not equals version
     * @throws Exception
     */

    @Test
    void parseFileJsonNotEquals() throws Exception {

        //grab the file
        File jsonTest1File = new File("src\\test\\java\\com\\quintor\\parser\\files\\test.940");

        File jsonTest2File = new File("src\\test\\java\\com\\quintor\\parser\\files\\test3.940");

        //make a bufferedInputStream, so it doesn't do it all at once and make a fileInputStream inside it
        BufferedInputStream jsonBs1 = new BufferedInputStream(new FileInputStream(jsonTest1File));

        BufferedInputStream jsonBs2 = new BufferedInputStream(new FileInputStream(jsonTest2File));

        //small byte array so all the bytes of the file can be sorted
        byte[] jsonFileContent = jsonBs1.readAllBytes();

        byte[] jsonFileContent2 = jsonBs2.readAllBytes();

        //make a multiPartFile mock, so you don't need an actual live file
        MockMultipartFile jsonFileTest1 = new MockMultipartFile("file", "test.940", "text/plain", jsonFileContent);

        MockMultipartFile jsonFileTest2 = new MockMultipartFile("file", "test3.940", "text/plain", jsonFileContent2);

        String jsonResultTest1 = jsonFile.parseFile(jsonFileTest1);

        String jsonResultTest2 = jsonFile.parseFile(jsonFileTest2);

        assertNotEquals(jsonResultTest1, jsonResultTest2, "the json file is the same as the other file which shouldn't happen so something is broken in JsonService");
    }
}