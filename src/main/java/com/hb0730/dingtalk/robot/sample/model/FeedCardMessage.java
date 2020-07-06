package com.hb0730.dingtalk.robot.sample.model;

import com.hb0730.dingtalk.robot.sample.type.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息卡片类型Message
 *
 * @author bing_huang
 * @date 2020/07/02 8:02
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class FeedCardMessage extends BaseMessage {
    private static final int MAX_BUTTON_COUNT = 10;
    private static final int MIN_BUTTON_COUNT = 1;

    /**
     * 消息明细条目
     */
    private List<FeedCardMessageItem> feedCardItems = new ArrayList<>();

    public FeedCardMessage() {
        super();
    }

    public FeedCardMessage(List<FeedCardMessageItem> feedCardItems) {
        if (!(feedCardItems instanceof ArrayList)) {
            throw new IllegalArgumentException("feedCardItems must bu ArrayList");
        }
        if (feedCardItems.size() > MAX_BUTTON_COUNT) {
            throw new IllegalArgumentException("the number of buttons is not advise bigger than " + MAX_BUTTON_COUNT);
        }
        this.feedCardItems = feedCardItems;
    }

    @Override
    protected void init() {
        this.msgType = MessageType.feedCard;
    }

    @Override
    public Map<String, Object> toMessageMap() {
        if (feedCardItems == null || feedCardItems.size() < MIN_BUTTON_COUNT) {
            throw new IllegalArgumentException("the number of feedCardItems is not allow lower than " + MIN_BUTTON_COUNT);
        }

        if (feedCardItems.size() > MAX_BUTTON_COUNT) {
            throw new IllegalArgumentException("the number of buttons is not advise bigger than " + MAX_BUTTON_COUNT);
        }

        HashMap<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("msgtype", this.msgType);

        HashMap<String, Object> feedCardMap = new HashMap<>(8);
        feedCardMap.put("links", this.feedCardItems);
        resultMap.put("feedCard", feedCardMap);

        return resultMap;
    }

    public List<FeedCardMessageItem> getFeedCardItems() {
        return feedCardItems;
    }

    public void addFeedCardItem(FeedCardMessageItem item) {
        if (item == null || StringUtils.isEmpty(item.getMessageUrl()) ||
                StringUtils.isEmpty(item.getPicUrl()) || StringUtils.isEmpty(item.getTitle())) {
            throw new IllegalArgumentException("please check the necessary parameters of item!");
        }
        if (feedCardItems == null || feedCardItems.size() >= MAX_BUTTON_COUNT) {
            throw new IllegalArgumentException("the number of buttons is not advise bigger than " + MAX_BUTTON_COUNT);
        }
        feedCardItems.add(item);
    }
}
