package com.hb0730.dingtalk.robot.sample.model;

import lombok.Data;
import lombok.ToString;

/**
 * 钉钉返回的消息体，可以根据errcode和errmsg
 *
 * @author bing_huang
 * @date 2020/07/01 18:13
 * @since V1.0
 */
@Data
@ToString
public class DingTalkResponse {
    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

}
