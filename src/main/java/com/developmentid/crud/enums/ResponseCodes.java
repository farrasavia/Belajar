package com.developmentid.crud.enums;

public enum ResponseCodes {
    SUCCESS("00", "success"),
    FAILED("01", "failed"),
    EXISTS("02", "data already exists"),
    NOTFOUND("03", "data not found"),
    NOAUTH("04", "unauthorized"),
	OTHER("99", "other error");
	
    public final String code;
    public final String desc;
 
    private ResponseCodes(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
