package com.hb0730.dingtalk.robot.sample.service;


import com.hb0730.dingtalk.robot.sample.model.BaseMessage;
import com.hb0730.dingtalk.robot.sample.model.DingTalkResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author bing_huang
 * @date 2020/07/02 8:05
 * @since V1.0
 */
public interface IDingTalkService {

    /**
     * 普通发送方式
     *
     * @param message 消息
     * @return 响应结果
     */
    @Nullable
    @SuppressWarnings("UnusedReturnValue")
    DingTalkResponse sendMessage(@NonNull BaseMessage message);

    /**
     * 异步发送方式
     *
     * @param message 消息
     * @return 响应结果
     */
    @Nullable
    @SuppressWarnings("UnusedReturnValue")
    DingTalkResponse sendMessageAsync(@NonNull BaseMessage message);
}
