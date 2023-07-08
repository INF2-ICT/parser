package com.quintor.parser.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class XmlServiceTest {

    XmlService xmlFile;

    @BeforeEach
    void setUp() {
        xmlFile = new XmlService();
    }

    /**
     * making a unit test for mt940 file parsing to xml the equals version
     * @throws IOException
     */
    @Test
    void parseFileXmlEquals() throws IOException {

        //grab the file
        File xmlTest1File = new File("src\\test\\java\\com\\quintor\\parser\\files\\test.940");

        //make a buffered input stream, so it doesn't load all at once and make a file input stream inside
        BufferedInputStream xmlBs1 = new BufferedInputStream(new FileInputStream(xmlTest1File));

        //make a byte array so all the bytes can be read from the file
        byte[] xmlFileContent = xmlBs1.readAllBytes();

        //make a mock from the Multi part file so you don't need a live file
        MockMultipartFile xmlFileTest = new MockMultipartFile("file", "test.940", "text/plain", xmlFileContent);

        String xmlResult = xmlFile.parseFile(xmlFileTest);

        assertEquals(xmlResult, xmlResult, "the xml file isn't the same as the other file or something is broken in XmlService");

    }


    /**
     * making a unit test for mt940 file parsing to xml the not equals version
     * @throws Exception
     */
    @Test
    void parseFileXmlNotEquals() throws Exception {

        //grab the file
        File xmlTest1File = new File("src\\test\\java\\com\\quintor\\parser\\files\\test.940");

        File xmlTest2File = new File("src\\test\\java\\com\\quintor\\parser\\files\\test3.940");

        //make a buffered input stream, so it doesn't load all at once and make a file input stream inside
        BufferedInputStream xmlBs1 = new BufferedInputStream(new FileInputStream(xmlTest1File));

        BufferedInputStream xmlBs2 = new BufferedInputStream(new FileInputStream(xmlTest2File));

        //make a byte array so all the bytes can be read from the file
        byte[] xmlFileContent = xmlBs1.readAllBytes();

        byte[] xmlFileContent2 = xmlBs2.readAllBytes();

        //make a mock from the Multi part file so you don't need a live file
        MockMultipartFile xmlFileTest1 = new MockMultipartFile("file", "test.940", "text/plain", xmlFileContent);

        MockMultipartFile xmlFileTest2 = new MockMultipartFile("file", "test3.940", "text/plain", xmlFileContent2);

        String xmlResultTest1 = xmlFile.parseFile(xmlFileTest1);

        String xmlResultTest2 = xmlFile.parseFile(xmlFileTest2);

        assertNotEquals(xmlResultTest1, xmlResultTest2, "the xml file is the same as the other file which shouldn't happen so something is broken in XmlService");
    }
}