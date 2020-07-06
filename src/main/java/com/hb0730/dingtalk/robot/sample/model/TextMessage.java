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
 * 文本请求消息
 *
 * @author bing_huang
 * @date 2020/07/01 18:08
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class TextMessage extends BaseMessage {

    /**
     * 文本消息的具体内容
     */
    private String content;

    /**
     * 可以通过群成员的绑定手机号来@具体的群成员
     */
    private String[] atMobiles;

    /**
     * 是否艾特所有人
     * 也可以设置isAtAll=true来@所有人
     */
    private boolean isAtAll;

    public TextMessage() {
    }

    public TextMessage(String content) {
        this.content = content;
    }

    public TextMessage(String content, String[] atMobiles) {
        this.content = content;
        this.atMobiles = atMobiles;
    }

    public TextMessage(String content, boolean isAtAll) {
        this.content = content;
        this.isAtAll = isAtAll;
    }

    @Override
    public Map<String, Object> toMessageMap() {
        Assert.hasText(this.content, "内容不为空");
        if (!Objects.equals(MessageType.text, this.msgType)) {
            throw new IllegalArgumentException("please check the necessary parameters!");
        }

        HashMap<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("msgtype", this.msgType);

        HashMap<String, String> textItems = new HashMap<>(8);
        textItems.put("content", this.content);
        resultMap.put("text", textItems);

        HashMap<String, Object> atItems = new HashMap<>(8);
        atItems.put("atMobiles", this.atMobiles);
        atItems.put("isAtAll", this.isAtAll);
        resultMap.put("at", atItems);

        return resultMap;
    }

    @Override
    protected void init() {
        this.msgType = MessageType.text;
    }

}
