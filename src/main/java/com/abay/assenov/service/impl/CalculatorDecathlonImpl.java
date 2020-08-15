package com.abay.assenov.service.impl;

import com.abay.assenov.model.Athlet;
import com.abay.assenov.model.Athlets;
import com.abay.assenov.service.CalculatorDecathlon;
import com.abay.assenov.service.PropertyService;
import com.abay.assenov.service.ReaderDataCSV;
import com.abay.assenov.service.WriterDataXML;
import com.abay.assenov.util.ServiceUtil;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.abay.assenov.constant.Contstans.*;
import static com.abay.assenov.util.ServiceUtil.getCentimetersFromMeters;
import static com.abay.assenov.util.ServiceUtil.scoreDecathlonPointSystem;

public class CalculatorDecathlonImpl implements CalculatorDecathlon {

    private ReaderDataCSV readerDataCSV;
    private WriterDataXML<Athlets> writerDataXML;
    private PropertyService propertyService;

    public CalculatorDecathlonImpl(ReaderDataCSV readerDataCSV, WriterDataXMLImpl<Athlets> writerDataXML, PropertyService propertyService) {
        this.readerDataCSV = readerDataCSV;
        this.writerDataXML = writerDataXML;
        this.propertyService = propertyService;
    }

    private List<Athlet> calculate(List<String> content) {

        List<Athlet> athlets = parseAthletsData(content);

        for (Athlet athlet : athlets) {

            Double totalScore = DEFAULT_ZERO_SCORE;

            totalScore += scoreDecathlonPointSystem(COEF_A_100_M, COEF_B_100_M, COEF_C_100_M, athlet.getDecathlon100M());

            Double centimetersLongJump = getCentimetersFromMeters(athlet.getDecathlonLongJump());
            totalScore += scoreDecathlonPointSystem(COEF_A_LONG_JUMP, COEF_B_LONG_JUMP, COEF_C_LONG_JUMP, centimetersLongJump);

            totalScore += scoreDecathlonPointSystem(COEF_A_SHOT_PUT, COEF_B_SHOT_PUT, COEF_C_SHOT_PUT, athlet.getDecathlonShotPut());

            Double centimetersHighJump = getCentimetersFromMeters(athlet.getDecathlonHighJump());
            totalScore += scoreDecathlonPointSystem(COEF_A_HIGH_JUMP, COEF_B_HIGH_JUMP, COEF_C_HIGH_JUMP, centimetersHighJump);

            totalScore += scoreDecathlonPointSystem(COEF_A_400_M, COEF_B_400_M, COEF_C_400_M, athlet.getDecathlon400M());
            totalScore += scoreDecathlonPointSystem(COEF_A_110_M_HURDLES, COEF_B_110_M_HURDLES, COEF_C_110_M_HURDLES, athlet.getDecathlon110MHurdles());
            totalScore += scoreDecathlonPointSystem(COEF_A_DISCUSS_THROW, COEF_B_DISCUSS_THROW, COEF_C_DISCUSS_THROW, athlet.getDecathlonDiscussThrow());

            Double centimetersPoleVault = getCentimetersFromMeters(athlet.getDecathlonPoleVault());
            totalScore += scoreDecathlonPointSystem(COEF_A_POLE_VAULT, COEF_B_POLE_VAULT, COEF_C_POLE_VAULT, centimetersPoleVault);

            totalScore += scoreDecathlonPointSystem(COEF_A_JAVELIN_THROW, COEF_B_JAVELIN_THROW, COEF_C_JAVELIN_THROW, athlet.getDecathlonJavelinThrow());

            Double seconds1500M = ServiceUtil.getSecondsFromDecathlonTime(athlet.getDecathlon1500M());
            totalScore += scoreDecathlonPointSystem(COEF_A_1500_M, COEF_B_1500_M, COEF_C_1500_M, seconds1500M);

            athlet.setTotalScore(totalScore);
        }

        finishScoring(athlets); // Fill places and sort by scores

        return athlets;
    }

    private void finishScoring(List<Athlet> athlets) {

        athlets.sort(Comparator.reverseOrder()); // sort by scores

        int indexNext = 1; // here we take next index for below logic counting
        int currentPlace = 1; // start from 1st place
        while (indexNext < athlets.size()) {

            int indexCurrent = indexNext - 1;
            int indexAfterNext = indexNext + 1;

            if (Objects.nonNull(athlets.get(indexCurrent).getPlace())
                    && Objects.equals(athlets.get(indexCurrent).getTotalScore(), athlets.get(indexNext).getTotalScore())) {
                athlets.get(indexNext).setPlace(athlets.get(indexCurrent).getPlace()); // duplicate because here same place as previous
                indexNext++;
                continue;
            }

            if (Objects.equals(athlets.get(indexCurrent).getTotalScore(), athlets.get(indexNext).getTotalScore())) {
                athlets.get(indexCurrent).setPlace("" + currentPlace + "-" + ++currentPlace); // build shared place
                athlets.get(indexNext).setPlace(athlets.get(indexCurrent).getPlace()); // duplicate because here same place as previous
                currentPlace++;
                indexNext++;
                continue;
            }

            if (Objects.isNull(athlets.get(indexCurrent).getPlace())) {
                athlets.get(indexCurrent).setPlace("" + currentPlace++); // set not shared place for current
            }

            if (athlets.size() == indexAfterNext) { // here edge case for last
                athlets.get(indexNext).setPlace("" + currentPlace);   // set not shared place for last
            }

            indexNext++;
        }
    }

    private List<Athlet> parseAthletsData(List<String> content) {
        List<Athlet> athlets = new ArrayList<>();

        for (String record : content) {
            if (record.trim().isEmpty()) return athlets;

            String[] splited = record.split(DEFAULT_SEPARATOR);

            Athlet athlet = new Athlet();
            athlet.setName(splited[DECATHLON_NAME]);
            athlet.setDecathlon100M(Double.valueOf(splited[DECATHLON_100_M]));
            athlet.setDecathlonLongJump(Double.valueOf(splited[DECATHLON_LONG_JUMP]));
            athlet.setDecathlonShotPut(Double.valueOf(splited[DECATHLON_SHOT_PUT]));
            athlet.setDecathlonHighJump(Double.valueOf(splited[DECATHLON_HIGH_JUMP]));
            athlet.setDecathlon400M(Double.valueOf(splited[DECATHLON_400_M]));
            athlet.setDecathlon110MHurdles(Double.valueOf(splited[DECATHLON_110_M_HURDLES]));
            athlet.setDecathlonDiscussThrow(Double.valueOf(splited[DECATHLON_DISCUSS_THROW]));
            athlet.setDecathlonPoleVault(Double.valueOf(splited[DECATHLON_POLE_VAULT]));
            athlet.setDecathlonJavelinThrow(Double.valueOf(splited[DECATHLON_JAVELIN_THROW]));
            athlet.setDecathlon1500M(splited[DECATHLON_1500_M]);
            athlets.add(athlet);
        }

        return athlets;
    }


    public void execute(String pathToDataFile) {

        List<String> content = readerDataCSV.readAsListString(pathToDataFile); // getting raw data

        List<Athlet> result = calculate(content); // do the calculation from the received data

        String saveResultPath = propertyService.getPropertyValue(CONFIG_PROPERTIES, DEFAULT_SAVE_RESULT_PATH);
        String fileResultName = propertyService.getPropertyValue(CONFIG_PROPERTIES, DEFAULT_NAME_RESULT_FILE);
        writerDataXML.marshaling(saveResultPath, fileResultName, new Athlets(result), Athlets.class); // save the result
    }


}
