package com.hb0730.dingtalk.robot.sample.model;


import com.hb0730.dingtalk.robot.sample.type.MessageType;

import java.io.Serializable;
import java.util.Map;

/**
 * 请求消息的抽象类
 *
 * @author bing_huang
 * @date 2020/07/01 18:05
 * @since V1.0
 */
public abstract class BaseMessage implements Serializable {

    private static final long serialVersionUID = 4711363181109638146L;

    public BaseMessage() {
        init();
    }

    /**
     * 消息类型
     */
    protected MessageType msgType;

    public MessageType getMsgType() {
        return msgType;
    }

    /**
     * 初始化MmessageType方法
     */
    protected abstract void init();

    /**
     * 返回Message对象组装出来的Map对象，供后续JSON序列化
     *
     * @return Map
     */
    public abstract Map<String, Object> toMessageMap();

}
