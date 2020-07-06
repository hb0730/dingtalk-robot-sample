package com.hb0730.dingtalk.robot.sample.type;

/**
 * 自定义接口返回类型
 *
 * @author bing_huang
 * @date 2020/07/01 18:11
 * @since V1.0
 */
public enum ResponseCodeType {
    /**
     * 消息发送成功
     */
    OK(0);

    ResponseCodeType(Integer value) {
        this.value = value;
    }

    private Integer value;

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
