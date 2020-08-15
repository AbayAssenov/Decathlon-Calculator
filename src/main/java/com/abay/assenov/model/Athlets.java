package com.abay.assenov.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "athlets")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlSeeAlso({Athlet.class})
public class Athlets {

    @XmlElement(name = "athlet")
    private List<Athlet> athlets = null;

    public Athlets(List<Athlet> athlets) {
        this.athlets = athlets;
    }

    public Athlets() {
    }

    public List<Athlet> getAthlets() {
        return athlets;
    }

    public void setAthlets(List<Athlet> athlets) {
        this.athlets = athlets;
    }

    @Override
    public String toString() {
        return "Athlets{" +
                "athlets=" + athlets +
                '}';
    }
}
