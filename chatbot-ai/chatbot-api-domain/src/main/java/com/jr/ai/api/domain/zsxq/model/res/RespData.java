package com.jr.ai.api.domain.zsxq.model.res;

import com.jr.ai.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * author: JiangJiang
 * description 返回对象 - 结果数据
 * date: 2024/4/6 13:12
 */
public class RespData {
    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }
}
