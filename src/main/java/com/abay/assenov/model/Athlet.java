package com.abay.assenov.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "athlet")
@XmlAccessorType(XmlAccessType.FIELD)
public class Athlet implements Comparable<Athlet>{
    private String name;
    private Double decathlon100M;
    private Double decathlonLongJump;
    private Double decathlonShotPut;
    private Double decathlonHighJump;
    private Double decathlon400M;
    private Double decathlon110MHurdles;
    private Double decathlonDiscussThrow;
    private Double decathlonPoleVault;
    private Double decathlonJavelinThrow;
    private String decathlon1500M;
    private Double totalScore;
    private String place;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDecathlon100M() {
        return decathlon100M;
    }

    public void setDecathlon100M(Double decathlon100M) {
        this.decathlon100M = decathlon100M;
    }

    public Double getDecathlonLongJump() {
        return decathlonLongJump;
    }

    public void setDecathlonLongJump(Double decathlonLongJump) {
        this.decathlonLongJump = decathlonLongJump;
    }

    public Double getDecathlonShotPut() {
        return decathlonShotPut;
    }

    public void setDecathlonShotPut(Double decathlonShotPut) {
        this.decathlonShotPut = decathlonShotPut;
    }

    public Double getDecathlonHighJump() {
        return decathlonHighJump;
    }

    public void setDecathlonHighJump(Double decathlonHighJump) {
        this.decathlonHighJump = decathlonHighJump;
    }

    public Double getDecathlon400M() {
        return decathlon400M;
    }

    public void setDecathlon400M(Double decathlon400M) {
        this.decathlon400M = decathlon400M;
    }

    public Double getDecathlon110MHurdles() {
        return decathlon110MHurdles;
    }

    public void setDecathlon110MHurdles(Double decathlon110MHurdles) {
        this.decathlon110MHurdles = decathlon110MHurdles;
    }

    public Double getDecathlonDiscussThrow() {
        return decathlonDiscussThrow;
    }

    public void setDecathlonDiscussThrow(Double decathlonDiscussThrow) {
        this.decathlonDiscussThrow = decathlonDiscussThrow;
    }

    public Double getDecathlonPoleVault() {
        return decathlonPoleVault;
    }

    public void setDecathlonPoleVault(Double decathlonPoleVault) {
        this.decathlonPoleVault = decathlonPoleVault;
    }

    public Double getDecathlonJavelinThrow() {
        return decathlonJavelinThrow;
    }

    public void setDecathlonJavelinThrow(Double decathlonJavelinThrow) {
        this.decathlonJavelinThrow = decathlonJavelinThrow;
    }

    public String getDecathlon1500M() {
        return decathlon1500M;
    }

    /*
    * additional logic remove empty space
    * */
    public void setDecathlon1500M(String decathlon1500M) {
        if (Objects.nonNull(decathlon1500M)) {
            this.decathlon1500M = decathlon1500M.trim();
        }
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public int compareTo(Athlet o) {
        return totalScore.compareTo(o.getTotalScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlet athlet = (Athlet) o;
        return Objects.equals(name, athlet.name) &&
                Objects.equals(decathlon100M, athlet.decathlon100M) &&
                Objects.equals(decathlonLongJump, athlet.decathlonLongJump) &&
                Objects.equals(decathlonShotPut, athlet.decathlonShotPut) &&
                Objects.equals(decathlonHighJump, athlet.decathlonHighJump) &&
                Objects.equals(decathlon400M, athlet.decathlon400M) &&
                Objects.equals(decathlon110MHurdles, athlet.decathlon110MHurdles) &&
                Objects.equals(decathlonDiscussThrow, athlet.decathlonDiscussThrow) &&
                Objects.equals(decathlonPoleVault, athlet.decathlonPoleVault) &&
                Objects.equals(decathlonJavelinThrow, athlet.decathlonJavelinThrow) &&
                Objects.equals(decathlon1500M, athlet.decathlon1500M) &&
                Objects.equals(totalScore, athlet.totalScore) &&
                Objects.equals(place, athlet.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, decathlon100M, decathlonLongJump, decathlonShotPut, decathlonHighJump, decathlon400M, decathlon110MHurdles, decathlonDiscussThrow, decathlonPoleVault, decathlonJavelinThrow, decathlon1500M, totalScore, place);
    }

    @Override
    public String toString() {
        return "Athlet{" +
                "name='" + name + '\'' +
                ", decathlon100M=" + decathlon100M +
                ", decathlonLongJump=" + decathlonLongJump +
                ", decathlonShotPut=" + decathlonShotPut +
                ", decathlonHighJump=" + decathlonHighJump +
                ", decathlon400M=" + decathlon400M +
                ", decathlon110MHurdles=" + decathlon110MHurdles +
                ", decathlonDiscussThrow=" + decathlonDiscussThrow +
                ", decathlonPoleVault=" + decathlonPoleVault +
                ", decathlonJavelinThrow=" + decathlonJavelinThrow +
                ", decathlon1500M='" + decathlon1500M + '\'' +
                ", totalScore=" + totalScore +
                ", place='" + place + '\'' +
                '}';
    }
}
