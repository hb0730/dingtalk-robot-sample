package com.hb0730.dingtalk.robot.sample.service;

import com.hb0730.dingtalk.robot.sample.exception.DingTalkException;
import com.hb0730.dingtalk.robot.sample.model.BaseMessage;
import com.hb0730.dingtalk.robot.sample.model.DingTalkResponse;
import com.hb0730.dingtalk.robot.sample.utils.json.GsonUtils;
import com.hb0730.dingtalk.robot.sample.utils.okhttp.OkHttpClientUtil;
import okhttp3.Call;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * dingTalk service 抽象
 *
 * @author bing_huang
 * @date 2020/07/02 8:10
 * @since V1.0
 */
public abstract class AbstractDingTalkService implements IDingTalkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDingTalkService.class);
    /**
     * webhok url
     */
    private final String webhok;
    private final OkHttpClientUtil client;
    /**
     * 密钥
     */
    private String secret;

    public AbstractDingTalkService(String webhok) {
        this.webhok = webhok;
        this.client = OkHttpClientUtil.getInstance();
    }

    /**
     * 发送消息
     *
     * @param tryToAsync 是否异常
     * @param secret     密钥
     * @param message    消息
     * @return 响应结果
     */
    @Nullable
    protected DingTalkResponse sendMessage(Boolean tryToAsync, @Nullable String secret, @NonNull BaseMessage message) {
        Assert.notNull(message, "消息不允许为空");
        if (tryToAsync) {
            return this.sendAsyncMessage(secret, message);
        } else {
            return this.sendSyncMessage(secret, message);
        }
    }

    /**
     * 异步发送
     *
     * @param secret  密钥
     * @param message 消息
     * @return 响应结果
     */
    @Nullable
    protected DingTalkResponse sendAsyncMessage(@Nullable String secret, @NonNull BaseMessage message) {
        this.secret = secret;

        try {

            String url = "";
            if (StringUtils.isNotBlank(secret)) {
                url = sign();
            } else {
                url = webhok;
            }
            final DingTalkResponse[] talkResponse = new DingTalkResponse[1];
            client.postJsonAsync(url, GsonUtils.json2String(message.toMessageMap()), new OkHttpClientUtil.MyNetCall() {
                @Override
                public void success(Call call, Response response) throws IOException {
                    String resultJson = Objects.requireNonNull(response.body()).string();
                    talkResponse[0] = GsonUtils.gsonToBean(resultJson, DingTalkResponse.class);
                }

                @Override
                public void failed(Call call, IOException e) {
                    LOGGER.error("发送失败", e);
                    throw new DingTalkException("发送失败");
                }
            });
            return talkResponse[0];
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("发送失败", e);
            throw new DingTalkException("发送失败");
        }
    }

    /**
     * 同步发送
     *
     * @param secret  密钥
     * @param message 消息
     * @return 响应结果
     */
    public DingTalkResponse sendSyncMessage(@Nullable String secret, @NonNull BaseMessage message) {
        this.secret = secret;
        try {
            String url = "";
            if (StringUtils.isNotBlank(secret)) {
                url = sign();
            } else {
                url = webhok;
            }
            Response response = client.postJson(url, GsonUtils.json2String(message.toMessageMap()));
            String resultJson = Objects.requireNonNull(response.body()).string();
            return GsonUtils.gsonToBean(resultJson, DingTalkResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("发送失败", e);
            throw new DingTalkException("发送失败");
        }
    }

    /**
     * 获取后一段加签url
     *
     * @return 加签后的url
     */
    private String sign() {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;

        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), StandardCharsets.UTF_8);
            return this.webhok + "&timestamp=" + timestamp + "&sign=" + sign;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("签名失败dingtalk", e);
            throw new DingTalkException("签名失败dingtalk");
        }
    }
}
