package com.hb0730.dingtalk.robot.sample.client;

import com.hb0730.dingtalk.robot.sample.model.BaseMessage;
import com.hb0730.dingtalk.robot.sample.model.DingTalkResponse;
import com.hb0730.dingtalk.robot.sample.model.TextMessage;
import com.hb0730.dingtalk.robot.sample.utils.json.GsonUtils;
import com.hb0730.dingtalk.robot.sample.utils.okhttp.OkHttpClientUtil;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * 钉钉机器人客户端
 *
 * @author bing_huang
 * @date 2020/07/01 18:11
 * @see <a href="https://github.com/caozxvip/dingtalk-robot/blob/master/src/main/java/com/mickey/dingtalk/client/DingTalkRobotClient.java">来源</a>
 * @since V1.0
 * @deprecated
 */
public class DingTalkRobotClient {

    /**
     * 钉钉机器人的WebHook地址
     */
    private final String webhook;

    public DingTalkRobotClient(String webhook) {
        this.webhook = webhook;
    }

    /**
     * 支持外部传入WebHook地址的方式发送消息
     *
     * @param message 消息
     * @return 是否成功
     */
    public DingTalkResponse sendMessage(BaseMessage message) {
        OkHttpClientUtil client = OkHttpClientUtil.getInstance();
        try {
            Response response = client.postJson(webhook, GsonUtils.json2String(message.toMessageMap()));
            String resultJson = Objects.requireNonNull(response.body()).string();
            return GsonUtils.gsonToBean(resultJson, DingTalkResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送文本信息
     *
     * @param message 文本消息
     * @return 响应结果
     */
    public DingTalkResponse sendTextMessage(TextMessage message) {
        return sendMessage(message);
    }

}
