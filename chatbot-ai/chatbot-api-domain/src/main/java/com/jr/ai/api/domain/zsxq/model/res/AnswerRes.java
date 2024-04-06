package com.jr.ai.api.domain.zsxq.model.res;

/**
 * author: JiangJiang
 * description 问题回答结果
 * date: 2024/4/6 13:34
 */
public class AnswerRes {
    private boolean isSucceeded;

    public AnswerRes(boolean isSucceeded) {
        this.isSucceeded = isSucceeded;
    }

    public boolean isSucceeded() {
        return isSucceeded;
    }

    public void setSucceeded(boolean succeeded) {
        isSucceeded = succeeded;
    }
}
