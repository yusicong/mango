package com.ysc.device.service.domain.enums;




public enum AntennaChannelEnum implements DictEnum {

    /**
     * 天线通道 0：内置，1：外置
     */
    IN(0, "内置"),
    OUT(1, "外置");
    /**
     * 获取值
     */
    private Integer value;

    /**
     * 获取Text
     */
    private String text;


    AntennaChannelEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getText() {
        return text;
    }

    public static String getText(Integer value) {
        for (AntennaChannelEnum type : AntennaChannelEnum.values()) {
            if (value.equals(type.getValue())) {
                return type.getText();
            }
        }
        return "--";
    }

    public static AntennaChannelEnum of(Integer value) {
        for (AntennaChannelEnum type : AntennaChannelEnum.values()) {
            if (value.equals(type.getValue())) {
                return type;
            }
        }
        throw new IllegalArgumentException("invalid access control type value :" + value);
    }
    public static void main(String[] args) {
        System.out.println(getText(3));
    }
}
