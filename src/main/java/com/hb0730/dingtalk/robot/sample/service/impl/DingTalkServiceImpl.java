package com.hb0730.dingtalk.robot.sample.service.impl;

import com.hb0730.dingtalk.robot.sample.model.BaseMessage;
import com.hb0730.dingtalk.robot.sample.model.DingTalkResponse;
import com.hb0730.dingtalk.robot.sample.service.AbstractDingTalkService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

/**
 * dingtalk实现
 *
 * @author bing_huang
 * @date 2020/07/02 8:37
 * @since V1.0
 */
public class DingTalkServiceImpl extends AbstractDingTalkService {
    @Getter
    @Setter
    private String secret;

    public DingTalkServiceImpl(String webhok, String secret) {
        super(webhok);
        this.secret = secret;
    }

    public DingTalkServiceImpl(String webhok) {
        super(webhok);
    }

    @Override
    public DingTalkResponse sendMessage(@NonNull BaseMessage message) {
        return super.sendMessage(false, this.secret, message);
    }

    @Override
    public DingTalkResponse sendMessageAsync(@NonNull BaseMessage message) {
        return super.sendMessage(true, this.secret, message);
    }

}
