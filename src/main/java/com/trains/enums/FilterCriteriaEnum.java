package com.trains.enums;

public enum FilterCriteriaEnum {
	
	MAX_STOPS("MAX_STOPS"),
	EXACT_STOPS("EXACT_STOPS"),
	MAX_DISTANCE("MAX_DISTANCE");
    
    private final String value;
    FilterCriteriaEnum(String valueOption){
        value = valueOption;
    }
    public String getValue(){
        return value;
    }
}
