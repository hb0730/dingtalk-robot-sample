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
 * Markdown消息类型
 *
 * @author bing_huang
 * @date 2020/07/02 7:44
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class MarkdownMessage extends BaseMessage {
    /**
     * 消息简介
     */
    private String text;
    /**
     * 消息标题
     */
    private String title;

    /**
     * 可以通过群成员的绑定手机号来@具体的群成员
     */
    private String[] atMobiles;

    /**
     * 是否@所有人
     * 也可以设置isAtAll=true来@所有人
     */
    private boolean isAtAll = false;

    public MarkdownMessage() {
    }

    public MarkdownMessage(String title, String text) {
        this.text = text;
        this.title = title;
    }

    public MarkdownMessage(String title, String text, String[] atMobiles) {
        this.text = text;
        this.title = title;
        this.atMobiles = atMobiles;
    }

    public MarkdownMessage(String title, String text, boolean isAtAll) {
        this.text = text;
        this.title = title;
        this.isAtAll = isAtAll;
    }

    @Override
    protected void init() {
        this.msgType = MessageType.markdown;
    }

    @Override
    public Map<String, Object> toMessageMap() {
        Assert.hasText(this.title, "标题不为空");
        Assert.hasText(this.text, "消息简介不为空");
        if (!Objects.equals(MessageType.markdown, this.msgType)) {
            throw new IllegalArgumentException("please check the necessary parameters!");
        }
        HashMap<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("msgtype", this.msgType);

        HashMap<String, String> markdownItems = new HashMap<>(8);
        markdownItems.put("title", this.title);
        markdownItems.put("text", this.text);
        resultMap.put("markdown", markdownItems);

        HashMap<String, Object> atItems = new HashMap<>(8);
        atItems.put("atMobiles", this.atMobiles);
        atItems.put("isAtAll", this.isAtAll);
        resultMap.put("at", atItems);

        return resultMap;
    }
}
