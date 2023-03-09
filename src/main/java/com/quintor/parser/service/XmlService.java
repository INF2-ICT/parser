package com.quintor.parser.service;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import com.quintor.parser.interfaces.FileParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class XmlService implements FileParser {

    @Override
    public String parseFile(MultipartFile file) throws IOException {
        MT940 toXml = MT940.parse(file.getInputStream());
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<MT940>\n" +
                "   <:20:>\n" +
                "       <reference>" + toXml.getField20().getReference() + "</reference>\n" +
                "   </:20:>\n" +
                "   <:25:>\n" +
                "       <acccount>" + toXml.getField25().getAccount() + "</account>\n" +
                "   </:25:>\n" +
                "   <:28C:>\n" +
                "       <statementNumber>" + toXml.getField28C().getStatementNumber() + "</statementNumber\n>" +
                "       <sequenceNumber>" + toXml.getField28C().getSequenceNumber() + "</sequenceNumber>\n" +
                "   </:28C>\n" +
                "   <:60F>\n" +
                "       <creditDebit>" + toXml.getField60F().getDCMark() + "</creditDebit>\n" +
                "       <date>" + toXml.getField60F().getDate() + "</date>\n" +
                "       <currency>" + toXml.getField60F().getCurrency() + "</currency>\n" +
                "       <amount>" + toXml.getField60F().getAmount() + "</amount>\n" +
                "   </:60F:>\n" +
                "   <:62F:>\n" +
                "       <creditDebit>" + toXml.getField62F().getDCMark() + "</creditDebit>\n" +
                "       <date>" + toXml.getField62F().getDate() + "</date>\n" +
                "       <currency>" + toXml.getField62F().getCurrency() + "</currency>\n" +
                "       <amount>" + toXml.getField62F().getAmount() + "</amount>\n" +
                "   </:62F:>\n" +
                "</MT940>\n";
        return xml;
    }
}
