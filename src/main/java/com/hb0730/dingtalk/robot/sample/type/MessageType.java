package com.hb0730.dingtalk.robot.sample.type;

/**
 * 定义消息类型，目前有文本、链接、MarkDown、跳转卡片、消息卡片五种枚举值
 *
 * @author bing_huang
 * @date 2020/07/01 18:06
 * @since V1.0
 */
public enum MessageType {
    /**
     * 文本类型
     */
    text,

    /**
     * 链接类型
     */
    link,

    /**
     * MarkDown类型
     */
    markdown,

    /**
     * 跳转卡片类型
     */
    actionCard,

    /**
     * 消息卡片类型
     */
    feedCard;
}
