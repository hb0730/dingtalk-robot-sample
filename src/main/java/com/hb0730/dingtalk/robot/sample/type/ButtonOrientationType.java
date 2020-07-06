package com.hb0730.dingtalk.robot.sample.type;

/**
 * ActionCard消息按钮布局枚举值
 *
 * @author bing_huang
 * @date 2020/07/02 7:55
 * @since V1.0
 */
public enum ButtonOrientationType {
    /**
     * 水平布局
     */
    HORIZONTAL("水平布局", "1"),
    /**
     * 垂直布局
     */
    VERTICAL("垂直布局", "0");

    private final String comment;

    private final String value;

    ButtonOrientationType(String comment, String value) {
        this.comment = comment;
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public String getValue() {
        return value;
    }
}
