package com.abay.assenov.util;

import static com.abay.assenov.constant.Contstans.*;

public class ServiceUtil {

    private ServiceUtil() {
    }

    public static Double getSecondsFromDecathlonTime(String time) {

        String[] splited = time.split(DEFAULT_TIME_SEPARATOR);
        Integer minutes = Integer.valueOf(splited[DEFAULT_TIME_MINUTES_INDEX]);
        Double seconds = Double.valueOf(splited[DEFAULT_TIME_SECONDS_INDEX]);
        return (minutes * COUNT_SECONDS_IN_MINUTE) + seconds;
    }

    public static Double getCentimetersFromMeters(Double meters) {

        return meters * COUNT_CENTIMETERS_IN_METER;
    }

    /**
     * Points = INT(A(P — B)^C) for field events (greater distance or height produces a higher score)
     * A, B and C are parameters that vary by discipline, as shown in the table below
     * , while P is the performance by the athlete, measured in seconds (running)
     * , metres (throwing), or centimetres (jumping)
     * <p>
     * Event	      A	         B	        C
     * 100 m	     25.4347	  18	    1.81
     * Long jump	 0.14354	220	        1.4
     * Shot put	     51.39  	1.5         1.05
     * High jump	 0.8465	    75	        1.42
     * 400 m	    1.53775	    82	        1.81
     * 110 m hurdles 5.74352	28.5	    1.92
     * Discus throw	 12.91	    4	        1.1
     * Pole vault	0.2797	    100	        1.35
     * Javelin throw  10.14	    7	        1.08
     * 1500 m	    0.03768	    480	        1.85
     * Main article: Decathlon scoring tables
     * </p>
     */
    public static Double scoreDecathlonPointSystem(Double valueA, Double valueB, Double valueC, Double valueP) {
        // also for right calculating make calibration by this calculator http://www.sportcalculators.com/decathlon-calculator
        return Math.floor(valueA * Math.pow(Math.abs(valueP - valueB), valueC));
    }

}
