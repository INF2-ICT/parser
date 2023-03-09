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
                "       <account>" + toXml.getField25().getAccount() + "</account>\n" +
                "   </:25:>\n" +
                "   <:28C:>\n" +
                "       <statementNumber>" + toXml.getField28C().getStatementNumber() + "</statementNumber>\n" +
                "       <sequenceNumber>" + toXml.getField28C().getSequenceNumber() + "</sequenceNumber>\n" +
                "   </:28C:>\n" +
                "   <:60F:>\n" +
                "       <creditDebit>" + toXml.getField60F().getDCMark() + "</creditDebit>\n" +
                "       <date>" + toXml.getField60F().getDate() + "</date>\n" +
                "       <currency>" + toXml.getField60F().getCurrency() + "</currency>\n" +
                "       <amount>" + toXml.getField60F().getAmount() + "</amount>\n" +
                "   </:60F:>\n" +
                statementline(toXml) +
                "   <:62F:>\n" +
                "       <creditDebit>" + toXml.getField62F().getDCMark() + "</creditDebit>\n" +
                "       <date>" + toXml.getField62F().getDate() + "</date>\n" +
                "       <currency>" + toXml.getField62F().getCurrency() + "</currency>\n" +
                "       <amount>" + toXml.getField62F().getAmount() + "</amount>\n" +
                "   </:62F:>\n" +
                "</MT940>\n";
        return xml;
    }

    private String statementline(MT940 lines) {
        String xmlPart = "";
        for (int i = 0; i < lines.getField61().size(); i++) {
            xmlPart +=      "       <statement" + i + ">\n" +
                            "           <:61:>\n" +
                            "               <valueDate>" + lines.getField61().get(i).getValueDate() + "</valueDate>\n" +
                            "               <entryDate>" + lines.getField61().get(i).getEntryDate() + "</entryDate>\n" +
                            "               <creditDebit>" + lines.getField61().get(i).getDebitCreditMark() + "</creditDebit>\n" +
                            "               <fundCode>" + lines.getField61().get(i).getFundsCode() + "</fundCode>\n" +
                            "               <amount>" + lines.getField61().get(i).getAmount() + "</amount>\n" +
                            "               <identifierCode>" + lines.getField61().get(i).getIdentificationCode() + "</identifierCode>\n" +
                            "               <customerReference>" + lines.getField61().get(i).getReferenceForTheAccountOwner() + "</customerReference>\n" +
                            "               <bankReference>" + lines.getField61().get(i).getReferenceOfTheAccountServicingInstitution() + "</bankReference>\n" +
                            "               <supplementaryDetails>" + lines.getField61().get(i).getSupplementaryDetails() + "</supplementaryDetails>\n" +
                            "           </:61:>\n" +
                            "           <:86:>\n" +
                            "               <line1>" + lines.getField86().get(i).getLine(1) + "</line1>\n" +
                            "               <line2>" + lines.getField86().get(i).getLine(2) + "</line2>\n" +
                            "               <line3>" + lines.getField86().get(i).getLine(3) + "</line3>\n" +
                            "               <line4>" + lines.getField86().get(i).getLine(4) + "</line4>\n" +
                            "               <line5>" + lines.getField86().get(i).getLine(5) + "</line5>\n" +
                            "               <line6>" + lines.getField86().get(i).getLine(6) + "</line6>\n" +
                            "           </:86:>\n" +
                            "       </statement" + i + ">\n";
        }
        return xmlPart;
    }
}
