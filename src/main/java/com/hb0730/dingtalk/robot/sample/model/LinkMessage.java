package com.hb0730.dingtalk.robot.sample.model;

import com.hb0730.dingtalk.robot.sample.type.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 链接消息类型
 *
 * @author bing_huang
 * @date 2020/07/02 7:50
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class LinkMessage extends BaseMessage {
    /**
     * 消息简介
     */
    private String text;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 封面图片URL
     */
    private String picUrl;

    /**
     * 消息跳转URL
     */
    private String messageUrl;

    public LinkMessage() {
    }

    public LinkMessage(String title, String text, String messageUrl) {
        this.text = text;
        this.title = title;
        this.messageUrl = messageUrl;
    }

    public LinkMessage(String title, String text, String messageUrl, String picUrl) {
        this.text = text;
        this.title = title;
        this.picUrl = picUrl;
        this.messageUrl = messageUrl;
    }

    @Override
    protected void init() {
        this.msgType = MessageType.link;
    }

    @Override
    public Map<String, Object> toMessageMap() {
        Assert.hasText(this.messageUrl, "消息跳转URL不为空");
        Assert.hasText(this.title, "标题不为空");
        Assert.hasText(this.text, "消息简介不为空");
        if (!Objects.equals(MessageType.markdown, this.msgType)) {
            throw new IllegalArgumentException("please check the necessary parameters!");
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("msgtype", this.msgType);

        HashMap<String, String> linkItems = new HashMap<>();
        linkItems.put("title", this.title);
        linkItems.put("text", this.text);
        linkItems.put("picUrl", this.picUrl);
        linkItems.put("messageUrl", this.messageUrl);
        resultMap.put("link", linkItems);

        return resultMap;
    }
}
