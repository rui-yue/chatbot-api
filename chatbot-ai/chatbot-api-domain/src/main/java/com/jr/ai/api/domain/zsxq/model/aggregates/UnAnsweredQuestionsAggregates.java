package com.jr.ai.api.domain.zsxq.model.aggregates;

import com.jr.ai.api.domain.zsxq.model.res.RespData;

/**
 * author: JiangJiang
 * description 未回答问题聚合信息
 * date: 2024/4/6 13:11
 */
public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;
    private RespData resp_data;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }
}
