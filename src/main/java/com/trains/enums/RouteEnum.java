package com.trains.enums;

public enum RouteEnum {
	
	DISTANCE_ABC("A-B-C"), 
	DISTANCE_AD("A-D"), 
	DISTANCE_ADC("A-D-C"), 
	DISTANCE_AEBCD("A-E-B-C-D"),
	DISTANCE_AED("A-E-D"),
	TRIPS_MAX_STOP_3_CC("MAX_STOPS,3,C-C"),
	TRIPS_EXACT_STOP_4_AC("EXACT_STOPS,4,A-C"),
	SHORTEST_AC("A-C"),
	SHORTEST_BB("B-B"),
	TRIPS_MAX_DISTANCE_30("MAX_DISTANCE,30,C-C");
    
    private final String value;
    RouteEnum(String valueOption){
        value = valueOption;
    }
    public String getValue(){
        return value;
    }
}
