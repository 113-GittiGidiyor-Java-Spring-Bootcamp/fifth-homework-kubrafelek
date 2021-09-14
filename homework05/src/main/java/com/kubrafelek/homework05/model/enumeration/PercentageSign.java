package com.kubrafelek.homework05.model.enumeration;

public enum PercentageSign {

    MINUS("Azalt", "-"),
    PLUS("Artır", "+");

    private String percentageName;
    private String percentageSign;

    PercentageSign(String percenateName, String percentageSign) {
        this.percentageName = percenateName;
        this.percentageSign = percentageSign;
    }

    public String getPercentageName() {
        return percentageName;
    }

    public String getPercentageSign() {
        return percentageSign;
    }
}
