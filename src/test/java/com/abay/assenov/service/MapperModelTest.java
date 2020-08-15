package com.abay.assenov.service;

import com.abay.assenov.model.Athlet;
import com.abay.assenov.service.impl.MapperModeAthletImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MapperModelTest {

    private List<String> contentTest;
    private Athlet athletTest;

    @Before
    public void init() {
        contentTest = new ArrayList<>();

        String athletScoreTest = "Test;100.0;100.0;100.0;100.0;100.0;100.0;100.0;100.0;100.0;1:1.1";
        contentTest.add(athletScoreTest);

        athletTest = new Athlet();
        athletTest.setName("Test");
        athletTest.setDecathlon100M(100.0);
        athletTest.setDecathlonLongJump(100.0);
        athletTest.setDecathlonShotPut(100.0);
        athletTest.setDecathlonHighJump(100.0);
        athletTest.setDecathlon400M(100.0);
        athletTest.setDecathlon110MHurdles(100.0);
        athletTest.setDecathlonDiscussThrow(100.0);
        athletTest.setDecathlonPoleVault(100.0);
        athletTest.setDecathlonJavelinThrow(100.0);
        athletTest.setDecathlon1500M("1:1.1");
    }

    @Test
    public void map_model_from_string_after_chek_to_equal_to_hand_filed_model() {

        MapperModel<Athlet> mapperModel = new MapperModeAthletImpl();
        List<Athlet> athletListTest = mapperModel.mapDataFromListString(contentTest);
        Assert.assertEquals(athletListTest.get(0), athletTest);

    }
}