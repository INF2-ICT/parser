package com.quintor.parser.service;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import com.quintor.parser.interfaces.FileParser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class JsonService implements FileParser {

    /**
     * Function to parse MT940 file to JSON
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String parseFile(MultipartFile file) throws IOException {

        MT940 mt940parsed = MT940.parse(file.getInputStream());

        String jsonString = new JSONObject()
                .put("MT940",
                    new JSONObject()

                    .put("line20", new JSONObject().put("reference", mt940parsed.getField20().getReference() == null ? JSONObject.NULL : mt940parsed.getField20().getReference()))

                    .put("line25", new JSONObject().put("account", mt940parsed.getField25().getAccount() == null ? JSONObject.NULL : mt940parsed.getField25().getAccount()))


                    .put("line28C", new JSONObject()
                        .put("sequenceNumber", mt940parsed.getField28C().getSequenceNumber() == null ? JSONObject.NULL : mt940parsed.getField28C().getSequenceNumber())
                        .put("statementNumber", mt940parsed.getField28C().getStatementNumber() == null ? JSONObject.NULL : mt940parsed.getField28C().getStatementNumber())
                    )

                    .put("line60F", new JSONObject()
                        .put("creditDebit", mt940parsed.getField60F().getDCMark() == null ? JSONObject.NULL : mt940parsed.getField60F().getDCMark())
                        .put("date", mt940parsed.getField60F().getDate() == null ? JSONObject.NULL : mt940parsed.getField60F().getDate())
                        .put("currency", mt940parsed.getField60F().getCurrency() == null ? JSONObject.NULL : mt940parsed.getField60F().getCurrency())
                        .put("amount", mt940parsed.getField60F().getAmount() == null ? JSONObject.NULL : mt940parsed.getField60F().getAmount())
                    )

                    .put("statements", new JSONObject(
                        statementLine(mt940parsed)
                    ))

                    .put("line62F", new JSONObject()
                        .put("creditDebit", mt940parsed.getField62F().getDCMark() == null ? JSONObject.NULL : mt940parsed.getField62F().getDCMark())
                        .put("date", mt940parsed.getField62F().getDate() == null ? JSONObject.NULL : mt940parsed.getField62F().getDate())
                        .put("currency", mt940parsed.getField62F().getCurrency() == null ? JSONObject.NULL : mt940parsed.getField62F().getCurrency())
                        .put("amount", mt940parsed.getField62F().getAmount() == null ? JSONObject.NULL : mt940parsed.getField62F().getAmount())
                    )
                )
                .toString();

        return jsonString;
    }

    /**
     * Function to create JSONObject of all statements
     * @param mt940parsed
     * @return
     */
    private String statementLine(MT940 mt940parsed) {
        JSONObject json = new JSONObject();

        for (int i = 0; i < mt940parsed.getField61().size(); i++) {
            json.put("statement" + i, new JSONObject()
                .put("line61", new JSONObject()
                    .put("valueDate", mt940parsed.getField61().get(i).getValueDate() == null ? JSONObject.NULL : mt940parsed.getField61().get(i).getValueDate())
                    .put("entryDate", mt940parsed.getField61().get(i).getEntryDate() == null ? JSONObject.NULL : mt940parsed.getField61().get(i).getEntryDate())
                    .put("creditDebit", mt940parsed.getField61().get(i).getDebitCreditMark() == null ? JSONObject.NULL : mt940parsed.getField61().get(i).getDebitCreditMark())
                    .put("fundCode", mt940parsed.getField61().get(i).getFundsCode() == null ? JSONObject.NULL : mt940parsed.getField61().get(i).getFundsCode())
                    .put("amount", mt940parsed.getField61().get(i).getAmount() == null ? JSONObject.NULL : mt940parsed.getField61().get(i).getAmount())
                    .put("identifierCode", mt940parsed.getField61().get(i).getIdentificationCode() == null ? JSONObject.NULL : mt940parsed.getField61().get(i).getIdentificationCode())
                    .put("customerReference", mt940parsed.getField61().get(i).getReferenceForTheAccountOwner() == null ? JSONObject.NULL : mt940parsed.getField61().get(i).getReferenceForTheAccountOwner())
                    .put("bankReference", mt940parsed.getField61().get(i).getReferenceOfTheAccountServicingInstitution() == null ? JSONObject.NULL : mt940parsed.getField61().get(i).getReferenceOfTheAccountServicingInstitution())
                    .put("supplementaryDetails", mt940parsed.getField61().get(i).getSupplementaryDetails() == null ? JSONObject.NULL : mt940parsed.getField61().get(i).getSupplementaryDetails())
                )

                .put("line86", new JSONObject()
                    .put("line1", mt940parsed.getField86().get(i).getLine(1) == null ? JSONObject.NULL : mt940parsed.getField86().get(i).getLine(1))
                    .put("line2", mt940parsed.getField86().get(i).getLine(2) == null ? JSONObject.NULL : mt940parsed.getField86().get(i).getLine(2))
                    .put("line3", mt940parsed.getField86().get(i).getLine(3) == null ? JSONObject.NULL : mt940parsed.getField86().get(i).getLine(3))
                    .put("line4", mt940parsed.getField86().get(i).getLine(4) == null ? JSONObject.NULL : mt940parsed.getField86().get(i).getLine(4))
                    .put("line5", mt940parsed.getField86().get(i).getLine(5) == null ? JSONObject.NULL : mt940parsed.getField86().get(i).getLine(5))
                    .put("line6", mt940parsed.getField86().get(i).getLine(6) == null ? JSONObject.NULL : mt940parsed.getField86().get(i).getLine(6))
                )
            );
        }

        return json.toString();
    }
}
