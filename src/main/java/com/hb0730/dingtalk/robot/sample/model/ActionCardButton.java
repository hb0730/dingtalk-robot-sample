package com.hb0730.dingtalk.robot.sample.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 跳转卡片中的按钮实体类
 *
 * @author bing_huang
 * @date 2020/07/02 7:55
 * @since V1.0
 */
@Data
@ToString
public class ActionCardButton implements Serializable {

    private static final long serialVersionUID = 7601205929154536390L;
    /**
     * 按钮标题
     */
    private String title;
    /**
     * 实际点击时调用的URL
     */
    private String actionUrl;

    public ActionCardButton() {
    }

    public ActionCardButton(String title, String actionUrl) {
        this.title = title;
        this.actionUrl = actionUrl;
    }

    public static ActionCardButton defaultReadButton(String actionUrl) {
        ActionCardButton button = new ActionCardButton();
        button.setTitle("阅读全文");
        button.setActionUrl(actionUrl);
        return button;
    }

}
