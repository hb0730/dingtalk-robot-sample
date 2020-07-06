package com.hb0730.dingtalk.robot.sample.model;

import com.hb0730.dingtalk.robot.sample.type.ButtonOrientationType;
import com.hb0730.dingtalk.robot.sample.type.HideAvatarType;
import com.hb0730.dingtalk.robot.sample.type.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 跳转卡片类型
 *
 * @author bing_huang
 * @date 2020/07/02 7:56
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class ActionCardMessage extends BaseMessage {
    /**
     * 当按钮数目超过2两个时，按钮一定是垂直排列。
     * 这里的最大值和最小值分别设置为10和0
     */
    private static final int MAX_BUTTON_COUNT = 10;
    private static final int MIN_BUTTON_COUNT = 0;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文，支持MarkDown语法
     */
    private String text;

    /**
     * 是否隐藏头像
     * 0 - 不隐藏头像
     * 1 - 隐藏头像
     */
    private HideAvatarType hideAvatar = HideAvatarType.UNHIDE;

    /**
     * 按钮排列方式
     * 0 - 垂直排列
     * 1 - 水平排列
     */
    private ButtonOrientationType btnOrientation = ButtonOrientationType.HORIZONTAL;

    /**
     * 操作按钮成员变量
     * 这个成员变量没有Set方法，主要是为了防止按钮过多造成的较差体验
     */
    private List<ActionCardButton> buttons = new ArrayList<>();

    public ActionCardMessage() {
    }

    public ActionCardMessage(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public ActionCardMessage(String title, String text, HideAvatarType hideAvatar) {
        this.title = title;
        this.text = text;
        this.hideAvatar = hideAvatar;
    }

    public ActionCardMessage(String title, String text, ActionCardButton button) {
        this.title = title;
        this.text = text;
        this.buttons.add(button);
    }

    public ActionCardMessage(String title, String text, HideAvatarType hideAvatar, ActionCardButton button) {
        this.title = title;
        this.text = text;
        this.hideAvatar = hideAvatar;
        this.buttons.add(button);
    }


    @Override
    protected void init() {
        this.msgType = MessageType.actionCard;
    }

    @Override
    public Map<String, Object> toMessageMap() {
        Assert.hasText(this.text, "正文不为空");
        Assert.hasText(this.title, "标题不为空");
        if (buttons == null) {
            throw new IllegalArgumentException("the number of buttons is not allow lower than " + MIN_BUTTON_COUNT);
        }

        if (buttons.size() > MAX_BUTTON_COUNT) {
            throw new IllegalArgumentException("the number of buttons is not advise bigger than " + MAX_BUTTON_COUNT);
        }

        HashMap<String, Object> resultMap = new HashMap<>(8);
        resultMap.put("msgtype", this.msgType);

        HashMap<String, Object> actionCardMap = new HashMap<>(8);
        actionCardMap.put("title", this.title);
        actionCardMap.put("text", this.text);
        actionCardMap.put("hideAvatar", this.hideAvatar.getValue());
        actionCardMap.put("btnOrientation", this.btnOrientation.getValue());

        if (buttons.size() == 1) {
            actionCardMap.put("singleTitle", buttons.get(0).getTitle());
            actionCardMap.put("singleURL", buttons.get(0).getActionUrl());
        } else if (buttons.size() > 1) {
            actionCardMap.put("btns", buttons);
        }

        resultMap.put("actionCard", actionCardMap);
        return resultMap;
    }

    /**
     * 增加操作按钮
     *
     * @param button 跳转卡片中的按钮实体类
     */
    public void addButton(ActionCardButton button) {
        if (button == null) {
            throw new IllegalArgumentException("not allow add empty button");
        }
        if (buttons == null || buttons.size() >= MAX_BUTTON_COUNT) {
            throw new IllegalArgumentException("the number of buttons is not advise bigger than " + MAX_BUTTON_COUNT);
        }
        buttons.add(button);
    }
}
