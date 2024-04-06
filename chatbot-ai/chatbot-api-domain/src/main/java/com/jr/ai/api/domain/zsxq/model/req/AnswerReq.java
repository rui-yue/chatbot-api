package com.jr.ai.api.domain.zsxq.model.req;

/**
 * author: JiangJiang
 * description 问题回答对象
 * date: 2024/4/6 13:33
 */
public class AnswerReq {
    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }
}
