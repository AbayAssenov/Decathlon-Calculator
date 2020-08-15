package com.abay.assenov.service.impl;

import com.abay.assenov.model.Athlet;
import com.abay.assenov.service.MapperModel;

import java.util.ArrayList;
import java.util.List;

import static com.abay.assenov.constant.Contstans.*;
import static com.abay.assenov.constant.Contstans.DECATHLON_1500_M;

public class MapperModeAthletImpl implements MapperModel<Athlet> {

    @Override
    public List<Athlet> mapDataFromListString(List<String> content) {
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
}
