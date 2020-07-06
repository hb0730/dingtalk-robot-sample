package com.hb0730.dingtalk.robot.sample.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 消息卡片中的明细条目
 *
 * @author bing_huang
 * @date 2020/07/02 8:01
 * @since V1.0
 */
@Data
@ToString
public class FeedCardMessageItem implements Serializable {

    private static final long serialVersionUID = -104409475674778009L;
    /**
     * 标题
     */
    private String title;

    /**
     * 消息跳转URL
     */
    private String messageUrl;

    /**
     * 封面图片URL
     */
    private String picUrl;

    public FeedCardMessageItem() {
    }

    public FeedCardMessageItem(String title, String messageUrl, String picUrl) {
        this.title = title;
        this.messageUrl = messageUrl;
        this.picUrl = picUrl;
    }
}
